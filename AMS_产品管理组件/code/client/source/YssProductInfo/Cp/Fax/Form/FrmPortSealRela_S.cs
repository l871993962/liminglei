using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.ElecSeal.Form;
using FAST.Core.Util;
using FAST.Common.Service.Pojo.Base;
using Yss.KRichEx.AutoFilter.Model;
using FAST.Platform.ElectronicSeal.Pojo;
using FAST.Core.Context;
using System.Collections;
using FAST.Core.ElecSeal.Pojo;
using FAST.Common.Service.Pojo;

namespace YssProductInfo.Cp.Fax.Form.Form
{
    /// <summary>
    /// 组合关联签章文件设置界面
    /// ClassName:  FrmPortSealRela_S 
    /// Author:     leijianhua@ysstech.com
    /// CreateDate: 2017/8/20 11:25:52
    /// Copyright:  (c) 2001-2017, 深圳赢时胜 All Rights Reserved.
    /// </summary>
    public partial class FrmPortSealRela_S : FrmBaseSealRela_S
    {
        /// <summary>
        /// 来源窗体的功能代码
        /// </summary>
        private string fromFunCode = "";

        /// <summary>
        /// 构造函数
        /// </summary>
        public FrmPortSealRela_S()
        {
            InitializeComponent();
        }

        /// <summary>
        /// 绑定来源窗体的功能菜单
        /// </summary>
        /// <param name="funCode">funCode</param>
        public void setFromFunCode(string funCode)
        {
            fromFunCode = funCode;
            cboFunCode.Value = funCode;
            cboFunCode.YssReadOnly = true;
        }

        /// <summary>
        /// 重写类型为PortSealRelaInfo
        /// </summary>
        /// <returns>Type</returns>
        public override Type getTargetType()
        {
            return typeof(PortSealRelaInfo);
        }


        /// <summary>
        /// 将扩展的信息封装到POJO
        /// </summary>
        /// <returns>BaseSealRelaInfo</returns>
        public override FAST.Core.ElecSeal.Pojo.BaseSealRelaInfo faceInfoToSealInfo()
        {
            PortSealRelaInfo relaInfo = null;
            if (this.EditedPojo != null)
            {
                relaInfo = this.EditedPojo as PortSealRelaInfo;
            }
            else
            {
                relaInfo = new PortSealRelaInfo();
            }

            relaInfo.C_FUN_CODE = cboFunCode.Value;
            string portCode = "";
            if (cboPortCode.Value != null)
            {
                portCode = cboPortCode.Value;
            }
            string assCode = "";
            if (cboAssetType.Value != null)
            {
                assCode = cboAssetType.Value;
            }
            relaInfo.C_ASSETS_CODE = assCode;

            relaInfo.C_PORT_CODE = portCode;

            return relaInfo;
        }

        /// <summary>
        /// 将扩展的信息展示到窗体
        /// </summary>
        /// <param name="sealRelaInfo">sealRelaInfo</param>
        public override void showSelaInfo(FAST.Core.ElecSeal.Pojo.BaseSealRelaInfo sealRelaInfo)
        {
            PortSealRelaInfo portRelaInfo = sealRelaInfo as PortSealRelaInfo;
            cboAssetType.Value = portRelaInfo.C_ASSETS_CODE;
            cboPortCode.Value = portRelaInfo.C_PORT_CODE;
            cboFunCode.Value = portRelaInfo.C_FUN_CODE;
        }

        /// <summary>
        /// 组合下拉框点击前事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboPortCode_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            e.Cancel = true;
            if (e.Items.Count == 0)
            {
                GroupTextBoxDataLoader.LoadData();
                foreach (BasePojo pojo in GroupTextBoxDataLoader.DataList)
                {
                    e.Items.Add(new KTableEntity(pojo));
                }
            }
        }

