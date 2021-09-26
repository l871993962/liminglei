using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
 
using FAST.Common.Service.Pojo;
using FAST.Core.Context.Events;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Context;
using FAST.Core.Util;
using FAST.Core.Resource;
using FAST.Common.Service.Interface;
using YssInformation.Support.Interface;
using Yss.KRichEx.AutoFilter.Events;
using YssInformation.Support.Fun;
using YssInformation.Support.Context;

namespace YssInformation.Support.Pojo
{
    /// <summary>
    /// 核算元素加载明细项目的基类
    /// </summary>
    public class ClsBaseDaeDeail : IDaeDetailLoader
    {
        /// <summary>
        /// 核算元素
        /// </summary>
        protected Cls_DAE_ELEM clsDae = null;

        /////// <summary>
        /////// 当前科目编码
        /////// </summary>
        ////private string currSpec = "";

        /// <summary>
        /// 控件参数
        /// </summary>
        private ClsAssocia _clsAssocia = null;

        /// <summary>
        /// 参数
        /// </summary>
        private string parameter = "";

        /// <summary>
        /// 是否加载明细数据
        /// </summary>
        private bool isLoadDetail = false;

        /// <summary>
        /// 操作的明细控件
        /// </summary>
        private FAST.Core.BaseControl.YssSelCombox cboDetail = null;

        /// <summary>
        /// 关联控件
        /// </summary>
        protected Dictionary<string, System.Windows.Forms.Control> collection;

        /// <summary>
        /// 科目辅助元素关联控件
        /// add by Yuntao Lau 2015.12.01 STORY #26998
        /// </summary>
        protected Dictionary<string, System.Windows.Forms.Control> collectionAuxDae;
        /// 关联核算元素
        /// </summary>
        private I_DAE_FIELD clsDaeField = null;

        /// <summary>
        /// 关联科目辅助元素
        /// add by Yuntao Lau 2015.12.01 STORY #26998
        /// </summary>
        private I_KMAux clsAuxDaeField = null;

        /// <summary>
        /// 关联核算元素
        /// </summary>
        public I_DAE_FIELD DaeFactor
        {
            get
            {
                return clsDaeField;
            }

            set
            {
                clsDaeField = value;
            }
        }

        /// <summary>
        /// 关联科目辅助元素
        /// add by Yuntao Lau 2015.12.01 STORY #26998
        /// </summary>
        public I_KMAux AuxDaeFactor
        {
            get
            {
                return clsAuxDaeField;
            }

            set
            {
                clsAuxDaeField = value;
            }
        }

        /// <summary>
        /// 是否加载明细数据
        /// </summary>
        /// <param name="bLoadDetail">值为真表示加载明细数据</param>
        public void loadDetailData(bool bLoadDetail)
        {
            isLoadDetail = bLoadDetail;
        }        

        #region IDaeDetailLoader 成员

        /// <summary>
        /// set Parameters
        /// </summary>
        /// <param name="clsDae">clsDae</param>
        /// <param name="parame">parame</param>
        /// <param name="cboDetail">cboDetail</param>
        public void setParameter(Cls_DAE_ELEM clsDae, string parame, FAST.Core.BaseControl.YssSelCombox cboDetail)
        {
            this.clsDae = clsDae;
            this.cboDetail = cboDetail;
            this.parameter = parame;
            setParam(cboDetail);
            cboDetail.YssOnBeforeLoadData += new FAST.Core.BaseControl.YssSelCombox.yssBeforeLoadData(cboDetail_YssOnBeforeLoadData);
            cboDetail.TextChanged += new EventHandler(cboDetail_TextChanged);
            cboDetail.AfterDropDownClick += new DropDownEventHandler(cboDetail_AfterDropDownClick);
        }

        #endregion  

