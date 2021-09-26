using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.CRUD.Form;
using YssInformation.Support.Sys.Dictionary.Service;
using FAST.Core.Communication.Service;
using FAST.Core.BaseControl;
using FAST.Core.Util;
using Yss.KRichEx;
using Yss.KRichEx.AutoFilter.Model;
using Yss.KTable.Collections;
using FAST.Common.Service.Pojo.Base;
using YssInformation.Support.Sys.Dictionary.Pojo;
using YssElecReco.Pojo.Bi;
using YssElecReco.Context;
using YssElecReco.Service.Bi;
using Yss.KTable.Models;
using Yss.KTableExpand;
using Yss.KTable.Events;
using FAST.Core.Context;
using FAST.Core.Communication.DataService;
using System.Collections;

namespace YssElecReco.Form.Bi
{
    /// <summary>
    /// 个性指标
    /// </summary>
    public partial class Frm_ELEC_RELA_PER_S : FrmBaseSet
    {
        /// <summary>
        /// IDzTypeDataService
        /// </summary>
        private IDzTypeDataService iDzTypeService = null;

        /// <summary>
        /// 是否为明细窗体
        /// </summary>
        private bool bDetailForm = false;

        /// <summary>
        /// 指标ID
        /// </summary>
        private string cZBId;

        /// <summary>
        /// 对账类型字典
        /// </summary>
        private Dictionary<string, string> dtDzTypeDic = new Dictionary<string, string>();

        /// <summary>
        /// 对账类型CodeList
        /// </summary>
        private List<string> lDzCodeList = new List<string>();

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_ELEC_RELA_PER_S()
        {
            InitializeComponent();
            ////this.isDetailForm = false;//初始化赋值为非明细
            this.bUseMVCService = true;
            BuildDZZBHeader();
            initTables();
            setTablesVisble();
            ////加载对账类型字典
            iDzTypeService = DataServiceFactory.createService<IDzTypeDataService>();
            List<BasePojo> lDzTypeList = iDzTypeService.getDataList();

            foreach (BasePojo pojo in lDzTypeList)
            {
                if (pojo is DzType)
                {
                    DzType xdzTypePojo = pojo as DzType;
                    dtDzTypeDic.Add(xdzTypePojo.C_DZ_CODE, xdzTypePojo.C_DZ_NAME);
                }
            }
        }

        /// <summary>
        /// 指标ID
        /// </summary>
        public string ZBId
        {
            get { return this.cZBId; }

            set { this.cZBId = value; }
        }

        /// <summary>
        /// 是否为明细窗体的set界面
        /// </summary>
        public bool IsDetailForm
        {
            get
            {
                return this.bDetailForm;
            }

            set
            {
                this.bDetailForm = value;
            }
        }

        /// <summary>
        /// 加载窗体
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ELEC_RELA_PER_S_LOAD(object sender, EventArgs e)
        {
            ////BUG212335个性指标设置list界面是否发送栏位显示与详细信息不符
            ////this.setDefaultValue();//初始化下拉框默认值
            ////新增时，才需要设置默认值
            if (ClsEnums.StatusSetting.YssAdd.Equals(this.YssStatus))
            {
                this.setDefaultValue(); ////初始化下拉框默认值
            }
        }

        /// <summary>
        /// 下拉框默认值设置
        /// </summary>
        private void setDefaultValue() 
        {
            this.cboIsSend.Value = "1"; ////默认为是
            //this.C_ZB_ELEM1.Value = "SEND_ORGIN_VAL";//指标项默认选择发送
            //this.C_ZB_ELEM2.Value = "SEND_ORGIN_VAL";
            //this.C_ZB_ELEM3.Value = "SEND_ORGIN_VAL";
            //this.C_ZB_ELEM4.Value = "SEND_ORGIN_VAL";
            //this.C_ZB_ELEM5.Value = "SEND_ORGIN_VAL";
            //this.C_ZB_ELEM6.Value = "SEND_ORGIN_VAL";
            //this.C_ZB_ELEM7.Value = "SEND_ORGIN_VAL";
            //this.C_ZB_ELEM8.Value = "SEND_ORGIN_VAL";
            //this.C_ZB_ELEM9.Value = "SEND_ORGIN_VAL";
        }

        

        

