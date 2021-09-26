using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.Context.Events;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Core.Exceptions;
using FAST.Core.Context;
using FAST.Platform.Right.Fun;
using FAST.Core.BaseControl.Fun;
using FAST.Core.Resource;
using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;
using FAST.Core.Bussiness.Form;
////using YssBaseCls.Pojo;
using FAST.Common.Service.Pojo.Base;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.BaseControl;
using FAST.Common.Service.Services;
using Yss.KMessage;
using Yss.KRichEx.AutoFilter.Model;
using FAST.Core.CRUD.Interface;
using YssInformation.Support.Bi.AssociationOrgan.Pojo;
using YssInformation.Support.Bi.AssociationOrgan.Service;
using YssInformation.Bi.AssociationOrgan.Form;
using YssInformation.Support.Bi.Account.Pojo;
using YssInformation.Support.Bi.Account.Service;
using YssInformation.Support.Fun;
using Newtonsoft.Json.Linq;
using Newtonsoft.Json;
using FAST.Core.BaseControl.Pojo;
using YssProductInfo.Support.Ab.Port.Service;
using FAST.Core.Communication.DataService;
using FAST.Common.Service.DataService.Base;
using YssInformation.Support.Bi.AssociationOrgan.Cache;
using FAST.Core.Cache;
using System.Collections;
////using YssPara.Pojo.Bi;
////using YssPara.Service.Bi;
////using YssPara.Form.Bi;


namespace YssInformation.Bi.Account.Form
{
    /// <summary>
    /// 功能简介：公用账户信息设置界面处理
    /// 创建人：chenyoulong
    /// 创建日期：20121105
    /// 发布版本：v1.0.0.4
    /// </summary>
    public partial class Frm_FUND_ACC_S : FrmBaseSet
    {
        /// <summary>
        /// 声明资金账户信息service对象
        /// </summary>
        private IFundAccService fundAccService = null;

        /// <summary>
        /// 标识符-是否在综合指令设置界面打开
        /// </summary>
        private bool openByOthers = false;

        /// <summary>
        /// 开户行 用于判断是否开户行文本框是否被修改
        /// </summary>
        private string cOpenAddrStr = "";

        /// <summary>
        /// BUG #187667 支付参数“账户是否二次录入”参数值的维护需优化
        /// 用于作用是否开启二次校验组合信息
        /// </summary>
        private string checkAccInfo = "";

        /// <summary>
        /// 复核校验弹窗
        /// </summary>
        private Frm_FUND_ACC_CHECK_S reCheckSet = null;

        /// <summary>
        /// STORY #35491 南方基金-收方账户支持在支付自动划款指令界面新增,用于修改的id
        /// </summary>
        private string id = "";

        
        /// <summary>
        /// BUG #267458 【30.7UI】--账户信息设置修改账户时，机构不能进行查询，加载也很慢
        /// </summary>
        private int refCount = 1;

        /// <summary>
        /// STORY #41401 产品信息-产品账户设置，批量关联账户 ,用于保存新增账户信息
        /// </summary>
        private FundAcc pojo = null;

        /// <summary>
        /// frmlist
        /// </summary>
        private Frm_FUND_ACC_L frmlist = null;

        /// <summary>
        /// 选择弹窗
        /// </summary>
        private Frm_PORT_LIST_S chooseSet = null;

        /// <summary>
        /// 组合列表
        /// </summary>
        private string protCodes = string.Empty;

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_FUND_ACC_S()
        {
            InitializeComponent();
            this.bUseMVCService = true;
            ////this.cboOrgan.BeforeDropDownClick += new Yss.KRichEx.AutoFilter.Events.DropDownEventHandler(this.cboOrgan_BeforeDropDownClick);
            ////string strIncrement = IniFileOperator.ReadIniData("CmdFUNDACC", "BankSort", "False", System.Windows.Forms.Application.StartupPath + @"\config\app.ini");
            ////if ("True".Equals(strIncrement))
            ////{
                ////this.exchangeControlPosition();
            ////}
        }

        /// <summary>
        /// 资金账户信息设置浏览界面装载事件
        /// 此处用于在窗体装载的时候初始化公用划款账户服务对象（IPubAccService）
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_FUND_ACC_S_Load(object sender, EventArgs e)
        {
            ////Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            ////this.fundAccService = ServiceFactory.createService(serviceType) as IFundAccUnifyPayService;
            ////STORY #35130 招商基金-删除账户信息时如果此账户已被用则需弹出提示框
            ////this.autoHkzlUnifyPayService = ServiceFactory.createService<IAutoHkzlUnifyPayService>();

            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            dataService = (IServiceBus)ServiceFactory.createService(serviceType);

            //// STORY #40860 南方基金-“支付产品账户信息”支持全角输入
            this.txtOpenName.ForbiddenSBC = false;
            this.txtOpenAddr.ForbiddenSBC = false;
            if (status == ClsEnums.StatusSetting.YssAdd || status == ClsEnums.StatusSetting.YssEdit)
            {
                this.cboPort.Value = null;
            } 
            
            //// STORY #28923 添加产品账户的时候将人民币设置为默认
            if (status == ClsEnums.StatusSetting.YssAdd)
            {
                this.cboCury.Value = "CNY";
            }

            IFundAccService fundAccService = ServiceFactory.createService<IFundAccService>();

            string prop = fundAccService.readProperty();
            if (!string.IsNullOrEmpty(prop))
            {
                this.checkAccInfo = prop;
            }

            if (status != ClsEnums.StatusSetting.YssBrow)
            {
                if (this.frmBaseViewList is Frm_FUND_ACC_L)
                {
                    frmlist = this.frmBaseViewList as Frm_FUND_ACC_L;
                    if (!string.IsNullOrEmpty(frmlist.payType) && frmlist.payType.Contains("CKTZ_"))
                    {
                        this.cboAccType.Value = "CPZH_CKH";
                        if (!string.IsNullOrEmpty(frmlist.portName))
                        {
                            this.txtOpenName.Value = frmlist.portName;
                        }
                    }
                }
            }

            addButton();
        }

        /// <summary>
        /// 添加按钮
        /// </summary>
        private void addButton()
        {
            ClsButtonInfo btnCheckAccInfoConfirm = new ClsButtonInfo();
            btnCheckAccInfoConfirm.Image = new Bitmap(FAST.Resource.Resource.TMConfirm, 24, 24);
            btnCheckAccInfoConfirm.Text = "信息确认";
            btnCheckAccInfoConfirm.Name = "btnCheckAccInfoConfirm";
            btnCheckAccInfoConfirm.Tooltip = "信息确认";
            btnCheckAccInfoConfirm.ClickEvent = btnCheckAccInfoConfirm_Click;
            this.btnBar.addButton(btnCheckAccInfoConfirm, 6);
            btnBar.setButtonVisable("btnCheckAccInfoConfirm", false);
        }

        /// <summary> 
        /// 修改二次校验参数控制
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected void btnCheckAccInfoConfirm_Click(object sender, EventArgs e)
        {
            reCheckSet = new Frm_FUND_ACC_CHECK_S();
            SysFun cls_FUN = null;
            if (ClsContext.sysFunHash.ContainsKey("fundAccInfo"))
            {
                cls_FUN = (SysFun)ClsContext.sysFunHash["fundAccInfo"];
            }

            reCheckSet.YssFormMenu = cls_FUN;
            reCheckSet.frmFundAccS = this;
            reCheckSet.fundAcc = this.faceInfoToObjMVC() as FundAcc;
            reCheckSet.initControlStat();
            changeZt(this);
            if ("1".Equals(this.checkAccInfo))
            {
                ////reCheckSet.Show(this);
                reCheckSet.ShowDialog();
            }

            btnBar.setButtonVisable(ClsButtonName.BtnSave, false);
            ////btnBar.setButtonDisabled("btnCheckAccInfoConfirm");
        }

