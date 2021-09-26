using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.Context.Events;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Platform.Menu.Pojo;
using FAST.Platform.Logger.Pojo;
using FAST.Platform.Safe.Pojo;
using FAST.Platform.Talk.Pojo;
using FAST.Common.Service.Interface;
using FAST.Common.Service.Pojo.Base;
////using YssBaseCls.Interface;
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

using Yss.KTable.Models;



using FAST.Core.BaseControl.Fun;
using YssInformation.Support.Bi.Holidays.Service;
using YssInformation.Support.Bi.Holidays_A.Pojo;
using YssInformation.Bi.Holidays_A.Form;



namespace YssInformation.Bi.Holidays.Form
{
    /// <summary>
    /// jia
    /// </summary>
    public partial class Frm_HDAY_CHILD_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private IHdayService hdayService = null;

        /// <summary>
        /// cehnbo 20170807 BUG #167551 【节假日群】当年存在数据进行会重新生成，系统的提示信息会一闪而过
        /// </summary>
        private Boolean isBool = false;

        /// <summary>
        /// 构造函数.
        /// </summary>
        public Frm_HDAY_CHILD_S()
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
            this.cboWeak.Value = "WD6|WD7"; // 星期控件赋值
        }





        /// <summary>
        /// 保存之后刷新控件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_HDAY_CHILD_S_YssOnAfterSaveClick(object sender, YssBeforeOperEventArgs e)
        {
            ////如果是视图模式，刷新视图
            if (!((Frm_HDAY_L)this.frmBaseViewList).rbdList.Checked)
            {
                ((Frm_HDAY_L)this.frmBaseViewList).inityearCalendar();
            }
        }

        /// <summary>
        /// 保存之前对数据进行验证
        /// chenbo 20170807   BUG #167551 【节假日群】当年存在数据进行会重新生成，系统的提示信息会一闪而过
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_HDAY_CHILD_S_YssOnBeforeSaveClick(object sender, YssBeforeOperEventArgs e)
        {
            ////ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();
            ////quyStrUtil.addQuyCon("C_HDAY_CODE", this.cboHolidays.Value, ClsConstant.SQL_RA_HYPHEN_EQUAL);
            ////quyStrUtil.addQuyCon("N_YEAR", this.intputYear.Text, ClsConstant.SQL_RA_HYPHEN_EQUAL);
            ////string obj = (string)dataAdmin.GetSpecValue(quyStrUtil.getQuyStr(this._formFun.C_FUN_CODE), "getSameHoiday"); // 后台查询的数据对象
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("C_HDAY_CODE", this.cboHolidays.Value);
            paraDict.Add("N_YEAR", this.intputYear.Text);
            string obj = hdayService.getSameHoiday(paraDict);

            if ("yes".Equals(obj))
            {
                isBool = true;
                e.IsCancel = true;
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("023", _formFun, status));
            }

        }

        /// <summary>
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_HDAY_CHILD_S_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.hdayService = ServiceFactory.createService(serviceType) as IHdayService;
            this.dataService = this.hdayService;
        }

        /// <summary>
        /// 封装一组窗体数据到集合
        /// </summary>
        /// <returns>ArrayList</returns>
        public override ArrayList yssGetObjListMVC()
        {
            ArrayList pojoList = new ArrayList();
            Hday child = null;
            Hashtable hash = new Hashtable();
            string weeks = this.cboWeak.Value; // 获取当前默认的节假日
            string[] weekAry = Regex.Split(weeks, "[|]");
            List<DayOfWeek> list = new List<DayOfWeek>();
            DateTime time = new DateTime();
            DateTime beginDate = DateTime.Parse(this.intputYear.Value + "-01-01"); // 获取起始时间
            DateTime endDate = DateTime.Parse(this.intputYear.Value + "-12-31"); // 获取结束时间
            TimeSpan ts = endDate - beginDate; // 判断时间差
            ////开始日期不能大于结束日期
            if (ts.Days < 0)
            {
                ////YssMessageBox.ShowInfo("开始日期不能大于结束日期！");
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("013", _formFun, status));
                return null;

            }

            ////循环数组产生当前的星期数组
            foreach (string dayOfWeek in weekAry)
            {
                if ("WD1".Equals(dayOfWeek))
                {
                    hash.Add(DayOfWeek.Monday, DayOfWeek.Monday);
                }
                else if ("WD2".Equals(dayOfWeek))
                {
                    hash.Add(DayOfWeek.Tuesday, DayOfWeek.Tuesday);
                }
                else if ("WD3".Equals(dayOfWeek))
                {
                    hash.Add(DayOfWeek.Wednesday, DayOfWeek.Wednesday);
                }
                else if ("WD4".Equals(dayOfWeek))
                {
                    hash.Add(DayOfWeek.Thursday, DayOfWeek.Thursday);
                }
                else if ("WD5".Equals(dayOfWeek))
                {
                    hash.Add(DayOfWeek.Friday, DayOfWeek.Friday);
                }
                else if ("WD6".Equals(dayOfWeek))
                {
                    hash.Add(DayOfWeek.Saturday, DayOfWeek.Saturday);
                }
                else if ("WD7".Equals(dayOfWeek))
                {
                    hash.Add(DayOfWeek.Sunday, DayOfWeek.Sunday);
                }

            }

            ////从开始的前一天开始遍历
            time = beginDate.AddDays(-1);
            string year = beginDate.Year.ToString();
            for (int i = 0; i <= ts.Days; i++)
            {
                time = time.AddDays(1);
                child = new Hday();
                child.C_HDAY_CODE = this.cboHolidays.Value;
                child.N_YEAR = year;
                child.C_DATE_TYPE = (null == hash[time.DayOfWeek] ? "D" : "H");
                if (child.C_DATE_TYPE == "D")
                {
                    continue;
                }

                child.D_HDAY = time.ToString("yyyy-MM-dd");
                pojoList.Add(child);
            }

            return pojoList;
        }

        /// <summary>
        /// 展示对象属性到窗体
        /// </summary>
        /// <param name="pojo">Pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                Hday exchange = (Hday)this.yssGetBaseSelTypeItemMVC();
                if (exchange == null)
                {
                    return;
                }

                this.cboHolidays.Value = exchange.C_HDAY_CODE;
                this.intputYear.Value = Convert.ToInt32(exchange.N_YEAR); // 赋值给市场名称
                this.cboWeak.Value = getDayOfWeak(DateTime.Parse(exchange.D_HDAY).DayOfWeek);
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }
        }

        /// <summary>
        /// 重写保存事件
        /// chenbo 20170807   BUG #167551 【节假日群】当年存在数据进行会重新生成，系统的提示信息会一闪而过
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnSave_Click(object sender, EventArgs e)
        {
            base.btnSave_Click(sender, e);
            ((Frm_HDAY_L)this.frmBaseViewList).setSelectRowYear();          
            if (!isBool)
            {
                  this.frmBaseViewList.btnSearch_Click(sender, e);
            }   
        }

        /// <summary>
        /// 获取对应的数据
        /// </summary>
        /// <param name="dayofWeak">当前日期类型</param>
        /// <returns>字符串</returns>
        private string getDayOfWeak(DayOfWeek dayofWeak)
        {
            if (DayOfWeek.Monday == dayofWeak)
            {
                return "WD1";
            }
            else if (DayOfWeek.Tuesday == dayofWeak)
            {
                return "WD2";
            }
            else if (DayOfWeek.Wednesday == dayofWeak)
            {
                return "WD3";
            }
            else if (DayOfWeek.Thursday == dayofWeak)
            {
                return "WD4";
            }
            else if (DayOfWeek.Friday == dayofWeak)
            {
                return "WD5";
            }
            else if (DayOfWeek.Saturday == dayofWeak)
            {
                return "WD6";
            }
            else
            {
                return "WD7";
            }
        }

        #region 旧方法 20160607
        /**
         * 
         * 
        /// <summary>
        /// 返回一组数据
        /// </summary>
        /// <returns>list</returns>
        public override List<IBasePojo> yssGetObjList()
        {
            List<IBasePojo> pojoList = new List<IBasePojo>();
            Cls_HDAY child = null;
            Hashtable hash = new Hashtable();
            string weeks = this.cboWeak.Value; // 获取当前默认的节假日
            string[] weekAry = Regex.Split(weeks, "[|]");
            List<DayOfWeek> list = new List<DayOfWeek>();
            DateTime time = new DateTime();
            DateTime beginDate = DateTime.Parse(this.intputYear.Value + "-01-01"); // 获取起始时间
            DateTime endDate = DateTime.Parse(this.intputYear.Value + "-12-31"); // 获取结束时间
            TimeSpan ts = endDate - beginDate; // 判断时间差
            ////开始日期不能大于结束日期
            if (ts.Days < 0)
            {
                ////YssMessageBox.ShowInfo("开始日期不能大于结束日期！");
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("013", _formFun, status));
                return null;

            }

            ////循环数组产生当前的星期数组
            foreach (string dayOfWeek in weekAry) 
            {
                if ("WD1".Equals(dayOfWeek))
                {
                    hash.Add(DayOfWeek.Monday, DayOfWeek.Monday);
                }
                else if ("WD2".Equals(dayOfWeek))
                {
                    hash.Add(DayOfWeek.Tuesday, DayOfWeek.Tuesday);
                }
                else if ("WD3".Equals(dayOfWeek))
                {
                    hash.Add(DayOfWeek.Wednesday, DayOfWeek.Wednesday);
                }
                else if ("WD4".Equals(dayOfWeek))
                {
                    hash.Add(DayOfWeek.Thursday, DayOfWeek.Thursday);
                }
                else if ("WD5".Equals(dayOfWeek))
                {
                    hash.Add(DayOfWeek.Friday, DayOfWeek.Friday);
                }
                else if ("WD6".Equals(dayOfWeek))
                {
                    hash.Add(DayOfWeek.Saturday, DayOfWeek.Saturday);
                }
                else if ("WD7".Equals(dayOfWeek))
                {
                    hash.Add(DayOfWeek.Sunday, DayOfWeek.Sunday);
                }
            
            }

            ////从开始的前一天开始遍历
            time = beginDate.AddDays(-1);
            string year = beginDate.Year.ToString();
            for (int i = 0; i <= ts.Days; i++)
            {
                time = time.AddDays(1);
                child = new Cls_HDAY();
                child.C_HDAY_CODE = this.cboHolidays.Value;
                child.N_YEAR = year;
                child.C_DATE_TYPE = (null == hash[time.DayOfWeek] ? "D" : "H");
                child.D_HDAY = time.ToString("yyyy-MM-dd");
                pojoList.Add(child);
            }

            return pojoList;

        }
         *         /// <summary>
        /// 获取list中选中记录，为界面元素赋值，显示数据.
        /// </summary>
        public override void yssShowInfo()
        {
            try
            {
                Cls_HDAY exchange = (Cls_HDAY)yssGetBaseSelTypeItem();
                if (exchange == null)
                {
                    return;
                }

                this.cboHolidays.Value = exchange.C_HDAY_CODE;
                this.intputYear.Value = Convert.ToInt32(exchange.N_YEAR); // 赋值给市场名称
                this.cboWeak.Value = getDayOfWeak(DateTime.Parse(exchange.D_HDAY).DayOfWeek);
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }

        }

        /// <summary>
        /// 获取对应的数据
        /// </summary>
        /// <param name="dayofWeak">当前日期类型</param>
        /// <returns>字符串</returns>
        private string getDayOfWeak(DayOfWeek dayofWeak) 
        {
            if (DayOfWeek.Monday == dayofWeak)
            {
                return "WD1";
            }
            else if (DayOfWeek.Tuesday == dayofWeak)
            {
                return "WD2";
            }
            else if (DayOfWeek.Wednesday == dayofWeak)
            {
                return "WD3";
            }
            else if (DayOfWeek.Thursday == dayofWeak)
            {
                 return "WD4";
            }
            else if (DayOfWeek.Friday == dayofWeak)
            {
                 return "WD5";
            }
            else if (DayOfWeek.Saturday == dayofWeak)
            {
                 return "WD6";
            }
            else
            {
                 return "WD7";
            }
        }
         *   /// <summary>
        /// 保存之前对数据进行验证
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_HDAY_CHILD_S_YssOnBeforeSaveClick(object sender, YssBeforeOperEventArgs e)
        {
            ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();


            quyStrUtil.addQuyCon("C_HDAY_CODE", this.cboHolidays.Value, ClsConstant.SQL_RA_HYPHEN_EQUAL);
            quyStrUtil.addQuyCon("N_YEAR", this.intputYear.Text, ClsConstant.SQL_RA_HYPHEN_EQUAL);
            string obj = (string)dataAdmin.GetSpecValue(quyStrUtil.getQuyStr(this._formFun.C_FUN_CODE), "getSameHoiday"); // 后台查询的数据对象
            if ("yes".Equals(obj))
            {
                e.IsCancel = true;
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("023", _formFun, status));
            }

        }
         * **/
        #endregion
    }
}


