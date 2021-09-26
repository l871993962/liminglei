using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.Context.Events;
using FAST.Core.CRUD.Form;

using FAST.Common.Service.Pojo.Base;
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
using System.Collections;
using System.Text.RegularExpressions;
using Yss.KRichEx.AutoFilter.Model;

using FAST.Core.BaseControl.Fun;

using YssVisAval.Pojo.AA;




using YssVisAval.service;
using YssVisAval.Fun;



namespace YssVisAval.Form.AA
{
    /// <summary>
    /// 高级算法set窗体
    /// </summary>
    public partial class Frm_ADVANCED_ALGORITHM_S : FrmBaseSet
    {
         /// <summary>
        /// 公式编辑器窗体引用
        /// </summary>
        private FrmFormulaEditor formulaForm = null;

        /// <summary>
        /// 检测结果
        /// </summary>
        private string checkRes = "";

        /// <summary>
        /// 获取点击前光标在text中的位置
        /// </summary>
        public int txtAlgoSelectionStart = 0;

        /// <summary>
        /// 算法公式
        /// </summary>
        private AdvAlgo algo = null;

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_ADVANCED_ALGORITHM_S()
        {
            bUseMVCService = true;
            InitializeComponent();
        }

        /// <summary>
        /// 算法公式
        /// </summary>
        public AdvAlgo Algo
        {
            get
            {
                return algo;
            }

            set
            {
                algo = value;
                this.status = ClsEnums.StatusSetting.YssBrow;
            }
        }