        /// <summary>
        /// 设置指标状态
        /// </summary>
        public void setZBCodeState() 
        {
            if (this.bDetailForm)
            {
                this.cboZBCode.Enabled = false;
                this.cboZBCode.Value = string.IsNullOrEmpty(cZBId) ? "" : cZBId;
            }
            else
            {
                this.cboZBCode.Enabled = true;
            }
        }

        /// <summary>
        /// 显示单条数据，参数为set界面数据对应的pojo对象
        /// </summary>
        /// <param name="pojo">pojo</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            ElecPerRela elecPerRela = pojo as ElecPerRela;
            IElecRelaService service = DataServiceFactory.createService<IElecRelaService>();
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("ARRAY_C_DZ_CODE", elecPerRela.C_DZ_CODE);
            paraDict.Add("C_ZB_CODE", elecPerRela.C_ZB_CODE);
            paraDict.Add("C_ZB_NAME", elecPerRela.C_ZB_NAME);
            paraDict.Add("dataClass", "ElecRela");
            BasePojo basePojo = service.queryByCondition(paraDict).DataList[0];
            this.cboZBCode.Value = basePojo.Id;
            this.cboIsSend.Value = elecPerRela.C_SEND_MODE;
            this.cboPort.Value = elecPerRela.C_PORT_CODE;
            if (elecPerRela.C_DZ_CODE.Equals("1011"))
            { ////估值表
                GZBTable.Rows[0].Checked = elecPerRela.C_ZB_VALUE1.Equals("SEND_ORGIN_VAL");
                GZBTable.Rows[1].Checked = elecPerRela.C_ZB_VALUE2.Equals("SEND_ORGIN_VAL");
                GZBTable.Rows[2].Checked = elecPerRela.C_ZB_VALUE3.Equals("SEND_ORGIN_VAL");
                GZBTable.Rows[3].Checked = elecPerRela.C_ZB_VALUE4.Equals("SEND_ORGIN_VAL");
                GZBTable.Rows[4].Checked = elecPerRela.C_ZB_VALUE5.Equals("SEND_ORGIN_VAL");
                GZBTable.Rows[5].Checked = elecPerRela.C_ZB_VALUE6.Equals("SEND_ORGIN_VAL");
                GZBTable.Rows[6].Checked = elecPerRela.C_ZB_VALUE7.Equals("SEND_ORGIN_VAL");
            }
            else if (elecPerRela.C_DZ_CODE.Equals("1013"))
            { ////双估值表
                GZBTable.Rows[0].Checked = elecPerRela.C_ZB_VALUE1.Equals("SEND_ORGIN_VAL");
                GZBTable.Rows[1].Checked = elecPerRela.C_ZB_VALUE2.Equals("SEND_ORGIN_VAL");
                GZBTable.Rows[2].Checked = elecPerRela.C_ZB_VALUE3.Equals("SEND_ORGIN_VAL");
                GZBTable.Rows[3].Checked = elecPerRela.C_ZB_VALUE4.Equals("SEND_ORGIN_VAL");
                GZBTable.Rows[4].Checked = elecPerRela.C_ZB_VALUE5.Equals("SEND_ORGIN_VAL");
                GZBTable.Rows[5].Checked = elecPerRela.C_ZB_VALUE6.Equals("SEND_ORGIN_VAL");
                GZBTable.Rows[6].Checked = elecPerRela.C_ZB_VALUE7.Equals("SEND_ORGIN_VAL");
            }
            else if (elecPerRela.C_DZ_CODE.Equals("1801") || elecPerRela.C_DZ_CODE.Equals("1811"))
            { ////利润表
                LRBTable.Rows[0].Checked = elecPerRela.C_ZB_VALUE1.Equals("SEND_ORGIN_VAL");
                LRBTable.Rows[1].Checked = elecPerRela.C_ZB_VALUE2.Equals("SEND_ORGIN_VAL");
            }
            else if (elecPerRela.C_DZ_CODE.Equals("1903"))
            { //净资产变动表
                JZCBDBTable.Rows[0].Checked = elecPerRela.C_ZB_VALUE1.Equals("SEND_ORGIN_VAL");
                JZCBDBTable.Rows[1].Checked = elecPerRela.C_ZB_VALUE2.Equals("SEND_ORGIN_VAL");
            }
            else if (elecPerRela.C_DZ_CODE.Equals("1701") || elecPerRela.C_DZ_CODE.Equals("1711"))
            { ////资产负债表
                ZCFZBTable.Rows[0].Checked = elecPerRela.C_ZB_VALUE1.Equals("SEND_ORGIN_VAL");
                ZCFZBTable.Rows[1].Checked = elecPerRela.C_ZB_VALUE2.Equals("SEND_ORGIN_VAL");
            }
            else if (elecPerRela.C_DZ_CODE.Equals("1901"))
            { ////资产负债表
                SYZQYBDBTable.Rows[0].Checked = elecPerRela.C_ZB_VALUE1.Equals("SEND_ORGIN_VAL");
                SYZQYBDBTable.Rows[1].Checked = elecPerRela.C_ZB_VALUE2.Equals("SEND_ORGIN_VAL");
                SYZQYBDBTable.Rows[2].Checked = elecPerRela.C_ZB_VALUE3.Equals("SEND_ORGIN_VAL");
                SYZQYBDBTable.Rows[3].Checked = elecPerRela.C_ZB_VALUE4.Equals("SEND_ORGIN_VAL");
                SYZQYBDBTable.Rows[4].Checked = elecPerRela.C_ZB_VALUE5.Equals("SEND_ORGIN_VAL");
                SYZQYBDBTable.Rows[5].Checked = elecPerRela.C_ZB_VALUE6.Equals("SEND_ORGIN_VAL");
            }
        }