        /// <summary>
        /// 保存时根据组合代码拆成多个pojo
        /// </summary>
        /// <param name="status">status</param>
        /// <returns>s</returns>
        public override string yssFormOperationMVC(ClsEnums.StatusSetting status)
        {
            ////获取PojoList
            string result = "";
            ArrayList pojoList = null;
            pojoList = yssGetDataObjMVC(pojoList);

            if (pojoList == null)
            {
                pojoList = new ArrayList();
            }

            if (pojoList.Count > 0)
            {
                PortSealRelaInfo relaInfo = (PortSealRelaInfo)pojoList[0];

                string portCodes = relaInfo.C_PORT_CODE;
                string relaFunCode = relaInfo.C_FUN_CODE;
                //// 将单个对象拆成多个pojo保存
                if (relaInfo.C_PORT_CODE.Contains('|'))
                {
                    pojoList.Clear();
                    string[] portCodeAry = relaInfo.C_PORT_CODE.Split('|');

                    PortSealRelaInfo tmpSealInfo = null;
                    foreach (string portCode in portCodeAry)
                    {
                        tmpSealInfo = (PortSealRelaInfo)relaInfo.Clone();
                        tmpSealInfo.C_PORT_CODE = portCode;
                        pojoList.Add(tmpSealInfo);
                    }
                }



                //// 校验重复
                ////ISealService sealService = ServiceFactory.createService<ISealService>();
                string tipInfo = "";
                if (status == ClsEnums.StatusSetting.YssAdd || status == ClsEnums.StatusSetting.YssCopy)
                {
                    ElecSealFile sealFile = peneCboSealCode.SelectedItem.DataEntity as ElecSealFile;

                    SealQueryCondition queryCondition = new SealQueryCondition(typeof(PortSealRelaInfo));
                    queryCondition.C_FUN_CODE = YssFormMenu.C_FUN_CODE;
                    queryCondition.ExtInfoSingle.Add("C_PORT_CODE", portCodes.Replace('|', ','));
                    queryCondition.C_FUN_CODE = relaFunCode;
                    ////如果选择的是私章，则同一个组合，同一个私章只能设置一次，
                    ////如果是其他章，则一种章的类型只能配置一次。
                    if (sealFile.C_SEAL_TYPE == SealQueryCondition.SEAL_TYPE_PERSIONAL)
                    {
                        queryCondition.C_SEAL_USER = sealFile.C_SEAL_USER;
                    }

                    queryCondition.C_SEAL_TYPE = sealFile.C_SEAL_TYPE;

                    List<BaseSealRelaInfo> sealRelaInfoList = sealFun.getSealRelaInfoByConditon(queryCondition);
                    Dictionary<string, PortSealRelaInfo> infoDict = new Dictionary<string, PortSealRelaInfo>();
                    Dictionary<string, PortSealRelaInfo> dictPortCode = new Dictionary<string, PortSealRelaInfo>();
                    foreach (BaseSealRelaInfo baseInfo in sealRelaInfoList)
                    {
                        PortSealRelaInfo portRelaInfo = baseInfo as PortSealRelaInfo;

                        if (!infoDict.ContainsKey(portRelaInfo.C_PORT_CODE))
                        {
                            infoDict.Add(portRelaInfo.C_PORT_CODE, portRelaInfo);
                            dictPortCode.Add(portRelaInfo.C_PORT_CODE + portRelaInfo.C_ASSETS_CODE + portRelaInfo.C_FUN_CODE, portRelaInfo);
                        }
                    }


                    foreach (BasePojo pojo1 in pojoList)
                    {
                        PortSealRelaInfo tmpSealInfo = pojo1 as PortSealRelaInfo;

                        if (infoDict.ContainsKey(tmpSealInfo.C_PORT_CODE))
                        {
                            if (tmpSealInfo.C_PORT_CODE == "")
                            {
                                ////tipInfo += "签章人[" + s.C_SEAL_MAN + "]组合代码为空的签章已存在\r\n";
                                if (sealFile.C_SEAL_TYPE == SealQueryCondition.SEAL_TYPE_PERSIONAL)
                                {
                                    tipInfo += "签章人[" + sealFile.C_SEAL_USER + "]组合代码为空的" + sealFile.C_SEAL_TYPE + "签章已存在\r\n";
                                }
                                else
                                {
                                    if (dictPortCode.ContainsKey(tmpSealInfo.C_PORT_CODE + tmpSealInfo.C_ASSETS_CODE + tmpSealInfo.C_FUN_CODE))
                                    {
                                        tipInfo += "组合代码为[" + tmpSealInfo.C_PORT_CODE + "]的" + sealFile.C_SEAL_TYPE + "签章已存在\r\n";
                                    }
                                }

                            }
                            else
                            {
                                if (sealFile.C_SEAL_TYPE == SealQueryCondition.SEAL_TYPE_PERSIONAL)
                                {
                                    tipInfo += "签章人[" + sealFile.C_SEAL_USER + "]组合代码为[" + tmpSealInfo.C_PORT_CODE + "]的签章已存在\r\n";
                                }
                                else
                                {
                                    if (dictPortCode.ContainsKey(tmpSealInfo.C_PORT_CODE + tmpSealInfo.C_ASSETS_CODE + tmpSealInfo.C_FUN_CODE))
                                    {
                                        tipInfo += "组合代码为[" + tmpSealInfo.C_PORT_CODE + "]的" + sealFile.C_SEAL_TYPE + "签章已存在\r\n";
                                    }

                                    
                                }
                            }
                        }
                    }
                    if (tipInfo.Length != 0)
                    {
                        ClsRetInfo inf = new ClsRetInfo();
                        inf.icon = MessageBoxIcon.Warning;
                        inf.infoTitle = "消息";
                        inf.infoContent = "数据重复!\r\n" + tipInfo;
                        YssMessageBox.ShowCommonInfoText(inf);
                        return result;
                    }
                }

                result = yssDoSetFormOperMVC(pojoList, status);

            }

            return result;
        }

