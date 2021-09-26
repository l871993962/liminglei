using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Net;
using System.Text.RegularExpressions;
using System.Collections;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Context;
using FAST.Core.BaseControl;
using FAST.Core.Context.Events;
using FAST.Core.CRUD.Form;
using FAST.Core.Communication.Service;
using FAST.Core.Exceptions;
using FAST.Core.Util;
using YssSztTool.Service.Para;
using YssSztTool.Pojo.Para;
using Yss.KTable.Models;

namespace YssSztTool.Form.Para
{
    /// <summary>
    /// STORY42784中国银行_深证通伺服器要求采用热备模式
    /// STORY42660【中国银行】深证通伺服器要求采用热备模式
    /// DESC: 深圳通参数设置窗体
    /// CREATED BY: wulongxing
    /// CREATED TIME: 2017-06-12
    /// </summary>
    public partial class Frm_Mr_Info_S : FrmBaseSet
    {
        public Frm_Mr_Info_S()
        {
            InitializeComponent();
            initRichTable();
            bUseMVCService = true;
            switchMarkDict.Add("主机","MAIN");
            switchMarkDict.Add("备机", "STANDBY");
            erParaService = ServiceFactory.createService<IErParaService>();
        }
        private IErParaService erParaService = null;
        private ErPara mainErPara = null;
        private Dictionary<string,string> switchMarkDict = new Dictionary<string,string>();
        /// <summary>
        /// 显示单条数据，参数为set界面数据对应的pojo对象
        /// </summary>
        /// <param name="pojo">显示数据对应的pojo对象</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            try
            {

                List<MrInfo> list = erParaService.queryMrInfos(mainErPara.C_Para_Code);
                mainErPara.MrInfoList = list;

                ErPara erPara = mainErPara;
                this.txtParaCode.Text = erPara.C_Para_Code;
                this.txtParaName.Text = erPara.C_Para_Name;
                this.txtSrcUser.Text = erPara.C_Src_UserId;
                this.txtSrcAppId.Text = erPara.C_Src_AppId;
                ////this.txtDeptCode.Text = erPara.C_Dept_Code;
                ////this.txtCertId.Text = erPara.C_Cert_Id;
                this.txtAppPwd.Text = erPara.C_Pkg_Password;

                Table linkTable = this.richTable.Table;
                linkTable.Rows.Clear();
                List<MrInfo> mrInfoList = erPara.MrInfoList;
                if (mrInfoList != null && mrInfoList.Count > 0)
                {
                    foreach (MrInfo mrInfo in mrInfoList)
                    {
                        Row row = new Row();
                        row.Tag = mrInfo;
                        foreach (Column col in linkTable.Columns)
                        {
                            if (col.DataPropertyName.Equals("C_SWITCH_MARK"))
                            {
                                Cell cell1 = new Cell();
                                YssSelCombox main = getCombox();
                                main.Value = mrInfo.C_Dv_Switch_Mark;
                                cell1.Text = main.Text;
                                row.Cells.Add(cell1);
                            }
                            else if (col.DataPropertyName.Equals("C_MR_IP"))
                            {
                                Cell cell2 = new Cell();
                                cell2.Text = mrInfo.C_Mr_Ip;
                                row.Cells.Add(cell2);
                            }
                            else if (col.DataPropertyName.Equals("C_MR_PORT"))
                            {
                                Cell cell3 = new Cell();
                                cell3.Text = mrInfo.C_Mr_Port;
                                row.Cells.Add(cell3);
                            }
                            else
                            {
                                Cell cell4 = new Cell();
                                row.Cells.Add(cell4);
                            }
                        }

                        linkTable.Rows.Add(row);
                    }

                    linkTable.Refresh();
                }
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }

        }
        /// <summary>
        /// 将界面控件录入信息封装成pojo对象
        /// </summary>
        /// <returns>封装的pojo对象</returns>
        public override AuditableParamPojo faceInfoToObjMVC()
        {
            try
            {
                ErPara erPara = new ErPara();
                if (mainErPara != null)
                {
                    erPara.Id = mainErPara.Id;
                }
                erPara.C_Para_Code = this.txtParaCode.Text;
                erPara.C_Para_Name = this.txtParaName.Text;
                erPara.C_Src_UserId = this.txtSrcUser.Text;
                erPara.C_Src_AppId = this.txtSrcAppId.Text;
                erPara.C_Dept_Code = " ";
                erPara.C_Cert_Id = " ";
                erPara.C_Pkg_Password = this.txtAppPwd.Text;
                getMrInfoList(erPara);
                return erPara;
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }
        }