        /// <summary>
        /// 将界面控件录入信息封装成pojo对象
        /// </summary>
        /// <returns>ArrayList</returns>
        public ArrayList faceInfoToBaseListObjMVC()
        {
            ArrayList basePojoList = new ArrayList();
             ////遍历下拉框的值
            foreach (ControlEntity cboEntity in cboZBCode.CheckedItems)
            {
                ElecPerRela elecPerRela = new ElecPerRela();
                ElecRela elecRela = cboEntity.DataEntity as ElecRela;
                elecPerRela.C_ZB_CODE = elecRela.C_ZB_CODE;
                elecPerRela.C_ZB_NAME = elecRela.C_ZB_NAME;
                elecPerRela.C_DZ_CODE = elecRela.C_DZ_CODE;
                elecPerRela.C_SEND_MODE = this.cboIsSend.Value;
                if (elecRela.C_DZ_CODE.Equals("1011"))
                { ////估值表
                    elecPerRela.C_ZB_ELEMENT1 = GZBTable.Rows[0].RowName;
                    elecPerRela.C_ZB_VALUE1 = GZBTable.Rows[0].Checked ? "SEND_ORGIN_VAL" : "NOSEND";
                    elecPerRela.C_ZB_ELEMENT2 = GZBTable.Rows[1].RowName;
                    elecPerRela.C_ZB_VALUE2 = GZBTable.Rows[1].Checked ? "SEND_ORGIN_VAL" : "NOSEND";
                    elecPerRela.C_ZB_ELEMENT3 = GZBTable.Rows[2].RowName;
                    elecPerRela.C_ZB_VALUE3 = GZBTable.Rows[2].Checked ? "SEND_ORGIN_VAL" : "NOSEND";
                    elecPerRela.C_ZB_ELEMENT4 = GZBTable.Rows[3].RowName;
                    elecPerRela.C_ZB_VALUE4 = GZBTable.Rows[3].Checked ? "SEND_ORGIN_VAL" : "NOSEND";
                    elecPerRela.C_ZB_ELEMENT5 = GZBTable.Rows[4].RowName;
                    elecPerRela.C_ZB_VALUE5 = GZBTable.Rows[4].Checked ? "SEND_ORGIN_VAL" : "NOSEND";
                    elecPerRela.C_ZB_ELEMENT6 = GZBTable.Rows[5].RowName;
                    elecPerRela.C_ZB_VALUE6 = GZBTable.Rows[5].Checked ? "SEND_ORGIN_VAL" : "NOSEND";
                    elecPerRela.C_ZB_ELEMENT7 = GZBTable.Rows[6].RowName;
                    elecPerRela.C_ZB_VALUE7 = GZBTable.Rows[6].Checked ? "SEND_ORGIN_VAL" : "NOSEND";
                    elecPerRela.C_ZB_ELEMENT8 = GZBTable.Rows[7].RowName;
                    elecPerRela.C_ZB_VALUE8 = GZBTable.Rows[7].Checked ? "SEND_ORGIN_VAL" : "NOSEND";
                }
                else if (elecRela.C_DZ_CODE.Equals("1013"))
                { ////双估值表
                    elecPerRela.C_ZB_ELEMENT1 = GZBTable.Rows[0].RowName;
                    elecPerRela.C_ZB_VALUE1 = GZBTable.Rows[0].Checked ? "SEND_ORGIN_VAL" : "NOSEND";
                    elecPerRela.C_ZB_ELEMENT2 = GZBTable.Rows[1].RowName;
                    elecPerRela.C_ZB_VALUE2 = GZBTable.Rows[1].Checked ? "SEND_ORGIN_VAL" : "NOSEND";
                    elecPerRela.C_ZB_ELEMENT3 = GZBTable.Rows[2].RowName;
                    elecPerRela.C_ZB_VALUE3 = GZBTable.Rows[2].Checked ? "SEND_ORGIN_VAL" : "NOSEND";
                    elecPerRela.C_ZB_ELEMENT4 = GZBTable.Rows[3].RowName;
                    elecPerRela.C_ZB_VALUE4 = GZBTable.Rows[3].Checked ? "SEND_ORGIN_VAL" : "NOSEND";
                    elecPerRela.C_ZB_ELEMENT5 = GZBTable.Rows[4].RowName;
                    elecPerRela.C_ZB_VALUE5 = GZBTable.Rows[4].Checked ? "SEND_ORGIN_VAL" : "NOSEND";
                    elecPerRela.C_ZB_ELEMENT6 = GZBTable.Rows[5].RowName;
                    elecPerRela.C_ZB_VALUE6 = GZBTable.Rows[5].Checked ? "SEND_ORGIN_VAL" : "NOSEND";
                    elecPerRela.C_ZB_ELEMENT7 = GZBTable.Rows[6].RowName;
                    elecPerRela.C_ZB_VALUE7 = GZBTable.Rows[6].Checked ? "SEND_ORGIN_VAL" : "NOSEND";
                    elecPerRela.C_ZB_ELEMENT8 = GZBTable.Rows[7].RowName;
                    elecPerRela.C_ZB_VALUE8 = GZBTable.Rows[7].Checked ? "SEND_ORGIN_VAL" : "NOSEND";
                }
                else if (elecRela.C_DZ_CODE.Equals("1801") || elecRela.C_DZ_CODE.Equals("1811"))
                { ////利润表
                    elecPerRela.C_ZB_ELEMENT1 = LRBTable.Rows[0].RowName;
                    elecPerRela.C_ZB_VALUE1 = LRBTable.Rows[0].Checked ? "SEND_ORGIN_VAL" : "NOSEND";
                    elecPerRela.C_ZB_ELEMENT2 = LRBTable.Rows[1].RowName;
                    elecPerRela.C_ZB_VALUE2 = LRBTable.Rows[1].Checked ? "SEND_ORGIN_VAL" : "NOSEND";
                }
                else if (elecRela.C_DZ_CODE.Equals("1903"))
                { //净资产变动表
                    elecPerRela.C_ZB_ELEMENT1 = JZCBDBTable.Rows[0].RowName;
                    elecPerRela.C_ZB_VALUE1 = JZCBDBTable.Rows[0].Checked ? "SEND_ORGIN_VAL" : "NOSEND";
                    elecPerRela.C_ZB_ELEMENT2 = JZCBDBTable.Rows[1].RowName;
                    elecPerRela.C_ZB_VALUE2 = JZCBDBTable.Rows[1].Checked ? "SEND_ORGIN_VAL" : "NOSEND";
                }
                else if (elecRela.C_DZ_CODE.Equals("1701") || elecRela.C_DZ_CODE.Equals("1711"))
                { ////资产负债表
                    elecPerRela.C_ZB_ELEMENT1 = ZCFZBTable.Rows[0].RowName;
                    elecPerRela.C_ZB_VALUE1 = ZCFZBTable.Rows[0].Checked ? "SEND_ORGIN_VAL" : "NOSEND";
                    elecPerRela.C_ZB_ELEMENT2 = ZCFZBTable.Rows[1].RowName;
                    elecPerRela.C_ZB_VALUE2 = ZCFZBTable.Rows[1].Checked ? "SEND_ORGIN_VAL" : "NOSEND";
                }
                else if (elecRela.C_DZ_CODE.Equals("1901"))
                { ////所有者权益变动表
                    elecPerRela.C_ZB_ELEMENT1 = SYZQYBDBTable.Rows[0].RowName;
                    elecPerRela.C_ZB_VALUE1 = SYZQYBDBTable.Rows[0].Checked ? "SEND_ORGIN_VAL" : "NOSEND";
                    elecPerRela.C_ZB_ELEMENT2 = SYZQYBDBTable.Rows[1].RowName;
                    elecPerRela.C_ZB_VALUE2 = SYZQYBDBTable.Rows[1].Checked ? "SEND_ORGIN_VAL" : "NOSEND";
                    elecPerRela.C_ZB_ELEMENT3 = SYZQYBDBTable.Rows[2].RowName;
                    elecPerRela.C_ZB_VALUE3 = SYZQYBDBTable.Rows[2].Checked ? "SEND_ORGIN_VAL" : "NOSEND";
                    elecPerRela.C_ZB_ELEMENT4 = SYZQYBDBTable.Rows[3].RowName;
                    elecPerRela.C_ZB_VALUE4 = SYZQYBDBTable.Rows[3].Checked ? "SEND_ORGIN_VAL" : "NOSEND";
                    elecPerRela.C_ZB_ELEMENT5 = SYZQYBDBTable.Rows[4].RowName;
                    elecPerRela.C_ZB_VALUE5 = SYZQYBDBTable.Rows[4].Checked ? "SEND_ORGIN_VAL" : "NOSEND";
                    elecPerRela.C_ZB_ELEMENT6 = SYZQYBDBTable.Rows[5].RowName;
                    elecPerRela.C_ZB_VALUE6 = SYZQYBDBTable.Rows[5].Checked ? "SEND_ORGIN_VAL" : "NOSEND";
                }

                basePojoList.Add(elecPerRela);
            }

            ////ElecPerRela elecPerRela = new ElecPerRela();
            ////elecPerRela.C_ZB_CODE = this.cboZBCode.Value;
            ////elecPerRela.C_ZB_NAME = this.cboZBName.Text;
            ////elecPerRela.C_DZ_CODE = this.cboDzType.Value;
            ////elecPerRela.C_SEND_MODE = this.cboIsSend.Value;
            ////elecPerRela.C_ZB_ELEMENT1 = trimBlankAndSpecial(this.tbMain.Rows[8].Cells[0].Text,':');
            ////elecPerRela.C_ZB_ELEMENT2 = trimBlankAndSpecial(this.tbMain.Rows[8].Cells[3].Text,':');
            ////elecPerRela.C_ZB_VALUE1 = this.C_ZB_ELEM1.Value;
            ////elecPerRela.C_ZB_VALUE2 = this.C_ZB_ELEM2.Value;
            ////if (cboDzType.Value.Equals("1901"))//所有者权益变动表
            ////{
            ////    elecPerRela.C_ZB_ELEMENT3 = trimBlankAndSpecial(this.tbMain.Rows[9].Cells[0].Text, ':');
            ////    elecPerRela.C_ZB_ELEMENT4 = trimBlankAndSpecial(this.tbMain.Rows[9].Cells[3].Text, ':');
            ////    elecPerRela.C_ZB_ELEMENT5 = trimBlankAndSpecial(this.tbMain.Rows[10].Cells[0].Text, ':');
            ////    elecPerRela.C_ZB_ELEMENT6 = trimBlankAndSpecial(this.tbMain.Rows[10].Cells[3].Text, ':');
            ////    elecPerRela.C_ZB_VALUE3 = this.C_ZB_ELEM3.Value;
            ////    elecPerRela.C_ZB_VALUE4 = this.C_ZB_ELEM4.Value;
            ////    elecPerRela.C_ZB_VALUE5 = this.C_ZB_ELEM5.Value;
            ////    elecPerRela.C_ZB_VALUE6 = this.C_ZB_ELEM6.Value;
            ////}
            ////else if (cboDzType.Value.Equals("1701"))//资产负债表  ----就两个指标已经在前面赋值
            ////{

            ////}
            ////else if (cboDzType.Value.Equals("1801"))//利润表   ----就两个指标已经在前面赋值
            ////{
               
            ////}
            ////else if (cboDzType.Value.Equals("1011"))//估值表
            ////{
            ////    elecPerRela.C_ZB_ELEMENT3 = trimBlankAndSpecial(this.tbMain.Rows[9].Cells[0].Text, ':');
            ////    elecPerRela.C_ZB_ELEMENT4 = trimBlankAndSpecial(this.tbMain.Rows[9].Cells[3].Text, ':');
            ////    elecPerRela.C_ZB_ELEMENT5 = trimBlankAndSpecial(this.tbMain.Rows[10].Cells[0].Text, ':');
            ////    elecPerRela.C_ZB_ELEMENT6 = trimBlankAndSpecial(this.tbMain.Rows[10].Cells[3].Text, ':');
            ////    elecPerRela.C_ZB_ELEMENT7 = trimBlankAndSpecial(this.tbMain.Rows[11].Cells[0].Text, ':');
            ////    elecPerRela.C_ZB_ELEMENT8 = trimBlankAndSpecial(this.tbMain.Rows[11].Cells[3].Text, ':');
            ////    elecPerRela.C_ZB_ELEMENT9 = trimBlankAndSpecial(this.tbMain.Rows[12].Cells[0].Text, ':');
            ////    elecPerRela.C_ZB_VALUE3 = this.C_ZB_ELEM3.Value;
            ////    elecPerRela.C_ZB_VALUE4 = this.C_ZB_ELEM4.Value;
            ////    elecPerRela.C_ZB_VALUE5 = this.C_ZB_ELEM5.Value;
            ////    elecPerRela.C_ZB_VALUE6 = this.C_ZB_ELEM6.Value;
            ////    elecPerRela.C_ZB_VALUE7 = this.C_ZB_ELEM7.Value;
            ////    elecPerRela.C_ZB_VALUE8 = this.C_ZB_ELEM8.Value;
            ////    elecPerRela.C_ZB_VALUE9 = this.C_ZB_ELEM9.Value;
            ////}
            return basePojoList;
        }