        /// <summary>
        /// 数据加载前事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboDetail_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            if (!isLoadDetail)
            {
                if (e.Pojos == null)
                {
                    e.Pojos = new List<BasePojo>();
                }

                ////if (loadNAflag())
                ////{
                ////    YssCore.Interface.IBasePojo pojoNA = (YssCore.Interface.IBasePojo)ReflectBase.getInstance(_clsAssocia.PojoDllName.Length > 0 ? _clsAssocia.PojoDllName : ClsFunction.getDllName(_clsAssocia.PojoClsName), _clsAssocia.PojoClsName);
                ////    ReflectBase.setAttrValue(_clsAssocia.Sel_DisplayName, pojoNA, ("[-NA-]"));
                ////    ReflectBase.setAttrValue(_clsAssocia.Sel_DisplayValue, pojoNA, ("[NA]"));
                ////    e.Pojos.Add(pojoNA);
                ////}

                ////YssCore.Interface.IBasePojo pojo = (YssCore.Interface.IBasePojo)ReflectBase.getInstance(_clsAssocia.PojoDllName.Length > 0 ? _clsAssocia.PojoDllName : ClsFunction.getDllName(_clsAssocia.PojoClsName), _clsAssocia.PojoClsName);
                ////ReflectBase.setAttrValue(_clsAssocia.Sel_DisplayName, pojo, ("<" + clsDae.C_DAE_NAME + ">"));
                ////ReflectBase.setAttrValue(_clsAssocia.Sel_DisplayValue, pojo, ("<" + clsDae.C_DAE_CODE + ">"));
                ////e.Pojos.Add(pojo);

                beforeLoadOther(e.Pojos);

                e.IsCancel = isCancelLoadData();
            }

            if (this.parameter != null)
            {
                cboDetail.QueryCond = buildConds(cboDetail, this.parameter);
            }

        }

        /// <summary>
        /// 设置参数
        /// </summary>
        /// <param name="cboDetail">下拉控件</param>
        private void setParam(FAST.Core.BaseControl.YssSelCombox cboDetail)
        {
            _clsAssocia = new ClsAssocia();
            Associa associaType = setAssociaType(cboDetail);
            _clsAssocia = ClsClzCfgMgr.getAssociaParam(associaType);
            cboDetail.YssAssociaType = associaType;
            cboDetail.setAssocia(_clsAssocia);
            cboDetail.KTableTree = false;
            cboDetail.SelectMode = Yss.KRichEx.AutoFilter.SelectMode.Detail;
            //// 设置树形信息
            setTreeDetail(cboDetail);

            cboDetail.Parameter = _clsAssocia.Sel_DisplayColumns; //// edit by Yuntao Lau 2015.11.17
            cboDetail.DisplayValue = _clsAssocia.Sel_DisplayValue;
            cboDetail.DisplayName = _clsAssocia.Sel_DisplayName;
            cboDetail.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
            cboDetail.RequestEveryTime = false;
            if (this.parameter != null)
            {
                cboDetail.QueryCond = buildConds(cboDetail, this.parameter);
            }
        }

        /// <summary>
        /// cboDetail_AfterDropDownClick
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboDetail_AfterDropDownClick(object sender, EventArgs e)
        {
            filterItem(cboDetail);            
        }

        /// <summary>
        /// cboDetail_YssTextChanged
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboDetail_TextChanged(object sender, EventArgs e)
        {
            filterItem(cboDetail);  
            ////需求 14409 手工凭证非必输项清理 By jinghehe
            if (cboDetail.Name.Equals(ClsDaeMapping.SEC_VAR))
            {
                if (collection[Cons.NAME_DAE].Controls[ClsDaeMapping.DTA] != null
                           && collection[Cons.NAME_DAE].Controls[ClsDaeMapping.DTA] is FAST.Core.BaseControl.YssSelCombox)
                {
                    FAST.Core.BaseControl.YssSelCombox yssSelDtaBox = collection[Cons.NAME_DAE].Controls[ClsDaeMapping.DTA] as FAST.Core.BaseControl.YssSelCombox;
                    yssSelDtaBox.Refresh();
                }
            }
        }