        /// <summary>
        /// 初始化控件
        /// </summary>
        public override void yssInitCtlAttr()
        {
            this.dtpEnd.setDateTime(Convert.ToDateTime("9998-12-31"));
            Port port = (this.frmBaseViewList as Frm_FUND_ACC_L)._mainDataPojo as Port;
            if (this.frmBaseViewList is Frm_FUND_ACC_L && port != null)
                {
                    if (status == ClsEnums.StatusSetting.YssAdd || status == ClsEnums.StatusSetting.YssCopy)
                    {
                        this.selPort.Value = port.C_PORT_CODE;
                        string d_BUILD = port.D_BUILD;
                        
                        if (d_BUILD == null || d_BUILD.Equals(""))
                        {
                            yssDateStart.setDateTime(Convert.ToDateTime("1900-01-01"));
                        }
                        else
                        {
                            yssDateStart.setDateTime(Convert.ToDateTime(d_BUILD));
                        }

                        yssDateEnd.setDateTime(Convert.ToDateTime("9998-12-31"));
                    }

                    if (status == ClsEnums.StatusSetting.YssEdit || status == ClsEnums.StatusSetting.YssBrow)
                    {
                        this.selPort.Value = port.C_PORT_CODE;
                        FundAcc fundAcc = (FundAcc)this.yssGetBaseSelTypeItemMVC();
                        if (fundAccService == null)
                        {
                            fundAccService = ServiceFactory.createService<IFundAccService>();
                        }

                        ////根据组合代码，账户id获取关联信息中的开始时间，结束时间
                       string qstime = fundAccService.getTimeByRelaPort(this.selPort.Value, fundAcc.Id);
                       if (qstime != null && qstime != "") 
                       {
                           string[] time = qstime.Split(',');
                           yssDateStart.setDateTime(Convert.ToDateTime(time[0]));
                           yssDateEnd.setDateTime(Convert.ToDateTime(time[1]));

                       }
                       else
                       {
                           yssDateStart.setDateTime(Convert.ToDateTime("1900-01-01"));
                           yssDateEnd.setDateTime(Convert.ToDateTime("9998-12-31"));
                       }
                    }
               }
            else
            {
                if (this.row13.Visible && this.row19.Visible) 
                {
                    this.row13.Visible = false;
                    this.row19.Visible = false;
                    this.Height -= this.row19.Height;
                    this.Height -= this.row13.Height;
                }
            }
        }

        /// <summary>
        /// initServiceMVC
        /// </summary>
        protected override void initServiceMVC()
        {
            if (dataService == null)
            {
                ////没有配置信息，先主动解析一次。龚金枝 20160504
                if (_formFun.YssAssocia == null)
                {
                    _formFun.YssAssocia = ClsClzCfgMgr.getAssociaParam(_formFun.C_FUN_CODE);
                }

                Type svcType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
                this.fundAccService = ServiceFactory.createService(svcType) as IFundAccService;
                this.dataService = this.fundAccService;

            }
        }

        /// <summary>
        /// 封装窗体数据到对象
        /// </summary>
        /// <returns>Pojo</returns>
        public override AuditableParamPojo faceInfoToObjMVC()
        {
            FundAcc fundAcc = null;
            try
            {
                fundAcc = new FundAcc();
                //// STORY #41401 产品信息-产品账户设置，批量关联账户
                ////BUG #170368 【Trunk21.5.1.2】新增“支付产品账户信息”时，二次输入信息后保存报错。
                ////fundAcc.C_PORT_CODE = this.cboPort.Value == null ? "" : this.cboPort.Value;//// 空值时保存空字符串

                if (!string.IsNullOrEmpty(this.txtOpenName.Text))
                {
                    fundAcc.C_OPEN_ACC_NAME = this.txtOpenName.Text.Trim(); ////  开户名称
                }

                if (!string.IsNullOrEmpty(this.txtOpenNo.Text))
                {
                    fundAcc.C_OPEN_ACC_NO = this.txtOpenNo.Text.Trim(); ////   开户账号
                }

                if (!string.IsNullOrEmpty(this.txtOpenAddr.Text))
                {
                    fundAcc.C_OPEN_ADDR = this.txtOpenAddr.Text.Trim(); ////   开户行
                }

                if (!string.IsNullOrEmpty(this.txtOrgCode.Text))
                {
                    fundAcc.C_OPEN_JC = this.txtOrgCode.Text.Trim(); ////   开户行简称
                }

                string tempStr = this.txtOpenNo.Text;
                while (tempStr.Trim().Length < 6)
                {
                    tempStr = "0" + tempStr;
                }

                fundAcc.C_CA_COMBO_CODE = this.txtOrgCode.Text + "-" + tempStr.Substring(tempStr.Length - 6);

                fundAcc.C_DC_CODE = this.cboCury.Value; //// 币种
                fundAcc.C_ACCOUNT_TYPE = this.cboAccType.Value == null ? " " : this.cboAccType.Value; //// 账户类型
                fundAcc.C_ORG_CODE = this.cboOrgan.Value == null ? "" : this.cboOrgan.Value; //// 空值时保存空字符串
                fundAcc.C_HOLDER = this.cboOrgan1.Value == null ? "" : this.cboOrgan1.Value;  ////所有人
                fundAcc.C_INTER_ORG_CODE = this.cboOrgan2.Value == null ? "" : this.cboOrgan2.Value; ////中间行
                //// STORY #41321 支付产品账户信息增加省份和城市字段
                fundAcc.C_PROVINCE = this.cboProvince.Value == null ? "" : this.cboProvince.Value;
                fundAcc.C_CITY = this.cboCity.Value == null ? "" : this.cboCity.Value;
                fundAcc.C_BANK_ADDR = this.txtBankAddr.Text.Trim(); ////开户地址
                fundAcc.C_RUNNING_ACC = this.txtRunningAcc.Text.Trim(); //// 流水账户
                if (!string.IsNullOrEmpty(this.txtPayNum.Text))
                {
                    fundAcc.C_PAY_CODE = this.txtPayNum.Text.Trim(); //// 大额支付号
                }

                fundAcc.C_OPEN_MODE = this.cboOpenModel.Value == null ? "" : this.cboOpenModel.Value;    ////开户方式
                fundAcc.C_CNX = this.txtCNX.Text.Trim(); //// 虚拟号
                fundAcc.C_BC_ORG_CODE = this.txtBCOrgCode.Text.Trim(); //// 中行机构号
                fundAcc.C_BC_LINK_NO = this.txtBCLinkNO.Text.Trim(); //// 中行联行号
                //// STORY #67960 【交银施罗德】_支付产品账户信息界面新增时，可以维护备注信息
                fundAcc.C_DESC = this.txtDesc.Text;

                //// BUG #141178 南方基金-支付账户信息修改时，无法将所有人修改为空 
                fundAcc.C_CA_CODE = this.cboCa.Value == null ? "" : this.cboCa.Value; ////现金账户
                fundAcc.Modifier = ClsContext.currentUser.C_USER_CODE;
                fundAcc.ModifyDate = DateTime.Now.ToString("yyyyMMdd hh:mm:ss");
                //// BUG #141178 南方基金-支付账户信息修改时，无法将所有人修改为空 
                fundAcc.C_ASS_CODE = this.txtAssCode.Text; //// 资产代码
                // 开户日期 关户日期 
                fundAcc.D_BEGIN = this.dtpBegin.getBeginDate;
                fundAcc.D_END = this.dtpEnd.getBeginDate;
                fundAcc.C_HAVEUSED = "0"; ////(新增)账号默认未使用过
                ////BUG #230178 【21.6】【易方达基金】产品账户信息新增保存报错
                fundAcc.C_DISCARD_STATUS = "NORMAL_ACC";
                ////20160219 zhaoguanchao STORY #28868 华泰证券-直销业务清算指令管理
                //// STORY #41401 产品信息-产品账户设置，批量关联账户 ,只有当账户类型为托管户时（组合是单选），才将组合代码保存到 T_C_CP_FUND_ACC
                fundAcc.C_PAYMENT_KEY = this.txtPayMentKey.Text;
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return fundAcc;
        }

        /// <summary>
        /// 修改保存是校验
        /// </summary>
        /// <returns>1</returns>
        protected override bool checkInput()
        {
            if (this.dtpBegin.getBeginDate.CompareTo(this.dtpEnd.getBeginDate) > 0)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, status));
                return false;
            }

