using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.Context.Events;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Core.Exceptions;


using FAST.Core.Context;

using FAST.Core.Resource;
using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using YssInformation.Support.Fun;
////using YssBaseCls.Fun;










namespace YssInformation.Bi.CuryPair.Form
{
    /// <summary>
    /// 功能简介：货币对信息浏览界面
    /// 创建版本：V4.5.0.1
    /// 创建人： caozhonghu
    /// 创建日期： 2011.03.07
    /// =================================== 
    /// 创建版本：V4.5.0.2
    /// 创建人： zhuangyuchen
    /// 创建日期： 2011.03.09
    /// 修改简介：代码的实现
    /// </summary>
    public partial class Frm_CURY_PAIR_HBD_L : FrmBaseList
    {
        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_CURY_PAIR_HBD_L()
        {
            this.bUseMVCService = true;
            InitializeComponent();
        }

        /// <summary>
        /// 窗体初始加载时，拼装初始查询条件.
        ///zhuangyuchen.
        ///2011-3-9.
        ///修改简介：.
        ///代码的实现.
        /// </summary>
        /// <returns> 返回查询条件.</returns>
        public override string yssInitQuery()
        {
            // 所有提供的参数项如下，只需要设置子类需要的项即可

            // 1.拼装查询条件证券品种代码为货币对的
            string cond = "";

            //// 2.设置只显示列头属性值为true
            IsOnlyHeder = true;

            //// 3 初始只加载列头，若需要则设为true，反之不需要设置此参数

            return cond;
        }

        /// <summary>
        /// 获取list查询条件区的查询条件.
        /// zhuagnyuchen.
        /// 2011-3-9.
        /// 修改简介：代码的实现.
        /// zhuangyuchen
        /// 2011-4-20 
        /// bug单号 1705
        /// </summary>
        /// <returns>返回查询条件.</returns>
        public string yssGetListCond()
        {
            string cond = "";
            ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();
            ////如果基准货币不为空
            if (this.cboBasicCury.Value != null && this.cboBasicCury.Value.ToString().Length >= 0)
            {
                if (this.cboBasicCury.Value.ToString().IndexOf("|") > 0)
                {
                    ////根据|分割
                    string[] basicCury = this.cboBasicCury.Value.ToString().Split('|');
                    ////转换为查询字符串
                    //// 定义临时变量 strTemp
                    string strTemp = "";
                    for (int i = 0; i < basicCury.Length; i++)
                    {
                        strTemp += "'" + basicCury[i] + "'" + ",";
                    }

                    ////cond += " and a.C_DC_CODE_MARK in (" + strTemp.Substring(0, strTemp.Length - 1) + ")";
                    quyStrUtil.addQuyCon("C_DC_CODE_MARK", strTemp.Substring(0, strTemp.Length - 1), ClsConstant.SQL_RA_HYPHEN_IN);
                }
                else
                {
                    ////cond += " and a.C_DC_CODE_MARK='" + this.cboBasicCury.Value.ToString() + "'";
                    ////quyStrUtil.addQuyCon("C_DC_CODE_MARK", cboBasicCury, "=");
                    ////刘良 2012-4-10
                    quyStrUtil.addQuyCon("C_DC_CODE_MARK", cboBasicCury.Value, "=");
                }

            }

            ////如果计价货币不为空
            if (this.cboValCury.Value != null && this.cboValCury.Value.ToString().Length >= 0)
            {
                if (this.cboValCury.Value.ToString().IndexOf('|') > 0)
                {
                    string[] valCury = this.cboValCury.Value.ToString().Split('|');
                    string strTmp = "";
                    for (int i = 0; i < valCury.Length; i++)
                    {
                        strTmp += "'" + valCury[i] + "'" + ",";
                    }

                    ////cond += "and a.C_DC_CODE_PRICE in (" + strTmp.Substring(0, strTmp.Length - 1) + ")";
                    quyStrUtil.addQuyCon("C_DC_CODE_PRICE", strTmp.Substring(0, strTmp.Length - 1), ClsConstant.SQL_RA_HYPHEN_IN);
                }
                else
                {
                    ////cond += " and a.C_DC_CODE_PRICE = '" + this.cboValCury.Value.ToString() + "'";
                    ////quyStrUtil.addQuyCon("C_DC_CODE_PRICE", cboValCury, "=");
                    ////2012-4-10
                    quyStrUtil.addQuyCon("C_DC_CODE_PRICE", cboValCury.Value, "=");
                }

            }

            cond = quyStrUtil.getQuyStr(_formFun.C_FUN_CODE);
            return cond;

        }

        /// <summary>
        /// 封装条件对象
        /// </summary>
        /// <param name="paraDict">条件对象</param>
        /// <returns>条件对象</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            string basicCury = "";
            string valCury = "";

            if (this.cboBasicCury.Value != null && this.cboBasicCury.Value.Trim().Length >= 0)
            {
                if (this.cboBasicCury.Value.ToString().IndexOf("|") > 0)
                {
                    basicCury = this.cboBasicCury.Value.Replace("|", ",");
                }
                else
                {
                    basicCury = this.cboBasicCury.Value.Trim();
                }

                paraDict.Add("ARRAY_C_DC_CODE_MARK", basicCury);
            }

            ////如果计价货币不为空
            if (this.cboValCury.Value != null && this.cboValCury.Value.ToString().Length >= 0)
            {
                if (this.cboValCury.Value.ToString().IndexOf('|') > 0)
                {
                    valCury = this.cboValCury.Value.Replace("|", ",");
                }
                else
                {
                    valCury = this.cboValCury.Value.Trim();
                }

                paraDict.Add("ARRAY_C_DC_CODE_PRICE", valCury);
            }

