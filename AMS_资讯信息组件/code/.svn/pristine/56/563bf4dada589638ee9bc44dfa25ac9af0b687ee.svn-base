using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
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


using FAST.Core.BaseControl.Pojo;
using FAST.Core.BaseControl.Fun;


using Yss.KTable.Models;
using Yss.KRichEx.AutoFilter.Model;

namespace YssSecInformation.Func
{
    /// <summary>
    /// 理财界面产品信息中的检查间隔调用界面
    /// </summary>
    public partial class FrmLC_SelectDatePara : FrmBaseSet
    {
        /// <summary>
        /// 缓存保存每一年设置的数据
        /// </summary>
        private Dictionary<int, List<DateTime>> cache = new Dictionary<int, List<DateTime>>();

        /// <summary>
        /// year中间变量
        /// </summary>
        private int yearCache;

        /// <summary>
        /// 构造函数
        /// </summary>
        public FrmLC_SelectDatePara()
        {
            InitializeComponent();
            if (!DesignMode)
            {
                setButton();
            }
        }

        /// <summary>
        /// 初始化状态
        /// </summary>
        public override void initControlStat()
        {
            this.status = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            base.initControlStat();              
            this.iptYear.Text = DateTime.Now.Year.ToString();
            this.yearCalendar.Year = DateTime.Now.Year;
        }

        /// <summary>
        /// 添加按钮
        /// </summary>
        private void setButton()
        {
            //// 添加生成按钮
            ClsButtonInfo btnGernerate = new ClsButtonInfo();
            btnGernerate.BeginGroup = true;
            btnGernerate.Name = ClsButtonName.BtnGernerate;
            btnGernerate.Tooltip = "生成";
            btnGernerate.Image = new Bitmap(FAST.Resource.Resource.btnGernerate_L, 16, 16);
            btnGernerate.ClickEvent += new EventHandler(btnGernerate_Click);

            List<string> list = new List<string>();
            list.Add(ClsButtonName.BtnGernerate);
            list.Add(ClsButtonName.BtnClose);
            list.Add(ClsButtonName.BtnHelp);

            this.btnBar.FunRightList = list;
            this.btnBar.UserOperList = list;
            this.btnBar.addButton(btnGernerate, 1);

            //// 屏蔽多余按钮
            this.btnBar.setButtonVisable(ClsButtonName.BtnPrevious, false);
            this.btnBar.setButtonVisable(ClsButtonName.BtnNext, false);
            this.btnBar.setButtonVisable(ClsButtonName.BtnRecall, false);
        }

         /// <summary>
        /// 生成方法
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnGernerate_Click(object sender, EventArgs e)
        {
            this.DialogResult = DialogResult.OK;
            this.Close();
            this.Dispose();
        }

        /// <summary>
        /// 确认按钮点击
        /// </summary>
        /// <param name="sender">s</param>
        /// <param name="e">e</param>
        private void btnConfirm_Click(object sender, EventArgs e)
        {
            try
            {
                if (!this.checkInput())
                {
                    return;
                }

                //// if (yearCalendar.Values.Length <= 0 )
                //// {
                //// return;
                //// }

                yearCalendar.Year = this.iptYear.Value;
                List<DateTime> dates = new List<DateTime>();
                List<DateTime> allDateList = new List<DateTime>();
                allDateList = this.yearCalendar.Values.ToList();
                int lastYear = -1;

                if (allDateList.Count > 0)
                {
                    List<DateTime> newList = allDateList.ToList();

                    if (cache.ContainsKey(lastYear))
                    {
                        newList = newList.Union(cache[lastYear]).ToList();
                    }

                    newList.Sort();
                    AddCache(lastYear, newList);
                    allDateList.Clear();
                }

                if (cache.ContainsKey(this.iptYear.Value))
                {
                    yearCalendar.Values = cache[this.iptYear.Value].ToArray();
                }
                else
                {
                    yearCalendar.Values = dates.ToArray();
                }
            }
            catch (System.Exception ex)
            {
                YssMessageBox.currentForm = this;
                YssMessageBox.ShowCommonInfo(ex.Message);
            }
            finally
            {
                this.DialogResult = DialogResult.OK;
                this.Close();
                this.Dispose();

            }
        }