        /// <summary>
        /// 去掉前后空格以及后面的特殊字符
        /// </summary>
        /// <param name="str">需处理的字符串</param>
        /// <param name="specialChar">分割字符</param>
        /// <returns>string</returns>
        private string trimBlankAndSpecial(string str, char specialChar) 
        {
            string str1 = str.Trim();
            string str2 = str1.TrimEnd(specialChar);
            return str2;
        }


        /// <summary>
        /// 获取修改的数据
        /// </summary>
        /// <returns>ArrayList</returns>
        public override System.Collections.ArrayList yssGetObjListMVC()
        {
            System.Collections.ArrayList list = new System.Collections.ArrayList();
            string selectPort = this.cboPort.Value;
            if (!string.IsNullOrEmpty(selectPort))
            {
                string[] ports = selectPort.Split('|');
                for (int i = 0; i < ports.Length; i++)
                {
                    ArrayList basePojoList = this.faceInfoToBaseListObjMVC();
                    foreach (BasePojo basePojo in basePojoList)
                    {
                        (basePojo as ElecPerRela).C_PORT_CODE = ports[i];
                        list.Add(basePojo);
                    }
                }
            }
            else
            {
                list.AddRange(this.faceInfoToBaseListObjMVC());
            }

            return list;
        }
        