            return paraDict;
        }

        /// <summary>
        /// 基准货币下拉框点击之前事件，处理获取当前计价货币下的计价货币代码.
        /// 修改人：liuping.
        /// 修改时间：2011-03-18           BUG #1488 货币对设置BUG.
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboBasicCury_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            try
            {
                if (this.cboValCury.Value != null && this.cboValCury.Value.Length > 0)
                {
                    string s = "";
                    if (this.cboValCury.Value.IndexOf("|") > 0)
                    {
                        s = parseCode(this.cboValCury.Value);
                    }
                    else
                    {
                        s = this.cboValCury.Value;
                    }

                    this.cboBasicCury.QueryCond = " where a.C_DC_CODE not in ('" + s + "')";
                    this.cboBasicCury.RequestEveryTime = true;
                }
                else
                {
                    this.cboBasicCury.QueryCond = "";
                    this.cboBasicCury.RequestEveryTime = true;
                }

            }
            catch (Exception ex)
            {
                ClsBaseException.DiscardException(ex);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, ClsEnums.StatusSetting.YssSave));
            }
        }


        /// <summary>
        /// 计价货币下拉框点击之前事件，处理获取当前基准货币下的基准货币代码.
        /// 修改人：liuping.
        /// 修改时间：2011-03-18       BUG #1488 货币对设置BUG.
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboValCury_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            try
            {
                if (this.cboBasicCury.Value != null && this.cboBasicCury.Value.Length > 0)
                {
                    string s = "";
                    if (this.cboBasicCury.Value.IndexOf("|") > 0)
                    {
                        s = parseCode(this.cboBasicCury.Value);
                    }
                    else
                    {
                        s = this.cboBasicCury.Value;
                    }

                    this.cboValCury.QueryCond = " where a.C_DC_CODE not in ( '" + s + "')";
                    this.cboValCury.RequestEveryTime = true;
                }
                else
                {
                    this.cboValCury.QueryCond = "";
                    this.cboValCury.RequestEveryTime = true;
                }
            }
            catch (Exception ex)
            {
                ClsBaseException.DiscardException(ex);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("002", _formFun, ClsEnums.StatusSetting.YssSave));
            }
        }

        /// <summary>
        /// 将基准货币代码或计价货币代码转换为sql需要的查询条件.
        /// 修改人：liuping.
        /// 修改时间：2011-03-18.
        /// </summary>
        /// <param name="value">出入值.</param>
        /// <returns>返回字符串.</returns>
        private string parseCode(string value)
        {
            string stemp = "";
            string[] s = null;
            if (value.IndexOf("|") > 0)
            {
                s = value.Split('|');

                if (s.Length > 0)
                {
                    for (int i = 0; i < s.Length; i++)
                    {
                        stemp += s[i] + "','";
                    }
                }
            }

            if (stemp != null && stemp.Length > 0)
            {
                stemp = stemp.Substring(0, stemp.Length - 3);
            }

            return stemp;
        }

        /// <summary>
        /// 基准货币下拉框刷新事件之前，处理获取当前计价货币下的计价货币代码.
        /// 修改人：liuping.
        /// 修改时间：2011-03-18         BUG #1488 货币对设置BUG.
        /// </summary>
        /// <param name="sender">sender.</param>
        /// <param name="e">e.</param>
        private void cboBasicCury_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            try
            {
                if (this.cboValCury.Value != null && this.cboValCury.Value.Length > 0)
                {
                    string s = "";
                    if (this.cboValCury.Value.IndexOf("|") > 0)
                    {
                        s = parseCode(this.cboValCury.Value);
                    }
                    else
                    {
                        s = this.cboValCury.Value;
                    }

                    this.cboBasicCury.QueryCond = " where a.C_DC_CODE not in ('" + s + "')";
                    this.cboBasicCury.RequestEveryTime = false;
                }
                else
                {
                    this.cboBasicCury.RequestEveryTime = true;
                }

            }
            catch (Exception ex)
            {
                ClsBaseException.DiscardException(ex);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, ClsEnums.StatusSetting.YssSave));
            }
        }

        /// <summary>
        /// 计价货币下拉框刷新事件之前，处理获取当前基准货币下的基准货币代码.
        /// 修改人：liuping.
        /// 修改时间：2011-03-18          BUG #1488 货币对设置BUG.
        /// </summary>
        /// <param name="sender">sender.</param>
        /// <param name="e">e.</param>
        private void cboValCury_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            try
            {
                if (this.cboBasicCury.Value != null && this.cboBasicCury.Value.Length > 0)
                {
                    string s = "";
                    if (this.cboBasicCury.Value.IndexOf("|") > 0)
                    {
                        s = parseCode(this.cboBasicCury.Value);
                    }
                    else
                    {
                        s = this.cboBasicCury.Value;
                    }

                    this.cboValCury.QueryCond = " where a.C_DC_CODE not in ( '" + s + "')";
                    this.cboValCury.RequestEveryTime = false;
                }
                else
                {
                    this.cboValCury.RequestEveryTime = true;
                }
            }
            catch (Exception ex)
            {
                ClsBaseException.DiscardException(ex);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("002", _formFun, ClsEnums.StatusSetting.YssSave));
            }
        }

        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_CURY_PAIR_HBD_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.dataService = ServiceFactory.createService(serviceType) as IServiceBus;
        }
    }
}