            //// 框架校验
            bool result = base.checkInput();
            if (!result)
            {
                return result;
            }

            //// 账户类型修改为托管户，如果关联了多个组合，则不能保存成功，一个托管户只能关联一个组合
            if (!string.IsNullOrEmpty(this.cboAccType.Value) && "CPZH_TGH" == this.cboAccType.Value && status == ClsEnums.StatusSetting.YssEdit)
            {
                IPortService unifypayParamService = ServiceFactory.createService<IPortService>();
                string param = unifypayParamService.queryByParaCode("TGH_RELA_MULTI_PORT");
                if (!"1".Equals(param))
                {
                    result = checkPortCntByTgh();
                    if (!result)
                    {
                        new MessageDialog().Show("托管账户只能关联一个产品组合，请检查！", "提示", MessageBoxButtons.OK);
                        return false;
                    }
                }
            }

            if (!string.IsNullOrEmpty(this.cboAccType.Value) && (this.cboAccType.Value.Contains("CPZH_TGH") || this.cboAccType.Value.Contains("CPZH_TGH_SEC")) && this.cboAccType.Value.Contains("|"))
            {
                new MessageDialog().Show("托管户不能维护多个账户类型！请检查！", "系统提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
                return false;
            }

            if (!string.IsNullOrEmpty(this.txtPayNum.Text))
            {
                string dezfh = this.txtPayNum.Text.Trim(); //// 大额支付号
                if (dezfh.Length != 12 || !isnumeric(dezfh))
                {
                    new MessageDialog().Show("大额支付号非12位数字，请检查！", "系统提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    return false;
                }               
            }

            return result;
        }

        /// <summary>
        /// 判断参数字符串是不是纯数字
        /// </summary>
        /// <param name="str">str</param>
        /// <returns>bool</returns>
        public bool isnumeric(string str)
        {
            char[] ch = new char[str.Length];
            ch = str.ToCharArray();
            for (int i = 0; i < ch.Length; i++)
            {
                if (ch[i] < 48 || ch[i] > 57)
                {
                    return false;
                }    
            }

            return true;
        }

        /// <summary>
        /// 修改时，当账户类型是托管户，校验关联账户的个数
        /// </summary>
        /// <returns>bool</returns>
        private bool checkPortCntByTgh()
        {
            bool result = true;
            FundAcc fundAcc = (FundAcc)this.yssGetBaseSelTypeItemMVC();
            if (fundAcc != null && !string.IsNullOrEmpty(fundAcc.Id))
            {
                string ports = fundAccService.getPortsByRelaCode(fundAcc.Id);
                if (!string.IsNullOrEmpty(ports) && ports.Contains(","))
                {
                    result = false;
                }
            }

            return result;
        }

        /// <summary>
        /// 展示对象到窗体
        /// </summary>
        /// <param name="pojo">pojo</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            try
            {
                FundAcc fundAcc = (FundAcc)this.yssGetBaseSelTypeItemMVC();
                if (fundAcc == null)
                {
                    return;
                }

                if (status != ClsEnums.StatusSetting.YssAdd || status != ClsEnums.StatusSetting.YssEdit)
                {
                    this.cboPort.Value = fundAcc.C_PORT_CODE;
                } 

               
                this.txtOrgCode.Text = fundAcc.C_OPEN_JC;
                this.txtOpenName.Text = fundAcc.C_OPEN_ACC_NAME;
                this.txtOpenNo.Text = fundAcc.C_OPEN_ACC_NO;
                this.txtOpenAddr.Text = fundAcc.C_OPEN_ADDR;
                this.cboCury.Value = fundAcc.C_DC_CODE;
                this.cboOrgan.Value = fundAcc.C_ORG_CODE;
                if (this.cboOrgan.SelectedItem != null)
                {
                    ////BUG #145305 南方基金-保存账户信息报错，大额支付号无法带出，无法反写入机构信息
                    if (this.cboOrgan.SelectedItem.DataEntity != null)
                    {
                        Org org = (Org)this.cboOrgan.SelectedItem.DataEntity;
                    }
                }
                ////STORY #36787 南方基金-综合指令界面新增收方账户不插入机构信息
                ////if (!string.IsNullOrEmpty(fundAcc.C_PAY_CODE))
                ////{
                this.txtPayNum.Text = fundAcc.C_PAY_CODE; ////大额支付号
                ////}             

                this.cboOrgan1.Value = fundAcc.C_HOLDER;
                this.cboOrgan2.Value = fundAcc.C_INTER_ORG_CODE;
                this.dtpBegin.setDateTime(fundAcc.D_BEGIN);
                this.dtpEnd.setDateTime(fundAcc.D_END);
                this.txtAssCode.Text = fundAcc.C_ASS_CODE;
                if (!string.IsNullOrEmpty(fundAcc.C_ACCOUNT_TYPE) && fundAcc.C_ACCOUNT_TYPE.Contains("|"))
                {
                    this.cboAccType.QueryByValues = false;
                }
                else
                {
                    this.cboAccType.QueryByValues = true;
                }

                ////20160219 zhaoguanchao STORY #28868 华泰证券-直销业务清算指令管理
                this.cboAccType.Value = fundAcc.C_ACCOUNT_TYPE;

                ////STORY #34271 招商基金-开户行与开户机构合并 20160905 zhouning_cs
                cOpenAddrStr = this.txtOpenAddr.Text;

                //// STORY #41321 支付产品账户信息增加省份和城市字段
                this.cboProvince.Value = fundAcc.C_PROVINCE;
                this.cboCity.Value = fundAcc.C_CITY;
                this.cboCa.Value = fundAcc.C_CA_CODE; ////现金账户
                this.txtBankAddr.Text = fundAcc.C_BANK_ADDR; ////开户地址
                this.txtRunningAcc.Text = fundAcc.C_RUNNING_ACC; ////流水账户
                this.cboOpenModel.Value = fundAcc.C_OPEN_MODE; ////开户方式
                this.txtCNX.Text = fundAcc.C_CNX; ////虚拟号
                this.txtBCOrgCode.Text = fundAcc.C_BC_ORG_CODE; ////中行机构号
                this.txtBCLinkNO.Text = fundAcc.C_BC_LINK_NO; ////中行联行号
                //// STORY #67960 【交银施罗德】_支付产品账户信息界面新增时，可以维护备注信息
                this.txtDesc.Text = fundAcc.C_DESC;

                ////BUG #174483 对接风控系统，导入定存数据时导入的账户信息，前台显示有误
                ////this.cboOrgan.YssReadOnly = true;
                this.txtPayMentKey.Text = fundAcc.C_PAYMENT_KEY;
                ////this.cboPort.Value = fundAcc.C_PORT_CODE;

            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 货币值改变
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">sender</param>
        private void cboCury_SelectedValueChanged(object sender, EventArgs e)
        {
            if (this.frmBaseViewList != null && this.frmBaseViewList is IFormDetailData)
            {
                IFormDetailData frmDetailData = this.frmBaseViewList as IFormDetailData;
                if (frmDetailData.MainDataPojo != null)
                {
                    return;
                }
            }

            this.cboCa.Value = null;
            this.cboCa.Text = null;
            this.cboCa.Items.Clear();

            getCacode();
        }

        /// <summary>
        /// STORY #41401 产品信息-产品账户设置，批量关联账户
        /// 账户类型下拉框值改变事件
        /// 当账户类型为托管户时，组合为单选；否则为多选
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboAccType_SelectedValueChanged(object sender, EventArgs e)
        {
            //// 先初始化组合多选
            ////this.cboPort.ShowCheckBox = true;
            ////if (this.cboAccType.Value != null && this.cboAccType.Value == "CPZH_TGH")
            ////{
            ////    this.cboPort.ShowCheckBox = false;
            ////}
        }

        /// <summary>
        /// txtPayNum_LostFocus
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void txtPayNum_LostFocus(object sender, EventArgs e)
        {
            if ((status == ClsEnums.StatusSetting.YssAdd || status == ClsEnums.StatusSetting.YssEdit
                || status == ClsEnums.StatusSetting.YssCopy))
            {
                this.txtPayNum.Text = this.txtPayNum.Text.Trim();
            }
        }
        ////  /// <summary>
        /////// 账户代码
        /////// </summary>
        /////// <param name="sender">sender</param>
        /////// <param name="e">sender</param>
        ////private void cboCa_BeforeDropDownClick(object sender, EventArgs e) 
        ////{
        ////    cboCa_YssOnBeforeLoadData(null,null);
        ////}



        /// <summary>
        /// 账户代码下拉框加载之前事件，用于处理账户代码加载
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboCa_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            string port = "";
            if (null == this.cboCury.Value)
            {
                ////BaseInfoUtil.setCaMethod(sender, /*this.cboPort.Value*/ "");
                if (null != frmBaseViewList && null != frmBaseViewList.pageTotalParaDic
                    && frmBaseViewList.pageTotalParaDic.ContainsKey("ARRAY_C_PORT_CODE"))
                {
                    Dictionary<string, string> paraDict = new Dictionary<string, string>();
                    paraDict = frmBaseViewList.pageTotalParaDic;
                    port = frmBaseViewList.pageTotalParaDic["ARRAY_C_PORT_CODE"];
                }

                BaseInfoUtil.setCaItem(e, port, "");
            }
            else
            {
                ////edit by weijj 20140615 BUG #94994
                e.IsCancel = true;
                if (null != frmBaseViewList && null != frmBaseViewList.pageTotalParaDic
                    && frmBaseViewList.pageTotalParaDic.ContainsKey("ARRAY_C_PORT_CODE"))
                {
                    Dictionary<string, string> paraDict = new Dictionary<string, string>();
                    paraDict = frmBaseViewList.pageTotalParaDic;
                    port = frmBaseViewList.pageTotalParaDic["ARRAY_C_PORT_CODE"];
                }

                BaseInfoUtil.setCaItem(e, port, "");
            }

        }

        /// <summary>
        /// 初始化控件状态
        /// </summary>
        public override void initControlStat()
        {
            base.initControlStat();

            if (status == FAST.Core.Context.ClsEnums.StatusSetting.YssAdd)
            {
                this.cboPort.YssReadOnly = false;
            }

            if (status == FAST.Core.Context.ClsEnums.StatusSetting.YssEdit)
            {
                this.cboPort.YssReadOnly = true;
            }
        }

        /// <summary>
        /// 锁定特殊控件的状态
        /// </summary>
        protected override void OnLockSpecialControlState()
        {
            base.OnLockSpecialControlState();

            ////List界面被关联内嵌至其他界面时，组合不能被更改
            if (this.frmBaseViewList != null && this.frmBaseViewList is IFormDetailData)
            {
                ////IFormDetailData frmDetailData = this.frmBaseViewList as IFormDetailData;
                ////if (frmDetailData.MainDataPojo is CashAcc)
                ////{
                ////    this.cboCa.YssReadOnly = true;
                ////    this.cboCa.Value = (frmDetailData.MainDataPojo as CashAcc).C_CA_CODE;
                ////}
                ////if (frmDetailData.MainDataPojo is Port)
                ////{
                ////    this.cboPort.YssReadOnly = true;
                ////    this.cboPort.Value = (frmDetailData.MainDataPojo as Port).C_PORT_CODE;
                ////}
                ////else if (frmDetailData.MainDataPojo is Org)
                ////{
                ////    if (status != ClsEnums.StatusSetting.YssBrow)
                ////    {
                ////        this.cboOrgan1.Value = (frmDetailData.MainDataPojo as Org).C_ORG_CODE;
                ////    }  
                ////}
            }
        }

        /// <summary>
        /// STORY #28923 添加产品账户的时候将人民币设置为默认
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnNew_Click(object sender, EventArgs e)
        {
            this.txtOpenAddr.YssReadOnly = false;
            base.btnNew_Click(sender, e);
            this.cboCury.Value = "CNY";
        }

        /// <summary>
        /// STORY #28923 添加产品账户的时候将人民币设置为默认
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnCopy_Click(object sender, EventArgs e)
        {
            this.txtOpenAddr.YssReadOnly = false;
            base.btnCopy_Click(sender, e);
        }

        /// <summary>
        /// 改变界面控件的状态
        /// </summary>
        /// <param name="set">set</param>
        public void changeZt(Frm_FUND_ACC_S set)
        {
            set.txtOpenNo.YssReadOnly = !set.txtOpenNo.YssReadOnly;
            set.txtOpenName.YssReadOnly = !set.txtOpenName.YssReadOnly;
            set.txtOpenAddr.YssReadOnly = !set.txtOpenAddr.YssReadOnly;
            set.txtAssCode.YssReadOnly = !set.txtAssCode.YssReadOnly;
            set.txtPayNum.YssReadOnly = !set.txtPayNum.YssReadOnly;
            set.cboAccType.YssReadOnly = !set.cboAccType.YssReadOnly;
            set.cboCury.YssReadOnly = !set.cboCury.YssReadOnly;
            set.cboOrgan.YssReadOnly = !set.cboOrgan.YssReadOnly;
            set.cboOrgan1.YssReadOnly = !set.cboOrgan1.YssReadOnly;
            set.cboOrgan2.YssReadOnly = !set.cboOrgan2.YssReadOnly;
            set.cboCa.YssReadOnly = !set.cboCa.YssReadOnly;
            //// bug ：点击保存确认时，set界面仍有一些控件可编辑
            set.cboProvince.YssReadOnly = !set.cboProvince.YssReadOnly;
            set.cboCity.YssReadOnly = !set.cboCity.YssReadOnly;

            set.txtBankAddr.YssReadOnly = !set.txtBankAddr.YssReadOnly; ////开户地址
            set.txtRunningAcc.YssReadOnly = !set.txtRunningAcc.YssReadOnly; ////流水账户
            set.cboOpenModel.YssReadOnly = !set.cboOpenModel.YssReadOnly; ////开户方式
            set.txtCNX.YssReadOnly = !set.txtCNX.YssReadOnly; ////虚拟号
            set.txtBCOrgCode.YssReadOnly = !set.txtBCOrgCode.YssReadOnly; ////中行机构号
            set.txtBCLinkNO.YssReadOnly = !set.txtBCLinkNO.YssReadOnly; ////中行联行号


            set.dtpBegin.ReadOnly = !set.dtpBegin.ReadOnly;
            set.dtpEnd.ReadOnly = !set.dtpEnd.ReadOnly;

        }

        /// <summary>
        /// 获取操作数据对象
        /// 默认审核
        /// </summary>
        /// <param name="pojoList">操作数据集合</param>
        /// <returns>操作对象</returns>
        protected ArrayList getDataObjMVC(ArrayList pojoList)
        {
            if (pojoList == null || pojoList.Count == 0)
            {
                FundAcc pojo = faceInfoToObjMVC() as FundAcc;

                pojoList = new ArrayList();
                if (null != pojo)
                {
                    ((ParamPojo)pojo).Modifier = ClsContext.currentUser.C_USER_CODE;
                    ((AuditableParamPojo)pojo).AuditState = (_formFun.N_CHECK == 1) ? 0 : 1; // 当功能窗体启用审核机制时新增状态为0 byleeyu20130403
                    ////当未开启审核状态时，这时是需要将审核人，审核时间填到POJO中去的byleeyu20130719
                    if (_formFun.N_CHECK <= 0)
                    {
                        ((AuditableParamPojo)pojo).OperUser = ClsContext.currentUser.C_USER_CODE;
                        ((AuditableParamPojo)pojo).AuditDate = ClsFunction.formatDateTime("yyyyMMdd HH:mm:ss", DateTime.Now.ToString());
                        ////((AuditableParamPojo)pojo).AuditState = '1';
                    }

                    pojoList.Add(pojo);
                }
            }

            return pojoList;
        }

        /// <summary>
        /// 重写保存方法，如果是从综合指令设置界面打开的，
        /// 则不显示审核反审核按钮，保存时数据为已审核，
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnSave_Click(object sender, EventArgs e)
        {
            try
            {
                ////STORY #94308 【招商基金】【0331】银行账户信息录入监控
                if (fundAccService == null)
                {
                    fundAccService = ServiceFactory.createService<IFundAccService>();
                }

                ////BUG #354781 产品账户信息界面不支持新增
                if (this.cboAccType.Value != null)
                {
                    List<string> strArr = this.cboAccType.Value.Split('|').ToList();
                    string id = "0";
                    if (this.status == ClsEnums.StatusSetting.YssEdit)
                    {
                        FundAcc fundAccObj = (FundAcc)this.yssGetBaseSelTypeItemMVC();
                        id = fundAccObj.Id;
                    }
                    ////BUG #367727 【南方基金】【20210228.0521】新增支付产品账户信息新增对于同一开户账户与同一开户行，且账户类型为存款账户保存报错
                    ////if (strArr.Contains("CPZH_CKH") &&
                    ////    (this.status == ClsEnums.StatusSetting.YssEdit || this.status == ClsEnums.StatusSetting.YssAdd
                    ////         || this.status == ClsEnums.StatusSetting.YssCopy))
                    ////{
                    ////    string flag = this.fundAccService.getAccListByOpenNoAndOpenAddr(this.txtOpenNo.Text, this.txtOpenAddr.Text, id);
                    ////    if ("true".Equals(flag))
                    ////    {
                    ////        string message = "开户账号与开户行已存在，请重新维护！";
                    ////        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo(message));
                    ////        return;
                    ////    }
                    ////}
                }

                ////对于可以选择继续保存的提示需要此方法里处理，不能放在checkInput()方法里，防止两次弹窗
                if (!string.IsNullOrEmpty(this.cboAccType.Value) && "CPZH_TGH".Equals(this.cboAccType.Value) && string.IsNullOrEmpty(this.cboCa.Value))
                {
                    if (new MessageDialog().Show("现金账户为空，是否保存该数据！", "系统提示", MessageBoxButtons.YesNo, MessageBoxIcon.Information) != DialogResult.Yes)
                    {
                        return;
                    }
                }

                string operResult = "";
                ArrayList pojoList = null;
                List<string> cidenList = null;
                ////STORY #40537 【加急】南方基金-增加划款指令账户信息不匹配时的系统提醒功能
                if (status == ClsEnums.StatusSetting.YssEdit && checkInput() && changeInfo() == false)
                {
                    if ("0".Equals(this.checkAccInfo))
                    {
                        base.btnSave_Click(sender, e);

                        ////同步修改组合关联账户的起止时间;
                        if (this.frmBaseViewList is Frm_FUND_ACC_L && (this.frmBaseViewList as Frm_FUND_ACC_L)._mainDataPojo != null)
                        {
                            string porCode = this.selPort.Value;
                            string startDate = this.yssDateStart.getBeginDateStr;
                            string endDate = this.yssDateEnd.getBeginDateStr;
                            FundAcc fundAcc = (FundAcc)this.yssGetBaseSelTypeItemMVC();
                            if (fundAccService == null)
                            {
                                fundAccService = ServiceFactory.createService<IFundAccService>();
                            }

                            if (porCode != "" && porCode != null)
                            {
                                Dictionary<string, string> dicts = new Dictionary<string, string>();
                                dicts.Add("portCode", porCode);
                                dicts.Add("id", fundAcc.Id);
                                dicts.Add("d_start", startDate);
                                dicts.Add("d_end", endDate);
                                ////根据组合代码，账户更新关联信息中的开始时间，结束时间
                                fundAccService.updateTimeByRelaPort(dicts);
                            }
                        }
                    }
                    else
                    {
                        ////new MessageDialog().Show("两次输入不一致！", "系统提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
                        return;
                    }
                }

                if ((status == ClsEnums.StatusSetting.YssAdd || status == ClsEnums.StatusSetting.YssCopy) && checkInput() && changeInfo() == false)
                {
                    if ("0".Equals(this.checkAccInfo))
                    {
                        FundAcc fundAcc = new FundAcc();
                        fundAcc.C_OPEN_ACC_NAME = this.txtOpenName.Text.Trim(); ////  开户名称
                        fundAcc.C_OPEN_ACC_NO = this.txtOpenNo.Text.Trim(); ////   开户账号
                        fundAcc.C_OPEN_ADDR = this.txtOpenAddr.Text.Trim(); ////   开户行
                        fundAcc.C_DC_CODE = this.cboCury.Value; //// 币种


                        ////FundAcc fundAcc = new FundAcc();
                        ////////账户类型
                        fundAcc.C_ACCOUNT_TYPE = this.cboAccType.Value == null ? " " : this.cboAccType.Value;
                        ////////保存账户信息
                        ////pojoList = getDataObjMVC(pojoList);
                        ////operResult = dataService.insert(pojoList[0] as BasePojo);
                        ////////获取账户信息的c_iden
                        ////ClsRetInfo retInfo = ClsRetInfoDealer.getReturnInfo(operResult);
                        ////cidenList = retInfo.cidenList;
                        ////fundAcc.Id = cidenList[0];
                        ////base.btnSave_Click(sender, e);
                        ////STORY #98216 【招商基金】【0331】银行账户信息加必填项
                        ////if (fundAcc.C_ACCOUNT_TYPE == "CPZH_TGH")
                        ////{
                        ////    if (this.cboCa.Value == null)
                        ////    {
                        ////        DialogResult dr = new MessageDialog().Show("现金账户为空，是否保存该数据！", "提示", MessageBoxButtons.YesNo);
                        ////        if (dr == DialogResult.No)
                        ////        {
                        ////            return;
                        ////        }
                        ////    }
                        ////}

                        operResult = yssFormOperation(status);
                        operAfterSave(operResult);
                        ////STORY #90197 银行账户信息界面新增账户时自动绑定组合
                        //// BUG246004【招商证券】产品基本信息下面的“产品账户信息”，新增保存一条后，看不到新增的账户信息
                        fundAcc.C_PORT_CODE = this.cboPort.Value;
                        ((AuditableParamPojo)fundAcc).OperUser = ClsContext.currentUser.C_USER_CODE;
                        ((AuditableParamPojo)fundAcc).AuditDate = ClsFunction.formatDateTime("yyyyMMdd HH:mm:ss", DateTime.Now.ToString());
                        saveAccRelaInfo(fundAcc);

                    }
                    else
                    {
                        return;
                    }
                }

                ////BUG #170816 【Trunk21.5.1.2】支付产品账户信息，设置界面的反审核按钮无效
                if (status == ClsEnums.StatusSetting.YssAudit || status == ClsEnums.StatusSetting.YssUnAudit || status == ClsEnums.StatusSetting.YssDel)
                {
                    base.btnSave_Click(sender, e);
                }
            }
            catch (System.Exception ex)
            {
                YssMessageBox.currentForm = this;
                YssMessageBox.ShowCommonInfos(TransferErrorMessageUtil.getTransferException(ex));
            }
        }

        /// <summary>
        /// 保存组合关联信息
        /// STORY #90197 银行账户信息界面新增账户时自动绑定组合
        /// </summary>
        /// <param name="fundAcc">fundAcc</param>
        private void saveAccRelaInfo(FundAcc fundAcc)
        {
            ////保存关联关系
            PortRela portRela = new PortRela();
            string portCodes = "";
            string startDate = "";
            string endDate = "";
            startDate = this.yssDateStart.getBeginDateStr;
            endDate = this.yssDateEnd.getBeginDateStr;
            portCodes = fundAcc.C_PORT_CODE;

            ////List界面被关联内嵌至其他界面时，组合不能被更改
            if (this.frmBaseViewList != null && this.frmBaseViewList is IFormDetailData && portCodes == null)
            {
                IFormDetailData frmDetailData = this.frmBaseViewList as IFormDetailData;
                if (frmDetailData.MainDataPojo != null && frmDetailData.MainDataPojo.GetType().GetProperty("C_PORT_CODE") != null)
                {
                    portCodes = (frmDetailData.MainDataPojo as Port).C_PORT_CODE;
                }
            }


            ////STORY #55433 【招商基金】同一个组合维护多个托管户时需要对托管户设置默认账户或增加提醒弹框功能
            ////组合选择不为空，且账户类型为托管户，保存时才提醒
            if (portCodes != null)
            {
                if (fundAccService == null)
                {
                    fundAccService = ServiceFactory.createService<IFundAccService>();
                }

                if (fundAcc.C_ACCOUNT_TYPE == "CPZH_TGH")
                {
                    Dictionary<string, string> dict = new Dictionary<string, string>();
                    dict.Add("C_PORT_CODE", portCodes);             ////组合
                    dict.Add("ARRAY_ACCOUNT_TYPE", "CPZH_TGH");         ////账户类型（托管户）
                    //// 获取产品账户信息
                    dict.Add("dataClass", "FundAcc");
                    QueryRes res = fundAccService.queryByCondition(dict);

                    if (res != null && res.DataList != null && res.DataList.Count > 0)
                    {
                        DialogResult dr = new MessageDialog().Show("该组合已经维护过托管户信息，请确认是否继续！", "提示", MessageBoxButtons.YesNo);
                        if (dr == DialogResult.No)
                        {
                            return;
                        }
                    }

                    IPortService unifypayParamService = ServiceFactory.createService<IPortService>();
                    string param = unifypayParamService.queryByParaCode("TGH_RELA_MULTI_PORT");
                    if (!"1".Equals(param))
                    {
                        if (portCodes.Contains("|"))
                        {
                            DialogResult dr = new MessageDialog().Show("托管户只能关联一个组合", "提示", MessageBoxButtons.OK);
                            return;
                        }
                    }
                    

                }

                fundAcc = fundAccService.getFundAccByAcc(fundAcc);

                Dictionary<string, string> dicts = new Dictionary<string, string>();
                dicts.Add("portCode", portCodes);
                dicts.Add("id", fundAcc.Id);
                dicts.Add("c_accout_type", fundAcc.C_ACCOUNT_TYPE);
                dicts.Add("d_start", startDate);
                dicts.Add("d_end", endDate);
                fundAccService.savePortFundRelaWithDate(dicts);
                ////new MessageDialog().Show("保存银行账户和产品关联关系成功", "提示", MessageBoxButtons.OK);
            }

            this.Dispose();
        }


        /// <summary>
        /// 保存方法
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        public void saveInfo(object sender, EventArgs e)
        {
            ClsEnums.StatusSetting statusFlag = status;
            ////string ports = this.cboPort.Value == null ? "" : this.cboPort.Value;
            if (openByOthers)
            {
                YssMessageBox.currentForm = this;
                string operResult = "";
                bool isdatasave;
                DateTime begin = DateTime.Now;
                try
                {
                    isdatasave = isSaveOper();

                    ////新增、修改操作需要作输入项校验
                    if (status == ClsEnums.StatusSetting.YssAdd || status == ClsEnums.StatusSetting.YssCopy || status == ClsEnums.StatusSetting.YssEdit)
                    {
                        if (!checkInput())
                        {
                            return;
                        }

                    }

                    ////如果是指令模式打开界面，保存直接审核
                    this._formFun.N_CHECK = 0;

                    operResult = yssFormOperation(status);


                    ////STORY #35491 南方基金-收方账户支持在支付自动划款指令界面新增,获取用户新增的id，方便修改
                    if (status == ClsEnums.StatusSetting.YssAdd || status == ClsEnums.StatusSetting.YssCopy)
                    {
                        string js = "{" + operResult + "}";

                        JObject jo = (JObject)JsonConvert.DeserializeObject(js);

                        JToken temp = jo["JSON_INFO"];

                        JValue s = (JValue)temp.Last.Last.Last.Last.First;

                        this.id = s.Value.ToString();
                    }

                    ////业务启动流程逻辑 
                    yssBusStartProcess(operResult);

                    this._formFun.N_CHECK = 1;

                    operAfterSave(operResult);

                    this.txtOpenAddr.YssReadOnly = true;

                    ////将审核未审核按钮设为不可用
                    btnBar.setButtonEnabled("btnAudit", false);
                    btnBar.setButtonEnabled("btnUnAudit", false);
                    ////BUG #147142 在综合指令界面新增手工指令，付款方新增一个账户信息并维护大额支付号，没有关联到开户机构，开户机构依然没有大额支付号
                    ////savechange();

                }
                catch (Exception ex)
                {
                    YssMessageBox.ShowCommonInfo(ex.Message);
                }
                finally
                {
                    YssMessageBox.currentForm = null;

                }
            }
            else
            {
                this.txtOpenAddr.YssReadOnly = true;
                //// STORY #35492 南方基金-产品账户信息中大额支付号与关联机构联动
                ////savechange();
                base.btnSave_Click(sender, e);
            }

            this.cboOrgan.YssReadOnly = true;
        }

        /// <summary>
        /// changeInfo
        /// </summary>
        /// <returns>bool</returns>
        private bool changeInfo()
        {
            reCheckSet = new Frm_FUND_ACC_CHECK_S();
            SysFun cls_FUN = null;
           
            if (ClsContext.sysFunHash.ContainsKey("fundAccInfo"))
            {
                ////add by mazhongyuan 20180607 BUG #202440 产品账户信息界面功能报错
                cls_FUN = (SysFun)ClsContext.sysFunHash["fundAccInfo"];
            }

            reCheckSet.YssFormMenu = cls_FUN;
            reCheckSet.frmFundAccS = this;
            reCheckSet.fundAcc = this.faceInfoToObjMVC() as FundAcc;
            reCheckSet.initControlStat();
            changeZt(this);
            if ("1".Equals(this.checkAccInfo))
            {
                ////reCheckSet.Show(this);
                reCheckSet.ShowDialog();
            }
            //// 在点击保存按钮后使得该按钮为不可见状态
            btnBar.setButtonVisable(ClsButtonName.BtnSave, false);

            return reCheckSet.isok;

        }

        /// <summary>
        /// 重写修改
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnEdit_Click(object sender, EventArgs e)
        {
            this.txtOpenAddr.YssReadOnly = false;
            base.btnEdit_Click(sender, e);
        }


        /// <summary>
        /// 重写删除,//STORY #35130 招商基金-删除账户信息时如果此账户已被用则需弹出提示框
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnDelete_Click(object sender, EventArgs e)
        {
            string c_open_addr;
            string c_open_acc_no;
            string c_open_acc_name;
            Yss.KMessage.MessageDialog msg = new Yss.KMessage.MessageDialog();
            bool flag = false;
            c_open_addr = this.txtOpenAddr.Text;
            c_open_acc_no = this.txtOpenNo.Text;
            c_open_acc_name = this.txtOpenName.Text;
            string s = "";
            try
            {
                s = this.fundAccService.queryZfbyPort(c_open_addr, c_open_acc_no, c_open_acc_name);
            }
            catch (System.Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ex.Message);
            }

            if ("true".Equals(s))
            {
                flag = true;
            }


            string tipMsg = "是否继续删除";
            if (flag)
            {
                tipMsg = "该账户已关联，是否继续删除";
            }

            DialogResult dr = msg.Show(tipMsg, "提示", MessageBoxButtons.YesNo);
            if (dr == DialogResult.Yes)
            {
                BasePojo pojo = this.yssGetBaseSelTypeItemMVC() as BasePojo;
                fundAccService.deleteById(pojo);
            }
            else
            {
                return;
            }

            this.Close();
            this.Dispose();

            if (this.frmlist == null)
            {
                frmlist = this.frmBaseViewList as Frm_FUND_ACC_L;
            }

            this.frmlist.btnSearch_Click(sender, e);
        }