        /// <summary>
        /// 指标代码下拉框加载前事件处理
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboZB_YssOnBeforeLoadData(object sender, FAST.Core.Context.Events.YssBeforeOperEventArgs e)
        {
            IElecRelaService service = ServiceFactory.createService<IElecRelaService>();
            List<BasePojo> lDzRelaList = service.getDataList();
            if (null != lDzRelaList && lDzRelaList.Count > 0)
            {
                foreach (BasePojo pojo in lDzRelaList)
                {
                    if (pojo is ElecRela)
                    {
                        Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(pojo as ElecRela);
                        (sender as YssSelCombox).Items.Add(entity);
                    }
                }
            }

            e.IsCancel = true;
        }

        /// <summary>
        /// 对账指标列头
        /// </summary>
        private void BuildDZZBHeader()
        {
            Table table = this.DZZBRichTable.Table;

            Column column = new MarkColumn();
            column.DataPropertyName = "ShowRowIndexColumn";
            table.Columns.Add(column);

            Column column1 = new Column();
            column1.DataPropertyName = "C_DZ_CODE";
            column1.Text = "指标代码";
            column1.Width = 140;
            table.Columns.Add(column1);

            Column column2 = new Column();
            column2.DataPropertyName = "C_DZ_NAME";
            column2.Text = "指标名称";
            column2.Width = 180;
            table.Columns.Add(column2);

            Column column3 = new Column();
            column3.DataPropertyName = "C_DZ_TYPE";
            column3.Text = "对账类型";
            column3.Width = 140;
            table.Columns.Add(column3);

            table.AllowColumnDrag = false;
            table.AllowResizeColumn = true;
            table.AutoColumnWidth = true;
            table.DefaultToolStripItems = Yss.KTable.Enums.SysToolStripItems.None;
            table.Refresh();
        }

