using System;
using System.Collections.Generic;
using System.Threading;
using FAST.Common.Service.Interface;
using FAST.Core.Bussiness.Form;
using FAST.Core.Communication.BusiService;
using YssElecReco.Service.Er;

namespace YssElecReco.Form.Er
{
    /// <summary>
    /// STORY55472日常运营可以配置电子对账发送
    /// 任务调度可配置电子对账发送项目
    /// </summary>
    public partial class Frm_ELEC_Send_L : FrmBaseOper, I_Workflow
    {
        public Frm_ELEC_Send_L()
        {
            InitializeComponent();
            this._useByThread = true;
        }
        /// <summary>
        /// 任务调度
        /// </summary>
        private bool isTask = false;

        public void setInit(Dictionary<string, string> lstSelPort, Dictionary<string, string> lstSelItems)
        {
            _leftPojos = lstSelPort;
            _selItems = lstSelItems;

            if (frmOperMes == null)
            {
                isTask = true;
                base.FrmBase_Load(this, System.EventArgs.Empty);
            }
        }

        public string doWorkflow(string idfCode, string funRelaId)
        {
            unchecked
            {
                try
                {
                    this.planCode = idfCode;
                    this.funRelaId = funRelaId;
                    doExecute(); ////调用基类方法执行业务 byleeyu 20120627

                    return "Success";
                }
                catch (Exception)
                {
                    return "Fail";

                }
            }
        }
        /// <summary>
        /// 获取处理服务实例
        /// </summary>
        protected override void setOperServiceInstance()
        {
            if (operSVC == null)
            {
                if (isTask)
                {
                    operSVC = BusiOperServiceFactory.createService<IElecTaskService>();
                }
                else
                {
                    operSVC = BusiOperServiceFactory.createService<IElecTaskService>();
                }
            }
        }
        public override string doSubSection()
        {
            string c_Result = "";
            bool isCancel = false;

            //// 1：执行前的一些处理
            beforeExecute(ref isCancel);

            if (!isCancel)
            {
                setOperServiceInstance();

                List<ThreadStart> threadStarts = new List<ThreadStart>();
                ////第一批次线程（处理公共接口），这里要先执行完之后，才能执行下面批次的线程。
                foreach (KeyValuePair<string, string> kv in this._selItems)
                {
                    foreach (string ports in getPortList())
                    {
                        //// kv.Key = 报表类型_电子对账类型
                        //// 估值表、科目表、余额表生成每个业务日期日报
                        //// 资产负债表、利润表、所有者权益变动表根据报表类型执行月、季、半年、年末和业务截止日期的电子对账生成
                        if (kv.Key.IndexOf("_") < 0 || (kv.Key.IndexOf("_") > -1 && checkDateByReport(kv.Key.Substring(0, 2))))
                        {
                            Dictionary<string, string> pdict = new Dictionary<string, string>();
                            pdict.Add("ARRAY_C_PORT_CODE", ports);
                            pdict.Add("ARRAY_C_DZ_CODE", kv.Key);
                            pdict.Add("D_START_DATE", this.d_OperCurr.ToString("yyyy-MM-dd"));
                            pdict.Add("D_END_DATE", this.d_OperCurr.ToString("yyyy-MM-dd"));
                            pdict.Add("C_FUN_CODE", this._formFun.C_FUN_CODE);//BUG19529821.6版本回归测试bug 1.产生电子对账页面业务历史日志查询为空
                            pdict.Add("C_PORT_CODE", ports);
                            pdict.Add("C_OPER_CODE", execOperCode);
                            pdict.Add("OPER_TYPE", "SEND");
                            pdict.Add("AUTOSEND", "1");
                            pdict.Add("C_IDF_CODE", this.planCode);
                            pdict.Add("C_RELA_ID", this.funRelaId);
                            /**20150720 added by liubo.STORY #24163 #26344任务调度日志修改成前台可查看
                               本次操作关联的调度方案的执行编号*/
                            pdict.Add("C_DISPATCH_ID", c_Dispatch_ID);

                            threadStarts.Add(delegate { this.doMethod(pdict); });
                        }
                    }
                }

                if (this.ThreadPool.ThreadCount > 0)
                {
                    ////如果线程已经启动，则将新线程附加到新的队列里
                    this.ThreadPool.JoinThread(threadStarts);
                }
                else
                {
                    ////如果尚未启动线程，则直接增加线程
                    foreach (ThreadStart loThreadStart in threadStarts)
                    {
                        this.ThreadPool.AddThread(loThreadStart);
                    }
                }
            }

            //// 3：执行后的一些处理
            afterExecute();

            return c_Result;
        }
        /// <summary>
        /// 获得分段后的组合
        /// </summary>
        /// <returns>1</returns>
        private List<string> getPortList()
        {
            List<string> list = new List<string>();
            //// 实现多线程处理：一个组合一个线程byleeyu20130811
            ////StringBuilder buf = new StringBuilder();
            ////分别记录，防止漏掉组合
            ////int i = 0;
            ////int j = 0;
            foreach (KeyValuePair<string, string> pojo in this._leftPojos)
            {
                list.Add(pojo.Key);
            }

            return list;
        }
        /// <summary>
        /// 执行前的判断
        /// </summary>
        /// <param name="isCancel">是否生成业务日期当天电子对账</param>
        public bool checkDateByReport(String rptType)
        {
            String sDate = this.d_OperCurr.ToString("yyyyMMdd");
            String month = sDate.Substring(4, 2);
            int num = (this.d_OperCurr.Month - 1) / 3;
            //// 日期控件最后一天所有报表类型都会执行生成
            if (this.d_OperCurr == this.yssDateTime.getEndDate)
            {
                return true;
            }

            if (rptType == "03")
            {
                //// 月报
                if (month.Equals("02"))
                {
                    if ((this.d_OperCurr.Year % 100 == 0 && this.d_OperCurr.Year % 400 == 0) ||
                        (this.d_OperCurr.Year % 100 != 0 && this.d_OperCurr.Year % 4 == 0))
                    {
                        //// 闰月
                        if (sDate.Substring(0, 6) + "29" == sDate)
                        {
                            return true;
                        }
                    }
                    else
                    {
                        if (sDate.Substring(0, 6) + "28" == sDate)
                        {
                            return true;
                        }
                    }
                }
                else if (month.Equals("04") || month.Equals("06") || month.Equals("09") || month.Equals("11"))
                {
                    //// 小月
                    if (sDate.Substring(0, 6) + "30" == sDate)
                    {
                        return true;
                    }
                }
                else
                {
                    //// 大月
                    if (sDate.Substring(0, 6) + "31" == sDate)
                    {
                        return true;
                    }
                }
            }
            else if (rptType == "04")
            {
                //// 季报
                String quarter = "";
                if (num == 0) quarter = "03";
                else if (num == 1) quarter = "06";
                else if (num == 2) quarter = "09";
                else quarter = "12";

                if (((num == 1 || num == 2) && sDate.Substring(0, 4) + quarter + "30" == sDate) ||
                    ((num == 0 || num == 3) && sDate.Substring(0, 4) + quarter + "31" == sDate))
                {
                    return true;
                }
            }
            else if (rptType == "05")
            {
                //// 半年报
                if (sDate == sDate.Substring(0, 4) + "0630" || sDate == sDate.Substring(0, 4) + "1231")
                {
                    return true;
                }
            }
            else
            {
                //// 年报
                if (sDate == sDate.Substring(0, 4) + "1231")
                {
                    return true;
                }
            }

            return false;
        }

        public int getTaskCount()
        {
            return 1;
        }

        public string doWorkflowAndShowForm()
        {
            throw new Exception("The method or operation is not implemented.");
        }
    }
}