        /// <summary>
        /// 封装界面元素为pojo对象.
        /// </summary>
        /// <returns>由界面元素组成的对象.</returns>
        public override ClsBasePojo yssFaceInfoToObj()
        {
            Cls_Advanecd_Algorithm clsAlgo = null;
            try
            {
                clsAlgo = new Cls_Advanecd_Algorithm();

        //// 判断list选中界面是否有选中数据，在修改时获取原数据的值
                if (null != yssGetBaseSelTypeItem())
                {
                    clsAlgo.OLD_ALGO_CODE = ((Cls_Advanecd_Algorithm)yssGetBaseSelTypeItem()).OLD_ALGO_CODE;
                }

                clsAlgo.C_ALGO_CODE = this.txtAlgoCode.Text;
                clsAlgo.C_ALGO_NAME = this.txtExchangeName.Text;
                clsAlgo.C_DV_ALGO_TYPE = this.cboAlgoType.Value;
                clsAlgo.C_ALGO_FORMULA = this.txtAlgo.Text;
                clsAlgo.C_ALGO_FORMULA_TRANSFORM = checkRes;
                clsAlgo.C_DESC = this.txtDesc.Text;
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return clsAlgo;
        }

        /// <summary>
        /// 封装界面元素为pojo对象
        /// </summary>
        /// <returns>由界面元素组成的对象</returns>
        public override AuditableParamPojo faceInfoToObjMVC()
        {
            AdvAlgo clsAlgo = null;
            try
            {
                clsAlgo = new AdvAlgo();
                

                clsAlgo.C_ALGO_CODE = this.txtAlgoCode.Text;
                clsAlgo.C_ALGO_NAME = this.txtExchangeName.Text;
                clsAlgo.C_DV_ALGO_TYPE = this.cboAlgoType.Value;
                clsAlgo.C_ALGO_FORMULA = this.txtAlgo.Text;
                clsAlgo.C_ALGO_FORMULA_TRANSFORM = checkRes;
                clsAlgo.C_DESC = this.txtDesc.Text;

                if (checkRes == "" || checkRes == null)
                {
                    clsAlgo.C_ALGO_FORMULA_TRANSFORM = " ";
                }

                if (this.algo != null)
                {
                    clsAlgo.Id = algo.Id;
                }
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return clsAlgo;
        }

        /// <summary>
        /// 展示
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void buttonX1_Click(object sender, EventArgs e)
        {
            this.txtAlgoSelectionStart = this.txtAlgo.SelectionStart; 
            formulaForm = new FrmFormulaEditor(this);   
            formulaForm.ShowDialog(this);
        }

        /// <summary>
        /// 点击检测公式,
        /// 把公式转换成系统能够识别
        /// 的算术公式，并把执行结果返回
        /// 到前台
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void buttonX2_Click(object sender, EventArgs e)
        {
            try
            {
                if (0 == this.txtAlgo.Text.Trim().Length)
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("003", _formFun, status));
                    return;
                }
                
                IVisAdvAlgoService service = (IVisAdvAlgoService)this.dataService;
                string retStr = service.checkFormula("False", this.txtAlgo.Text);

        ////string retStr = (string)dataAdmin.GetSpecValue(this.txtAlgo.Text, "checkFormula", "AdvancedAlgorithm"); // 获取对应的算法
                if (ClsBizCons.CheckResult.Equals(retStr))
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("002", _formFun, status));
                }
            else
            {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, status));

                }
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtError("001", _formFun, status));
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 服务接口
        /// </summary>
        /// <returns>service</returns>
        public IVisAdvAlgoService getService()
        {
            return (IVisAdvAlgoService)this.dataService;
        }

        /// <summary>
        /// 保存之前对数据进行验证
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ADVANCED_ALGORITHM_S_YssOnBeforeSaveClick(object sender, YssBeforeOperEventArgs e)
        {
            // //delete by zhaoxianlin 20130506 STORY #3351 算法公式的优化--start
            ////try
            ////{

            // //    ////string retStr = dataAdmin.GetSpecValue(this.txtAlgo.Text, "getRealExpression", "AdvancedAlgorithm"); // 获取对应的算法
            ////    IAdvAlgoService service = (IAdvAlgoService)this.dataService;

        ////    string retStr = service.getRealExpression(this.txtAlgo.Text);

        ////    if (ClsBizCons.CheckResult.Equals(retStr))
            ////    {

            // //        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtError("002", _formFun, status));

        ////        e.IsCancel = true;

        ////     }

        ////    else
            ////    {

            // //        checkRes = retStr;

        ////    }
                
            //// }

        ////catch (Exception ex)
            ////{

            // //    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtError("003", _formFun, status));

        ////    ClsBaseException.DiscardException(ex);

        //// }

        ////delete by zhaoxianlin 20130506 STORY #3351 算法公式的优化--end
            try
            {
                if (0 == this.txtAlgo.Text.Trim().Length)
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("003", _formFun, status));
                    e.IsCancel = true;
                    return;
                }

                IVisAdvAlgoService service = (IVisAdvAlgoService)this.dataService;
                string retStr = service.checkFormula("False", this.txtAlgo.Text);
                if (ClsBizCons.CheckResult.Equals(retStr))
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtError("002", _formFun, status));
                    e.IsCancel = true;
                }
            else
            {
                    checkRes = retStr;

                }
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtError("003", _formFun, status));
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 获取list中选中记录，为界面元素赋值，显示数据
        /// </summary>
        /// <param name="pojo">数据对象</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            AdvAlgo clsAlgo = this.algo;
            if (clsAlgo == null)
            {
                clsAlgo = (AdvAlgo)this.yssGetBaseSelTypeItemMVC();
            }

            try
            {
                if (clsAlgo != null)
                {
                    this.txtAlgoCode.Text = clsAlgo.C_ALGO_CODE;
                    this.txtExchangeName.Text = clsAlgo.C_ALGO_NAME;
                    this.cboAlgoType.Value = clsAlgo.C_DV_ALGO_TYPE;
                    this.txtAlgo.Text = clsAlgo.C_ALGO_FORMULA;
                    this.txtDesc.Text = clsAlgo.C_DESC;
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }
       
        /// <summary>
        /// readOnly事件改变
        /// 促发的事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void txtDesc_ReadOnlyChanged(object sender, EventArgs e)
        {
            try
            {
                if (this.txtDesc.YssReadOnly)
                {
                    this.pnlFormula.Enabled = true;
                    this.txtAlgo.YssReadOnly = true;
                    this.txtAlgo.Enabled = true;
                    this.panel1.Enabled = false;
                }
                else 
                {
                    this.txtAlgo.YssReadOnly = false;
                    this.panel1.Enabled = true;
                }

                this.txtAlgo.Refresh();

            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 更改窗体的控件/数据控件的状态
        /// </summary>
        protected override void YssChangeControlState()
        {
            // //增加新增时的控件初始化 by leeyu 20120618
            if (status == ClsEnums.StatusSetting.YssAdd)
            {
                clsInterface.yssClearTableCtlValue(tbMain); //// 调整了代码位置到ClsInterface类中 byleeyu 20120618
                this.txtAlgo.Clear(); //// add by zhaoxianlin 20130510 BUG7776#3351
            }

            txtDesc_ReadOnlyChanged(new object(), new EventArgs());
        }

        /// <summary>
        /// 初始化窗体控件状态
        /// </summary>
        public override void initControlStat()
        {
            if (this._formFun == null)
            {
                this._formFun = FAST.Core.Context.ClsContext.sysFunHash["AdvancedAlgorithm"];
            }

            base.initControlStat();
        }

    }

      
}