        /// <summary>
        /// txtOpenName_LostFocus
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void txtOpenName_LostFocus(object sender, EventArgs e)
        {
            if ((status == ClsEnums.StatusSetting.YssAdd || status == ClsEnums.StatusSetting.YssEdit
                || status == ClsEnums.StatusSetting.YssCopy))
            {
                this.txtOpenName.Text = this.txtOpenName.Text.Trim();
            }
        }

        /// <summary>
        /// txtOpenNo_LostFocus
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void txtOpenNo_LostFocus(object sender, EventArgs e)
        {
            if ((status == ClsEnums.StatusSetting.YssAdd || status == ClsEnums.StatusSetting.YssEdit
                || status == ClsEnums.StatusSetting.YssCopy))
            {
                this.txtOpenNo.Text = this.txtOpenNo.Text.Trim();
            }
        }

        /// <summary>
        /// STORY #34271 招商基金-开户行与开户机构合并
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void txtOpenAddr_LostFocus(object sender, EventArgs e)
        {
            if ((status == ClsEnums.StatusSetting.YssAdd || status == ClsEnums.StatusSetting.YssEdit
                || status == ClsEnums.StatusSetting.YssCopy))
            {
                this.txtOpenAddr.Text = this.txtOpenAddr.Text.Trim();
                if (cOpenAddrStr != this.txtOpenAddr.Text)
                {
                    cOpenAddrStr = this.txtOpenAddr.Text;
                    Dictionary<string, string> paraDict = new Dictionary<string, string>();
                    paraDict.Add("dataClass", "Org");
                    paraDict.Add("C_ORG_NAME", this.txtOpenAddr.Text);
                    IOrgService orgService = ServiceFactory.createService<IOrgService>();
                    QueryRes rs = orgService.queryByCondition(paraDict);
                    List<BasePojo> dataList = rs.DataList;
                    if (dataList.Count > 0)
                    {
                        foreach (BasePojo pj in dataList)
                        {
                            Org orgPojo = (Org)pj;
                            if (orgPojo.C_ORG_NAME == this.txtOpenAddr.Text)
                            {
                                this.cboOrgan.Value = orgPojo.C_ORG_CODE;
                                return;
                            }
                        }
                    }
                }
            }
        }