        /// <summary>
        /// 下拉框点击前事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboFunCode_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            e.Cancel = true;
            if (string.IsNullOrEmpty(fromFunCode) == false)
            {
                e.Items.Clear();
                if (ClsContext.sysFunHash.ContainsKey(fromFunCode))
                {
                    e.Items.Add(new KTableEntity(ClsContext.sysFunHash[fromFunCode]));
                }
            }
            else
            {
                if (e.Items.Count == 0)
                {
                    List<SysFun> funList = getSysFunList();
                    foreach (SysFun fun in funList)
                    {
                        e.Items.Add(new KTableEntity(fun));
                    }
                }
            }
        }

        /// <summary>
        /// 组合改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboPort_SelectedValueChanged(object sender, EventArgs e)
        {
            if (!string.IsNullOrEmpty(this.cboPortCode.Value))
            {
                this.cboAssetType.Value = null;
                this.cboAssetType.YssReadOnly = true;
            }
            else
            {
                this.cboAssetType.YssReadOnly = false;
            }
        }

        /// <summary>
        /// 资产种类改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboAssetType_SelectedValueChanged(object sender, EventArgs e)
        {
            if (!string.IsNullOrEmpty(this.cboAssetType.Value))
            {
                cboPortCode.Value = null;
                cboPortCode.YssReadOnly = true;
            }
            else
            {
                cboPortCode.YssReadOnly = false;
            }
        }

        /// <summary>
        /// ss
        /// </summary>
        /// <returns>s</returns>
        protected virtual List<SysFun> getSysFunList()
        {
            List<SysFun> list = new List<SysFun>();
            SysFun fun = new SysFun();
            fun.C_FUN_CODE = "zhzlMgr";
            fun.C_FUN_NAME = "综合指令管理";
            list.Add(fun);
            SysFun fun_FrAststat = new SysFun();
            fun_FrAststat.C_FUN_CODE = "FrAststat";
            fun_FrAststat.C_FUN_NAME = "估值报表";
            list.Add(fun_FrAststat);
            SysFun fun_balanceTable = new SysFun();
            fun_balanceTable.C_FUN_CODE = "balanceTable";
            fun_balanceTable.C_FUN_NAME = "余额报表";
            list.Add(fun_balanceTable);
            SysFun fun_reportBrowse = new SysFun();
            fun_reportBrowse.C_FUN_CODE = "reportBrowse";
            fun_reportBrowse.C_FUN_NAME = "财务报表";
            list.Add(fun_reportBrowse); 
            return list;
        }

        /// <summary>
        /// 根据修改行的数据ID，来更新列表界面的数据。
        /// STORY #34372 优化系统数据维护后的查询。张绍林-20160926
        /// </summary>
        /// <param name="operResult">保存操作结果</param>
        private void UpdateFrmListTableByDataId(ClsRetInfo operResult)
        {
            if (frmBaseViewList != null && operResult.operRes == "Success")
            {
                List<string> dataIds = operResult.cidenList;
                if (dataIds == null)
                {
                    dataIds = new List<string>();
                }

                ////找到修改、审核、反审核的数据ID
                if (this.ModifiedRowIds != null && dataIds.Count == 0)
                {
                    dataIds.AddRange(this.ModifiedRowIds);
                }

                this.frmBaseViewList.UpdateRowsByDataIds(dataIds);
            }
        }
    }
}
