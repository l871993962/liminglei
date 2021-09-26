using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.CRUD.Form;
using YssVisAval.service;
using YssVisAval.Pojo.AA;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Context;
using FAST.Core.Communication.Service;
using FAST.Core.BaseControl;
using FAST.Common.Service.Dict.Pojo;

namespace YssVisAval.Form.Algo
{
    /// <summary>
    /// 1
    /// </summary>
    public partial class Frm_ADVANCED_ALGORITHM_L_CZ : FrmBaseList
    {
        /// <summary>
        /// 接口
        /// </summary>
        private IVisAdvAlgoService service = null;

        /// <summary>
        /// 下拉控件
        /// </summary>
        private YssSelCombox box = null;

        /// <summary>
        /// 2
        /// </summary>
        public Frm_ADVANCED_ALGORITHM_L_CZ()
        {
            InitializeComponent();
        }

        /// <summary>
        /// 带参构造方法
        /// </summary>
        /// <param name="selCombox">控件</param>
        public Frm_ADVANCED_ALGORITHM_L_CZ(YssSelCombox selCombox)
        {
            InitializeComponent();
            box = selCombox;
        }

        /// <summary>
        /// 设置窗体菜单信息
        /// </summary>
        /// <param name="baseFun">菜单对象</param>
        public void setMenuFun(IBaseFun baseFun)
        {
            this._formFun = baseFun;
        }

         /// <summary>
        /// 窗体初始化
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void FrmBaseList_Load(object sender, EventArgs e)
        {
            this.service = (IVisAdvAlgoService)ServiceFactory.createService<IVisAdvAlgoService>();

        //// 初始化窗体，隐藏掉一些不需要的panel
            yssPanel1.Visible = false;
            this.pnlLeftMain.Visible = false;
            this.pnlMain.Visible = false;
            splitLeft.Visible = false;
            loadTreeData();
        }

        /// <summary>
        /// 加载左侧算法树
        /// </summary>
        public void loadTreeData()
        {
            string dataJson = this.service.getAlgos();
            List<AdvAlgo> algos = (List<AdvAlgo>)JsonUtil.toObject(dataJson, typeof(List<AdvAlgo>));

            QueryRes res = new QueryRes();
            ListHeadInfo listHead = new ListHeadInfo();
            listHead.Key = "C_ALGO_NAME";
            listHead.Text = "算法名称";
            ListHeadInfo listHead2 = new ListHeadInfo();
            listHead2.Key = "C_ALGO_CODE";
            listHead2.Text = "算法code";
            List<ListHeadInfo> list = new List<ListHeadInfo>();
            list.Add(listHead2);
            list.Add(listHead);

            res.DataList = convertPojo(algos);
            res.ListHeadList = list;
            TableListLoader tableListLoader = new TableListLoader();
            tableListLoader.loadTable(this.algoTreeTable, res, false, false, ClsEnums.KTableDataShowMode.ListMode);
        }

        /// <summary>
        /// pojo转换为BasePojo
        /// </summary>
        /// <param name="algos">1</param>
        /// <returns>12</returns>
        private List<BasePojo> convertPojo(List<AdvAlgo> algos)
        {
            List<BasePojo> datas = new List<BasePojo>();
            if (null != algos)
            {
                foreach (AdvAlgo api in algos)
                {
                    BasePojo pojo = (BasePojo)api;
                    datas.Add(pojo);
                }
            }

            return datas;
        }

        /// <summary>
        /// 树选择事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void algoTreeTable_SelectionChanged(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            this.aogoDescText.Enabled = false;
            AdvAlgo algo = (AdvAlgo)this.algoTreeTable.SelectedRow.Tag;
            this.aogoDescText.Rtf = this.service.getAlgoDesc(algo.C_ALGO_CODE);
        }

        /// <summary>
        /// 确定按钮事件
        /// </summary>
        /// <param name="sender">按钮</param>
        /// <param name="e">事件参数</param>
        private void button1_Click(object sender, EventArgs e)
        {
            // // this.Combox.SeletectData();
            AdvAlgo algo = (AdvAlgo)this.algoTreeTable.SelectedRow.Tag;
            box.Text = algo.C_ALGO_CODE;

            box.Items.Clear();
            box.Parameter = "C_DV_NAME";
            box.DisplayValue = "C_DV_CODE";
            box.DisplayName = "C_DV_NAME";

            Vocabulary voc = new Vocabulary();
            voc.C_DV_CODE = algo.C_ALGO_CODE;
            voc.C_DV_NAME = algo.C_ALGO_NAME;
            Yss.KRichEx.AutoFilter.Model.KTableEntity enty = new Yss.KRichEx.AutoFilter.Model.KTableEntity(voc);
            box.Items.Add(enty);

            box.IsRefresh = false;

            box.Value = algo.C_ALGO_CODE;

            this.Close();
        }

        /// <summary>
        /// 取消按钮事件
        /// </summary>
        /// <param name="sender">1</param>
        /// <param name="e">2</param>
        private void button2_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        /// <summary>
        /// 查询
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void selfTxtSearch_TailClick(object sender, EventArgs e)
        {
            Yss.CommonLib.FilterTable(this.algoTreeTable.Rows, this.setSelfTxtSearch.Text, new string[] { "C_ALGO_CODE", "C_ALGO_NAME" });
        }

    }
}