        /// <summary>
        /// bankAccount
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void bankAccount(object sender, EventArgs e)
        {
            try
            {
                if (status != ClsEnums.StatusSetting.YssAdd && status != ClsEnums.StatusSetting.YssEdit && status != ClsEnums.StatusSetting.YssCopy)
                {
                    return;
                }

                Frm_ORG_S set = new Frm_ORG_S();
                SysFun cls_FUN = null;
                if (ClsContext.sysFunHash.ContainsKey("base_organ"))
                {
                    cls_FUN = (SysFun)ClsContext.sysFunHash["base_organ"];
                }
                else
                {
                    new MessageDialog().Show("功能菜单【关联机构设置】-【organ】未启用或未授权", "提示", MessageBoxButtons.OK);
                    return;
                }

                set.openType = "FrOther";
                bool modified = false; //// 记录修改标识
                if (cls_FUN.N_CHECK == 1)
                {
                    cls_FUN.N_CHECK = 0; //// 使新增的机构信息为已审核
                    modified = true;
                }

                set.YssFormMenu = cls_FUN;
                set.YssStatus = ClsEnums.StatusSetting.YssAdd;
                set.initControlStat();
                set.ShowDialog();
                Org org = (Org)set.faceInfoToObjMVC();
                //// 如果修改了复核机制，审核和反审核按钮会消失，需要重新添加进去
                if (modified)
                {
                    cls_FUN.N_CHECK = 1; //// 重新改回需要复核机制
                    List<string> list = (List<string>)ClsContext.HtFunRight[cls_FUN.C_FUN_CODE];
                    //// 添加审核、反审核按钮
                    if (!list.Contains(ClsButtonName.BtnAudit))
                    {
                        list.Add(ClsButtonName.BtnAudit);
                    }

                    if (!list.Contains(ClsButtonName.BtnUnAudit))
                    {
                        list.Add(ClsButtonName.BtnUnAudit);
                    }

                }

                set.Dispose();
                if (org != null && !string.IsNullOrEmpty(org.C_ORG_NAME))
                {
                    //// 将新增的机构信息放到控件中
                    this.cboOrgan.Items.Add(new KTableEntity(org));
                    //// 修改，增加联行号字段的赋值动作
                    this.txtOpenAddr.Text = org.C_ORG_NAME;
                    //// this.cboOrgan.Value = org.C_ORG_NAME;
                    this.cboOrgan.Value = org.C_ORG_CODE; //// 修改org.C_ORG_NAME为org.C_ORG_CODE（看上一行）
                    this.cboOrgan.Text = org.C_ORG_NAME;
                    ////BUG #156397 产品账户信息界面穿透关联机构设置界面，新增开户机构，点击保存报错ss
                    this.txtPayNum.Text = org.C_PAY_CODE;
                }
            }
            catch (System.Exception ex)
            {
                new MessageDialog().Show("新增关联机构异常", "系统异常", MessageBoxButtons.OK, MessageBoxIcon.Error, ex.Message);
            }
        }
      