        /// <summary>
        /// 用于批量添加事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="richTableName">richTable名字</param>
        /// <param name="cboName">下拉控件</param>
        private void batchAdd(object sender, RichTable richTableName, YssSelCombox cboName)
        {
            lDzCodeList.Clear();
            List<Yss.KTable.Models.Row> rowlist = new List<Yss.KTable.Models.Row>();

            foreach (Yss.KTable.Models.Row row in richTableName.Table.Rows)
            {
                int aa = row.Cells.Count;
                if (row.RowName != null)
                {
                    rowlist.Add(row);
                }
            }

            int cellsCount = richTableName.Table.Columns.Count;
            int count = rowlist.Count;
            for (int a = 0; a < count; a++)
            {
                richTableName.Table.Rows.Remove(rowlist[a], true);
            }

            ////遍历下拉框的值
            foreach (ControlEntity cboEntity in cboName.CheckedItems)
            {
                ElecRela elecRela = cboEntity.DataEntity as ElecRela;
                string cZbName = elecRela.C_ZB_NAME;
                string cZbCode = elecRela.C_ZB_CODE;
                string cDzType = elecRela.C_DZ_CODE;

                Row row = new Row();
                for (int i = 0; i < cellsCount; i++)
                {
                    if (i == 1) 
                    {
                        Cell cell = new Cell();
                        cell.Text = cZbCode;
                        row.Cells.Add(cell);
                    }
                    else if (i == 2)
                    {
                        Cell cell = new Cell();
                        cell.Text = cZbName;
                        row.Cells.Add(cell);
                    }
                    else if (i == 3)
                    {
                        Cell cell = new Cell();
                        cell.Text = dtDzTypeDic[cDzType];
                        row.Cells.Add(cell);
                    }
                    else
                    {
                        row.Cells.Add(new Cell());
                    }

                }

                row.Checked = true;
                row.Selected = true;
                row.RowName = "ZB";
                richTableName.Table.Rows.Add(row);
                lDzCodeList.Add(cDzType);
            }

            richTableName.Table.Refresh();
            setTablesVisble();
        }