        /// <summary>
        /// 加载数据前的加载其他的参数
        /// </summary>
        /// <param name="listPojo">装载数据的集合</param>
        protected virtual void beforeLoadOther(List<BasePojo> listPojo)
        {
            if (loadNAflag())
            {
                BasePojo pojoNA = (BasePojo)ReflectBase.getInstance(_clsAssocia.DataServiceClassDll.Length > 0 ? _clsAssocia.DataServiceClassDll : ClsFunction.getDllName(_clsAssocia.DataServiceClass), _clsAssocia.DataServiceClass);
                ReflectBase.setAttrValue(_clsAssocia.Sel_DisplayName, pojoNA, ("[-NA-]"));
                ReflectBase.setAttrValue(_clsAssocia.Sel_DisplayValue, pojoNA, Cons.DAE_NA);
                listPojo.Add(pojoNA);
            }

            if (loadDaeflag())
            {
                BasePojo pojo = (BasePojo)ReflectBase.getInstance(_clsAssocia.DataServiceClassDll.Length > 0 ? _clsAssocia.DataServiceClassDll : ClsFunction.getDllName(_clsAssocia.DataServiceClass), _clsAssocia.DataServiceClass);
                ReflectBase.setAttrValue(_clsAssocia.Sel_DisplayName, pojo, ("<" + clsDae.C_DAE_NAME + ">"));
                ReflectBase.setAttrValue(_clsAssocia.Sel_DisplayValue, pojo, ("<" + clsDae.C_DAE_CODE + ">"));
                listPojo.Add(pojo);
            }
        }

        /// <summary>
        /// 设置参数
        /// </summary>
        /// <param name="currDetail">下拉框</param>
        /// <returns>返回类型</returns>
        protected virtual Associa setAssociaType(FAST.Core.BaseControl.YssSelCombox currDetail)
        {
            return AssociaType.NULL;
        }

        /// <summary>
        /// 生成查询条件
        /// </summary>
        /// <param name="currDetail">当前操作的控件</param>
        /// <param name="parameter">参数</param>
        /// <returns>查询条件信息</returns>
        protected virtual string buildConds(FAST.Core.BaseControl.YssSelCombox currDetail, string parameter)
        {
            return parameter;
        }

        /// <summary>
        /// 根据本控件的值设置关联控件的值
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        public virtual void setCtlValue(object sender, EventArgs e)
        {
        }

        /// <summary>
        /// 是否取消加载明细数据
        /// </summary>
        /// <returns>布尔值，值为真表示不加载</returns>
        protected virtual bool isCancelLoadData()
        {
            return false;
        }

        /// <summary>
        /// 是否加载NA任意值标识
        /// </summary>
        /// <returns>默认加载</returns>
        protected virtual bool loadNAflag()
        {
            return true;
        }       

        /// <summary>
        /// 是否加载核算项标识
        /// add by Yuntao Lau 2015.12.01 STORY #26998
        /// </summary>
        /// <returns>默认加载</returns>
        protected virtual bool loadDaeflag()
        {
            return true;
        }
        /// 过滤已加载的项目
        /// </summary>
        /// <param name="currDetail">currDetail</param>
        protected virtual void filterItem(FAST.Core.BaseControl.YssSelCombox currDetail)
        {
        }

        /// <summary>
        /// 设置控件树形下拉属性
        /// </summary>
        /// <param name="currDetail">下拉框</param>
        protected virtual void setTreeDetail(FAST.Core.BaseControl.YssSelCombox currDetail)
        {
        }


        #region IRelation 成员
        /// <summary>
        /// 设置关联关系
        /// </summary>
        /// <param name="collection">关联控件</param>
        public void setRelaCtl(Dictionary<string, System.Windows.Forms.Control> collection)
        {
            if (collection.ContainsKey(Cons.NAME_AUX_DAE))
            {
                this.collectionAuxDae = collection;
            }
            else
            {
                this.collection = collection;
            }
        }

        #endregion




        #region IDaeDetailLoader 成员

        void IDaeDetailLoader.setParameter(Cls_DAE_ELEM clsDae, string parame, FAST.Core.BaseControl.YssSelCombox cboDetail)
        {
            throw new NotImplementedException();
        }

        #endregion

        #region IRelation 成员

        void FAST.Common.Service.IRelation.setRelaCtl(Dictionary<string, System.Windows.Forms.Control> collection)
        {
            throw new NotImplementedException();
        }

        #endregion

        #region IDaeFactor 成员

        I_DAE_FIELD IDaeFactor.DaeFactor
        {
            get
            {
                throw new NotImplementedException();
            }
            set
            {
                throw new NotImplementedException();
            }
        }

        I_KMAux IDaeFactor.AuxDaeFactor
        {
            get
            {
                throw new NotImplementedException();
            }
            set
            {
                throw new NotImplementedException();
            }
        }

        #endregion
    }
}