        /// <summary>
        /// STORY #35492 南方基金-产品账户信息中大额支付号与关联机构联动
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void changeOrg(object sender, EventArgs e)
        {
            if (this.cboOrgan.Value != null)
            {
                if (this.cboOrgan.SelectedItem.DataEntity != null && string.IsNullOrEmpty(this.txtPayNum.Text))
                {
                    Org org = (Org)this.cboOrgan.SelectedItem.DataEntity;
                    this.txtPayNum.Text = org.C_PAY_CODE;
                }
            }
        }

        /// <summary>
        /// STORY #35398 南方基金-开户机构支持在支付产品账户信息界面新增 
        /// 开户机构值改变时自动给开户行赋值
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboOrgan_SelectedValueChanged(object sender, EventArgs e)
        {
            ////BUG #155498 南方基金-支付产品账户信息界面优化【十分紧急】，去掉，不给用户造成错觉
            /***
            if (this.cboOrgan.Value != null && this.txtOpenAddr.Text != this.cboOrgan.Text)
            {
               this.txtOpenAddr.Text = this.cboOrgan.Text;  
            }
             * ***/
            ////STORY #35492 南方基金-产品账户信息中大额支付号与关联机构联动
            ////BUG #146232 南方基金-中信银行深证通划款确认指令状态未更新
            if (this.cboOrgan.Value != null)
            {
                if (this.cboOrgan.SelectedItem != null)
                {
                    if (this.cboOrgan.Value != null)
                    {
                        if (this.cboOrgan.SelectedItem.DataEntity != null && string.IsNullOrEmpty(this.txtPayNum.Text))
                        {
                            Org org = (Org)this.cboOrgan.SelectedItem.DataEntity;
                            this.txtPayNum.Text = org.C_PAY_CODE;
                        }

                        if (status == ClsEnums.StatusSetting.YssAdd || status == ClsEnums.StatusSetting.YssEdit)
                        {
                            if (this.txtOpenAddr.Text == null || "".Equals(this.txtOpenAddr.Text))
                            {
                                this.txtOrgCode.Text = this.cboOrgan.Value;
                                this.txtOpenAddr.Text = this.cboOrgan.Text;
                            }
                        }
                    }
                }
            }
        }