        /// <summary>
        /// 指标代码改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboZBCode_SelectedValueChanged(object sender, EventArgs e)
        {
            batchAdd(sender, DZZBRichTable, cboZBCode);
        }

        /// <summary>
        /// 初始化分页表
        /// </summary>
        private void initTables() 
        { 
            Dictionary<string, string> rowsDic = new Dictionary<string, string>();
            rowsDic.Add("数量", "N_AMOUNT");
            rowsDic.Add("成本", "N_PORT_COST");
            rowsDic.Add("市值", "N_PORT_MV");
            rowsDic.Add("行情标志", "N_QUOT_LOGO");
            rowsDic.Add("行情价格", "N_VA_PRICE");
            rowsDic.Add("估值增值", "N_PORT_IV");
            rowsDic.Add("成本占净值比", "N_CB_JZ_BL");
            rowsDic.Add("市值占净值比", "N_SZ_JZ_BL");
            initTable(GZBTable, rowsDic);

            rowsDic = new Dictionary<string, string>();
            rowsDic.Add("本期数", "N_CUR_VALUE");
            rowsDic.Add("本年数", "N_TOL_VALUE");
            initTable(LRBTable, rowsDic);

            rowsDic = new Dictionary<string, string>();
            rowsDic.Add("本期数", "N_CUR_VALUE");
            rowsDic.Add("本年数", "N_TOL_VALUE");
            initTable(JZCBDBTable, rowsDic);


            rowsDic = new Dictionary<string, string>();
            rowsDic.Add("期初值", "N_BEGIN_VALUE");
            rowsDic.Add("期末值", "N_END_VALUE");
            initTable(ZCFZBTable, rowsDic);

            rowsDic = new Dictionary<string, string>();
            rowsDic.Add("本期实收基金", "N_THIS_NAV");
            rowsDic.Add("本期未分配利润", "N_THIS_UNPROFIT");
            rowsDic.Add("本期所有者权益", "N_THIS_INTERESTS");
            rowsDic.Add("上期实收基金", "N_LAST_NAV");
            rowsDic.Add("上期未分配利润", "N_LAST_UNPROFIT");
            rowsDic.Add("上期所有者权益", "N_LAST_INTERESTS");
            initTable(SYZQYBDBTable, rowsDic);

        }