        private void getMrInfoList(ErPara erPara)
        {
            Table mainTable = this.richTable.Table;
            //// 组装应用连接信息
            List<MrInfo> mrInfoList = new List<MrInfo>();
            int rowcount = 0;
            foreach (Row r in mainTable.Rows)
            {
                rowcount = rowcount + 1;
                MrInfo mrInfo = new MrInfo();
                mrInfo.N_Order = rowcount;
                mrInfo.C_Para_Code = this.txtParaCode.Text;
                foreach (Cell c in r.Cells)
                {
                    string t = c.Text;
                    switch (c.ColumnIndex)
                    {
                        case 1:
                            if (c.InnerControl != null && c.InnerControl is YssSelCombox)
                            {
                                t = ((YssSelCombox)c.InnerControl).Value;
                            }else{
                                t = switchMarkDict[t];
                            }
                            mrInfo.C_Dv_Switch_Mark = t;
                            break;
                        case 2:
                            mrInfo.C_Mr_Ip = t;
                            break;
                        case 3:
                            mrInfo.C_Mr_Port = t;
                            break;
                    }
                }

                mrInfoList.Add(mrInfo);
            }

            erPara.MrInfoList = mrInfoList;
        }

        private void Frm_Mr_Info_S_YssOnBeforeSaveClick(object sender, YssBeforeOperEventArgs e)
        {
            e.IsCancel = true;
            Table mainTable = this.richTable.Table;
            if (mainTable.Rows.Count == 0)
            {
                TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                transferErrorMessage.MESSAGESLINK = new List<string>();
                string errorMess = "请配置连接IP，连接端口信息!";
                transferErrorMessage.MESSAGESLINK.Add(errorMess);
                throw new TransferException(JsonUtil.toJson(transferErrorMessage));
            }
            int rowcount = 0;
            bool hasMain = false;
            StringBuilder strBuilder = null;
            foreach (Row r in mainTable.Rows)
            {
                rowcount = rowcount + 1;
                strBuilder = new StringBuilder();
                foreach (Cell c in r.Cells)
                {
                    string t = c.Text;
                    if(c.InnerControl != null && c.InnerControl is YssSelCombox){
                        t = ((YssSelCombox)c.InnerControl).Value;
                    }
                    switch (c.ColumnIndex)
                    {
                        case 1:
                            if (t == null || t == "")
                            {
                                strBuilder.Append("第").Append(rowcount).Append("行").Append("主备标识不能为空!");
                            }

                            if (hasMain && (t == "MAIN" || t == "主机"))
                            {
                                strBuilder.Append("主备标识【主机】需唯一!");
                            }
                            if (t == "MAIN" || t == "主机")
                            {
                                hasMain = true;
                            }
                            break;
                        case 2:
                            if (t == null || t == "")
                            {
                                strBuilder.Append("第").Append(rowcount).Append("行").Append("连接IP不能为空!");
                            }
                            else if (!isIp(t))
                            {
                                strBuilder.Append("第").Append(rowcount).Append("行").Append("连接IP格式错误!");
                            }
                            break;
                        case 3:
                            if (t == null || t == "")
                            {
                                strBuilder.Append("第").Append(rowcount).Append("行").Append("连接端口不能为空!");
                            }
                            else
                            {
                            }
                            break;
                    }
                    if (strBuilder != null && strBuilder.Length > 0)
                    {
                        TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                        transferErrorMessage.MESSAGESLINK = new List<string>();
                        string errorMess = strBuilder.ToString();
                        transferErrorMessage.MESSAGESLINK.Add(errorMess);
                        throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                    }
                }
            }
            e.IsCancel = false;
        }

        private bool isIp(string ipStr)
        {
            /*if (!Regex.IsMatch(ipStr, @"^((25[0-5]|2[0-4]\d|(1\d|[1-9])?\d)\.){3}(25[0-5]|2[0-4]\d|(1\d|[1-9])?\d)$"))
            {
                return false;
            }
            else
            {
                return true;
            }
            */
            ////微服务版本，使用的是服务名访问，不是ip地址，故去掉ip校验规则
            return true;
        }
        /// <summary>
        /// 初始化子table，增加列头信息
        /// </summary>
        private void initRichTable()
        {
            Table t = this.richTable.Table;
            Column col = new MarkColumn();
            col.DataPropertyName = "ShowRowIndexColumn";
            t.Columns.Add(col);

            Column column1 = new Column();
            column1.DataPropertyName = "C_SWITCH_MARK";
            column1.Text = "主备标识";
            column1.Width = 100;
            t.Columns.Add(column1);
            Column column2 = new Column();
            column2.DataPropertyName = "C_MR_IP";
            column2.Text = "连接IP";
            column2.Width = 150;
            t.Columns.Add(column2);
            Column column3 = new Column();
            column3.DataPropertyName = "C_MR_PORT";
            column3.Text = "连接PORT";
            column3.Width = 100;
            t.Columns.Add(column3);

            t.CellMouseClick += new Yss.KTable.Events.CellEventHandler(t_CellMouseClick);

            // 需要单独方法控制,只能在修改状态时可编辑
            t.EditCells = true;

            t.Refresh();
        }