        /// <summary>
        /// STORY #39521 南方基金-支付产品账户信息界面优化
        /// </summary>
        /// <param name="sender">""</param>
        /// <param name="e">""</param>
        private void changType(object sender, EventArgs e)
        {
            if (this.cboAccType.Value != null && (this.cboAccType.Value.Contains("CPZH_TGH") || this.cboAccType.Value.Contains("CPZH_TGH_SEC")))
            {
                this.cboOrgan.YssIsMust = true;
            }
            else
            {
                this.cboOrgan.YssIsMust = false;
            }

            //// STORY #80209 【华富基金】支付产品账户信息维护直销账户时，所属人为必填 
            if (this.cboAccType.Value == "CPZH_JGXSZH" || this.cboAccType.Value == "CPZH_JGXSZH_WSZX"
                || this.cboAccType.Value == "CPZH_JGXSZH_DX")
            {
                this.cboOrgan1.YssIsMust = true;
            }
            else
            {
                this.cboOrgan1.YssIsMust = false;
            }

            getCacode();
        }

        /// <summary>
        /// BUG #267458 【30.7UI】--账户信息设置修改账户时，机构不能进行查询，加载也很慢
        /// 机构下拉框事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboOrgan_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            if (this.cboAccType.Value != null && (this.cboAccType.Value.Contains("CPZH_TGH") || this.cboAccType.Value.Contains("CPZH_TGH_SEC")))
            {
                cboOrgan.Items.Clear();
                e.Cancel = true;
                List<BasePojo> pojos = getOrgByCache();
                try
                {
                    if (pojos != null && pojos.Count > 0)
                    {
                        foreach (BasePojo pojo in pojos)
                        {
                            Org org = pojo as Org;
                            ////STORY #60287 【富国基金】维护支付产品账户信息账户类型为托管户时，开户机构展示取值优化
                            if (string.IsNullOrEmpty(this.cboAccType.Value) || !this.cboAccType.Value.Contains("CPZH_TGH")
                                    || (!string.IsNullOrEmpty(this.cboAccType.Value) && this.cboAccType.Value.Contains("CPZH_TGH") && (!string.IsNullOrEmpty(org.C_DV_TRUSTEE)
                                    || !string.IsNullOrEmpty(org.C_DV_TRUSTEE_MA) || !string.IsNullOrEmpty(org.C_DV_TRUSTEE_SEC))))
                            {
                                cboOrgan.Items.Add(new Yss.KRichEx.AutoFilter.Model.KTableEntity(pojo));
                            }
                        }
                    }

                }
                catch (Exception ex)
                {
                    ClsLogger.Error(ex.Message);
                }
            }
        }

        /// <summary>
        /// 获取机构信息
        /// </summary>
        /// <returns>list</returns>
        private List<BasePojo> getOrgByCache()
        {
            List<BasePojo> pojos = null;
            try
            {
                string[] args = new string[] { "ORG_BXZJ", "ORG_SY", "ORG_FDC", "ORG_QTJR", "ORG_GR", "ORG_BXGS", "ORG_CWGS", "ORG_JJGS", "ORG_JYS_QH", "ORG_JYS_ZQ", "ORG_QHGS", "ORG_QT", "ORG_SB", "ORG_SYYH", "ORG_XTGS", "ORG_ZCGL", "ORG_ZQDJJG", "ORG_ZQGS", "" };
                OrgCache orgCache = (OrgCache)CacheFactory.CreateCache(CacheGroup.ORG);
                pojos = orgCache.getDataListByTypes(args);

                if (pojos == null || (pojos != null && pojos.Count == 0))
                {
                    if (refCount <= 5)
                    {
                        refCount++;
                        System.Threading.Thread.Sleep(2000);
                        getOrgByCache();
                    }
                }
            }
            catch (Exception ex)
            {
                ClsLogger.Error(ex.Message);
            }

            return pojos;
        }

        /// <summary>
        /// BUG #181159 【一体化账户】银行卡信息的省份、城市字段建议更改为下拉框，加载数据可从省份城市表中获取
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboProvince_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            e.Cancel = true;
            ////清空掉省份下拉框
            this.cboProvince.Items.Clear();

            QueryRes res = new QueryRes();

            Dictionary<string, string> dictPara = new Dictionary<string, string>();

            dictPara.Add("dataClass", "AreaCity");
            dictPara.Add("C_LEVEL", "2"); ////2代表省份

            IAreaCityDataService service = ServiceFactory.createService<IAreaCityDataService>();

            res = service.queryByCondition(dictPara);

            foreach (BasePojo pojo in res.DataList)
            {
                cboProvince.Items.Add(new KTableEntity(pojo));
            }
        }

        /// <summary>
        /// BUG #181159 【一体化账户】银行卡信息的省份、城市字段建议更改为下拉框，加载数据可从省份城市表中获取
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboProvince_SelectedValueChanged(object sender, EventArgs e)
        {
            ////清空本条数据城市
            this.cboCity.Items.Clear();
            this.cboCity.Text = "";
            this.cboCity.Value = "";
        }

        /// <summary>
        /// STORY #60287 【富国基金】维护支付产品账户信息账户类型为托管户时，开户机构展示取值优化
        /// </summary>
        /// <param name="sender">""</param>
        /// <param name="e">""</param>
        private void cboOrgan_AfterDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
        }

        /// <summary>
        /// BUG #181159 【一体化账户】银行卡信息的省份、城市字段建议更改为下拉框，加载数据可从省份城市表中获取
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboCity_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            cboCity.Items.Clear();

            e.Cancel = true;

            ////如果省份没有选中数据，城市下拉框不加载数据
            if (string.IsNullOrEmpty(cboProvince.Value))
            {
                return;
            }

            QueryRes res = new QueryRes();

            Dictionary<string, string> dictPara = new Dictionary<string, string>();

            dictPara.Add("dataClass", "AreaCity");
            dictPara.Add("C_LEVEL", "3"); //// 3代表城市
            dictPara.Add("C_PARENT_CODE", cboProvince.Value);

            IAreaCityDataService service = ServiceFactory.createService<IAreaCityDataService>();

            res = service.queryByCondition(dictPara);

            foreach (BasePojo pojo in res.DataList)
            {
                cboCity.Items.Add(new KTableEntity(pojo));
            }
        }


        /// <summary>
        /// exchangeControlPosition
        /// </summary>
        private void exchangeControlPosition()
        {
            this.cell1.Text = "   开户名称：";
            this.cell23.Text = "   开户账号：";
            this.cell11.Text = "   开 户 行：";
            this.cell2.InnerControl = null;
            this.cell24.InnerControl = null;
            this.cell12.InnerControl = null;
            this.cell2.InnerControl = this.txtOpenName;
            this.cell24.InnerControl = this.txtOpenNo;
            this.cell12.InnerControl = this.txtOpenAddr;
        }

        /// <summary>
        /// 密文选择框勾选状态改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void checkBoxKey_CheckedChanged(object sender, System.EventArgs e)
        {
            ////密文选择框为勾选状态，划款秘钥文本框内容加密展示
            if (this.checkBoxKey.Checked)
            {
                this.txtPayMentKey.PasswordChar = '*'; 
            }
            else
            {
                this.txtPayMentKey.PasswordChar = '\0';
            }
        }



        /// <summary>
        /// 根据账户类型（托管户）以及组合代码、币种代码查询现金账户
        /// 20210421 add by pya
        /// </summary>
        private void getCacode()
        {
            string portCode = "";
            ////账户类型为"托管户"。
            if (!string.IsNullOrEmpty(this.cboAccType.Value))
            {
                if (!string.IsNullOrEmpty(this.cboPort.Value)) 
                {
                    portCode = this.cboPort.Value;
                }
                ////新增和复制
                if (status == ClsEnums.StatusSetting.YssAdd || status == ClsEnums.StatusSetting.YssCopy)
                {
                    if (this.cboAccType.Value.Contains("CPZH_TGH"))
                    {
                        IPortService unifypayParamService = ServiceFactory.createService<IPortService>();
                        string accountType = unifypayParamService.getCacodeByAccountType(portCode, this.cboCury.Value);
                        if (string.IsNullOrEmpty(accountType))
                        {
                            this.cboCa.Value = "";
                        }
                        else
                        {
                            this.cboCa.Value = accountType;
                        }
                    }
                }
                ////修改
                if (status == ClsEnums.StatusSetting.YssEdit)
                {
                    if (this.cboAccType.Value.Contains("CPZH_TGH"))
                    {
                        IPortService unifypayParamService = ServiceFactory.createService<IPortService>();
                        string accountType = unifypayParamService.getCacodeByAccountType1(this.txtOpenName.Value, this.txtOpenNo.Value, this.txtOpenAddr.Value, this.cboCury.Value);
                        if (string.IsNullOrEmpty(accountType))
                        {
                            this.cboCa.Value = "";
                        }
                        else
                        {
                            this.cboCa.Value = accountType;
                        }
                    }
                }
            }
        }

        /// <summary>
        /// 组合值改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboPort_ValueChanged(object sender, EventArgs e)
        {
            getCacode();
        }
    }
}
