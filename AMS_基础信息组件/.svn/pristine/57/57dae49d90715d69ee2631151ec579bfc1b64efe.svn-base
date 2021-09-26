using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.Context.Events;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;


using FAST.Core.Context;
using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;
using System;
using System.Collections;
using YssInformation.Support.Bi.Holidays.Service;
using YssInformation.Support.Bi.Holidays_A.Pojo;
using YssInformation.Bi.Holidays_A.Form;





namespace YssInformation.Bi.Holidays.Form
{   
    /// <summary>
    /// 自定义节假日期设置
    /// </summary>
    /// <author>yh 2011.08.20</author>
    public partial class Frm_HDAY_CHILD1_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private IHdayService hdayService = null;

        /// <summary>
        /// 默认构造方法
        /// </summary>
        public Frm_HDAY_CHILD1_S()
        {
            this.bUseMVCService = true;
            InitializeComponent();
        }

        /// <summary>
        /// 初始化查询模块控件.
        /// </summary>
        public override void yssInitCtlAttr()
        {
            if (null != this.frmBaseViewList.tbLeftMain.SelectedRow)
            {
                HdayGroup clsHday = this.frmBaseViewList.tbLeftMain.SelectedRow.Tag as HdayGroup;
                this.cboHolidays.Value = clsHday.C_HDAY_CODE;
            }

            this.intputYear.Value = Convert.ToInt32(DateTime.Now.Year.ToString()); // 给年份赋值
        }

        /// <summary>
        /// 检查开始日期和结束日期是否是同一年
        /// </summary>
        /// <returns>判断结果</returns>
        private bool checkYear()
        {
            bool result = false;
            DateTime beginDate = dtpBegin.getBeginDate;
            DateTime endDate = dtpEnd.getEndDate;
            if (beginDate.Year != endDate.Year)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("008", _formFun, status));
            }
            else
            {
                result = true;
            }

            return result;

        }

        /// <summary>
        /// 数据改变时对控件进行赋值
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void intputYear_ValueChanged(object sender, EventArgs e)
        {
            int year = intputYear.Value;
            this.dtpBegin.setRange(DateTime.Parse(year + "-01-01"), DateTime.Parse(year + "-12-31"));
            this.dtpEnd.setRange(DateTime.Parse(year + "-01-01"), DateTime.Parse(year + "-12-31"));

            ////Orlando 2012-10-29 add 让开始时间与结束时间根据年份变动
            this.dtpBegin.setDateTime(new DateTime(year, 1, 1));
            this.dtpEnd.setDateTime(new DateTime(year, 12, 31));
               
        }

        /// <summary>
        /// 保存之后刷新控件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_HDAY_CHILD1_S_YssOnAfterSaveClick(object sender, YssBeforeOperEventArgs e)
        {
            ////判断该年份是否已经有数据
            
            ////如果是视图模式，刷新视图
            if (!((Frm_HDAY_L)this.frmBaseViewList).rbdList.Checked)
            {
                ((Frm_HDAY_L)this.frmBaseViewList).inityearCalendar();
            }
        }

        /// <summary>
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_HDAY_CHILD1_S_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.hdayService = ServiceFactory.createService(serviceType) as IHdayService;
            this.dataService = this.hdayService;
        }

        /// <summary>
        /// 展示对象属性到窗体
        /// </summary>
        /// <param name="pojo">Pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                HdayGen exchange = (HdayGen)this.yssGetBaseSelTypeItemMVC();
                if (exchange == null)
                {
                    return;
                }

                this.cboHolidays.Value = exchange.C_HDAY_CODE;
                this.intputYear.Value = Convert.ToInt32(exchange.N_YEAR); // 赋值给市场名称
                this.dtpBegin.setDateTime(Convert.ToDateTime(exchange.D_HDAY)); // 上市日期
                this.dtpEnd.setDateTime(Convert.ToDateTime(exchange.D_HDAY)); // 退市日期
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }
        }

        /// <summary>
        /// 封装一组窗体数据到集合
        /// </summary>
        /// <returns>ArrayList</returns>
        public override System.Collections.ArrayList yssGetObjListMVC()
        {
            ArrayList pojoList = null;
            Hday child = null;

            if (checkYear())
            {
                pojoList = new ArrayList();
                DateTime time = new DateTime();
                DateTime beginDate = dtpBegin.getBeginDate;
                DateTime endDate = dtpEnd.getEndDate;
                TimeSpan ts = endDate - beginDate;

                ////开始日期不能大于结束日期
                if (ts.Days < 0)
                {
                    ////YssMessageBox.ShowInfo("开始日期不能大于结束日期！");
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("013", _formFun, status));
                    return null;

                }

                ////从开始的前一天开始遍历
                time = beginDate.AddDays(-1);
                string year = beginDate.Year.ToString();
                for (int i = 0; i <= ts.Days; i++)
                {
                    time = time.AddDays(1);
                    child = new Hday();
                    child.C_HDAY_CODE = this.cboHolidays.Value;
                    child.N_YEAR = this.intputYear.Value.ToString();
                    child.C_DATE_TYPE = "H";
                    child.D_HDAY = time.ToString("yyyy-MM-dd");
                    pojoList.Add(child);
                }
            }

            return pojoList;
        }

        /// <summary>
        /// 重写保存事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnSave_Click(object sender, EventArgs e)
        {
            base.btnSave_Click(sender, e);
            Frm_HDAY_L listFrm = this.frmBaseViewList as Frm_HDAY_L;
            listFrm.reLoadyear();
            ////((Frm_HDAY_L)this.frmBaseViewList).setSelectRowYear();
            ////this.frmBaseViewList.btnSearch_Click(sender, e);
        }
    }
}