        /// <summary>
        /// 单元格单击事件
        /// </summary>
        /// <param name="sender">单元格</param>
        /// <param name="e">事件</param>
        private void t_CellMouseClick(object sender, Yss.KTable.Events.CellEventArgs e)
        {
            if (e.Cell.ColumnIndex == 1)
            {
                string text = e.Cell.Text;
                e.Cell.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.ShowOnClick;
                if (e.Cell.InnerControl == null)
                {
                    YssSelCombox combox = this.getCombox();
                    combox.Value = text;
                    e.Cell.InnerControl = combox;
                }
            }
        }
        /// <summary>
        /// 初始化连接内容信息
        /// </summary>
        /// <returns>连接类型下拉框</returns>
        private YssSelCombox getCombox()
        {
            YssSelCombox cboSwitchMark = new YssSelCombox();
            ControlMethodInfo controlMethodInfo1 = new ControlMethodInfo();
            cboSwitchMark.DisplayName = "C_DV_NAME";
            cboSwitchMark.DisplayValue = "C_DV_CODE";
            cboSwitchMark.FilterCond = "";
            cboSwitchMark.Location = new System.Drawing.Point(360, 79);
            cboSwitchMark.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo1.MethodName = "getDataListByTypes";
            controlMethodInfo1.MethodParams = null;
            controlMethodInfo1.MethodParamValues = new string[] {
        "SWITCH_MARK,"};
            controlMethodInfo1.Methods = null;
            cboSwitchMark.MethodInfo = controlMethodInfo1;
            cboSwitchMark.Name = "cboSwitchMark";
            cboSwitchMark.QueryCond = "SWITCH_MARK";
            cboSwitchMark.QueryType = "";
            cboSwitchMark.Size = new System.Drawing.Size(119, 21);
            cboSwitchMark.TabIndex = 13;
            cboSwitchMark.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            cboSwitchMark.YssCaption = "主备标识";
            cboSwitchMark.YssIsMust = true;

            return cboSwitchMark;
        }
        public override void initControlStat()
        {
            base.initControlStat();

            Frm_Mr_Info_L frmList = frmBaseViewList as Frm_Mr_Info_L;
            mainErPara = frmList.MainDataPojo as ErPara;

            if (status == ClsEnums.StatusSetting.YssCopy)
            {
                this.tbMain.ReadOnly = true;
            }
            else if (status == ClsEnums.StatusSetting.YssEdit)
            {
                this.tbMain.ReadOnly = true;
            }
            else if (status == ClsEnums.StatusSetting.YssAdd)
            {
                this.tbMain.ReadOnly = true;
            }
        }
        /// <summary>
        /// 将界面控件录入信息封装成pojo对象
        /// </summary>
        /// <returns>封装的pojo对象</returns>
        public override BasePojo faceInfoToBaseObjMVC()
        {
            BasePojo basePojo = faceInfoToObjMVC();
            return basePojo;
        }

        /// <summary>
        /// 根据窗体状态执行相应的保存操作
        /// </summary>
        /// <param name="pojoList">操作的数据对象</param>
        /// <param name="status">窗体的打开状态</param>
        /// <returns>保存数据后后台返回的操作结果信息</returns>
        protected override string yssDoSetFormOperMVC(ArrayList pojoList, ClsEnums.StatusSetting status)
        {
            string operResult = "";
           
            switch (status)
            {
                case ClsEnums.StatusSetting.YssAdd:
                    foreach (object pojo in pojoList)
                    {
                        setAddOperPojoInfo((BasePojo)pojo);
                    }

                    operResult = erParaService.updateById(pojoList);
                    break;
                case ClsEnums.StatusSetting.YssCopy:
                    foreach (object pojo in pojoList)
                    {
                        setAddOperPojoInfo((BasePojo)pojo);
                    }

                    operResult = erParaService.updateById(pojoList);
                    break;
                case ClsEnums.StatusSetting.YssEdit:
                    foreach (object pojo in pojoList)
                    {
                        setEditOperPojoInfo((BasePojo)pojo);
                    }

                    operResult = erParaService.updateById(pojoList);
                    break;
                case ClsEnums.StatusSetting.YssDel:
                    foreach (object pojo in pojoList)
                    {
                        setDelOperPojoInfo((BasePojo)pojo);
                    }

                    operResult = erParaService.deleteById(pojoList);
                    break;
                case ClsEnums.StatusSetting.YssAudit:
                    foreach (object pojo in pojoList)
                    {
                        setAuditOperPojoInfo((AuditableParamPojo)pojo);

                    }

                    DataFunction.setAuditStateByOperState(pojoList, status);
                    operResult = erParaService.auditById(pojoList);
                    break;
                case ClsEnums.StatusSetting.YssUnAudit:
                    foreach (object pojo in pojoList)
                    {
                        setAuditOperPojoInfo((AuditableParamPojo)pojo);
                    }

                    DataFunction.setAuditStateByOperState(pojoList, status);
                    operResult = erParaService.unAuditById(pojoList);
                    break;
                case ClsEnums.StatusSetting.YssBrow:
                    break;
            }

            return operResult;
        }

        private void Frm_Mr_Info_S_Load(object sender, EventArgs e)
        {
            if (status == ClsEnums.StatusSetting.YssAdd)
            {
                showInfoMVC(null);
            }
        }
    }
}