        /// <summary>
        /// iptYear_ValueChanged
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void iptYear_ValueChanged(object sender, EventArgs e)
        {
            ////添加改变前设置
            AddCache(yearCache, this.yearCalendar.Values.ToList());
            cache.Remove(-1);
            yearCache = this.yearCalendar.Year = Convert.ToInt32(iptYear.Value);

            ////加载当前年是否在缓存中有数据
            if (cache.ContainsKey(yearCache))
            {
                SetOneYearDate(yearCache, cache[yearCache]);
            }
        }

        /// <summary>
        /// 获取选择日期
        /// </summary>
        /// <returns>选择日期</returns>
        public List<DateTime> GetSelectedDate()
        {
            ////保存当前年的设置到缓存中
            AddCache(yearCache, this.yearCalendar.Values.ToList());
            ////cache.Remove(-1);
            List<DateTime> dateTimes = new List<DateTime>();
            ////Dictionary<int, List<DateTime>> dics = cache.OrderByDescending(t => t.Key).ToDictionary(t => t.Key, t => t.Value);

            foreach (KeyValuePair<int, List<DateTime>> dic in cache)
            {
                dateTimes.AddRange(dic.Value.Distinct());
            }

            if (this.DialogResult == DialogResult.Cancel)
            {
                return new List<DateTime>();
            }
            else
            {
                return dateTimes.OrderByDescending(t => t).ToList();
            }
        }

        /// <summary>
        /// 设置选择日期
        /// </summary>
        /// <param name="dateTimes">选择日期</param>
        public void SetSelectedDate(List<DateTime> dateTimes)
        {
            try
            {
                Dictionary<int, List<DateTime>> dics = new Dictionary<int, List<DateTime>>();
                foreach (var dt in dateTimes)
                {
                    if (dics.ContainsKey(dt.Year))
                    {
                        continue;
                    }

                    dics.Add(dt.Year, dateTimes.FindAll(x => x.Year == dt.Year));
                }

                ////var dics = dateTimes.ToDictionary(t => t.Year , t=>dateTimes.FindAll(x => x.Year == t.Year ));
                if (cache.Count != 0)
                {
                    cache.Clear();
                }

                cache = dics.OrderByDescending(t => t.Key).ToDictionary(t => t.Key, t => t.Value);
                //// 降序后取第一个显示
                var first = cache.First();
                SetOneYearDate(first.Key, first.Value);
                this.iptYear.Value = first.Key;
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 添加缓存
        /// </summary>
        /// <param name="year">year</param>
        /// <param name="dateTimes">dateTimes</param>
        private void AddCache(int year, List<DateTime> dateTimes)
        {
            if (cache.ContainsKey(year))
            {
                cache.Remove(year);
            }

            cache.Add(year, dateTimes);
        }

        /// <summary>
        /// 设置一年的选择日期
        /// </summary>
        /// <param name="year">年</param>
        /// <param name="dateTimes">选择日期</param>
        private void SetOneYearDate(int year, List<DateTime> dateTimes)
        {
            try
            {
                if (dateTimes == null || dateTimes.Count == 0)
                {
                    return;
                }

                this.yearCache = this.yearCalendar.Year = year;
                this.yearCalendar.Values = dateTimes.ToArray();
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 获取日期JSON字符串
        /// </summary>
        /// <returns>日期JSON字符串</returns>
        public string GetSelectedDateString()
        {
            ////保存当前年的设置到缓存中
            AddCache(yearCache, this.yearCalendar.Values.ToList());

            List<DateTime> dateTimes = new List<DateTime>();

            foreach (KeyValuePair<int, List<DateTime>> dic in cache)
            {
                dateTimes.AddRange(dic.Value.Distinct());
            }

            return JsonUtil.toJson(dateTimes.OrderByDescending(t => t).ToList());
        }

        /// <summary>
        /// 清空当前日历
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnClear_Click(object sender, EventArgs e)
        {
            yearCalendar.Values = null;
            this.cache.Clear();
        }
    }
}