        /// <summary>
        /// 初始化适用范围Table界面
        /// </summary>
        /// <param name="table">table</param>
        /// <param name="rowsDic">元素名称和对应name</param>
        private void initTable(Table table, Dictionary<string, string> rowsDic)
        {
            Column column0 = new CheckBoxColumn();
            column0.DataPropertyName = "ShowRowCheckBoxColumn";
            column0.Width = 20;
            table.Columns.Add(column0);

            Column column1 = new Column();
            column1.DataPropertyName = "ysName";
            column1.Text = "元素名称";
            column1.Width = 200;
            table.Columns.Add(column1);

            table.AllowColumnDrag = false;
            table.AllowResizeColumn = true;
            table.AutoColumnWidth = true;
            table.DefaultToolStripItems = Yss.KTable.Enums.SysToolStripItems.None;
            table.Refresh();


            List<Yss.KTable.Models.Row> rowlist = new List<Yss.KTable.Models.Row>();

            foreach (Yss.KTable.Models.Row row in table.Rows)
            {
                int aa = row.Cells.Count;
                if (row.RowName != null)
                {
                    rowlist.Add(row);
                }
            }

            int cellsCount = table.Columns.Count;
            int count = rowlist.Count;
            for (int a = 0; a < count; a++)
            {
                table.Rows.Remove(rowlist[a], true);
            }

            foreach (var item in rowsDic)
            {
                newTableRows(table, item.Key, item.Value);
            }
        }

        /// <summary>
        /// 加载行
        /// </summary>
        /// <param name="table">table</param>
        /// <param name="text">text</param>
        /// <param name="name">name</param>
        private void newTableRows(Table table, string text, string name) 
        {
            Row row = new Row();
            row.Cells.Add(new Cell());
            Cell cell = new Cell();
            cell.Text = text;
            cell.Name = name;
            row.Cells.Add(cell);

            row.Checked = true;
            row.Selected = true;
            row.RowName = text;
            table.Rows.Add(row);
        }

        /// <summary>
        ///  setTablesVisble
        /// </summary>
        private void setTablesVisble() 
        {
            HashSet<string> htDzCodeHashSet = new HashSet<string>(lDzCodeList);
            tabItemGZB.Visible = false;
            tabItemLRB.Visible = false;
            tabItemZCFZB.Visible = false;
            tabItemSYZQYBDB.Visible = false;
            tabItemJZCBDB.Visible = false;
            this.tabItemGZB.Text = "估值表";
            if (htDzCodeHashSet.Count == 0)
            {
                panelEx2.Visible = false;
            }
            else
            {
                panelEx2.Visible = true;
            }

            foreach (string cDzCode in htDzCodeHashSet)
            {
                if (cDzCode.Equals("1011"))
                { ////估值表
                    tabItemGZB.Visible = true;
                    tabConPanGZB.Visible = true;
                }
                else if (cDzCode.Equals("1013"))
                { ////双估值表
                    this.tabItemGZB.Text = "双估值表";
                    tabItemGZB.Visible = true;
                    tabConPanGZB.Visible = true;
                }
                else if (cDzCode.Equals("1801") || cDzCode.Equals("1811"))
                { ////利润表
                    tabItemLRB.Visible = true;
                    tabConPanLRB.Visible = true;
                 }
                else if (cDzCode.Equals("1903"))
                { //净资产变动表
                    tabItemJZCBDB.Visible = true;
                    this.tabControlPanelJZCBDB.Visible = true;
                }
                else if (cDzCode.Equals("1701") || cDzCode.Equals("1711"))
                { ////资产负债表
                     tabItemZCFZB.Visible = true;
                     tabConPanZCFZB.Visible = true;
                }
                else if (cDzCode.Equals("1901"))
                { ////所有者权益变动表
                    tabItemSYZQYBDB.Visible = true;
                    tabConPanSYZQYBDB.Visible = true;
                }
                else
                { ////BUG #235220 [ 指标关联设置 ]"指标个性设置"set界面黑屏 addby mazhongyuan 2018-12-26
                    panelEx2.Visible = false;
                }
            }

            panelEx2.Refresh();
        }

    }
}
