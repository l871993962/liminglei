using System;
using System.Collections.Generic;
using System.Collections;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Reflection;
using System.ComponentModel;
using Yss.KRichEx;
using Newtonsoft.Json.Linq;
using Yss.KRichEx.AutoFilter;
using FAST.Core.Context;
using FAST.Common.Service.Services;

////namespace YssBaseCls.Fun
namespace YssInformation.Support.Fun
{   
    /// <summary>
    /// 前台查询条件组装类
    /// </summary>
    public class ClsQuyStrUtil
    {
        /// <summary>
        /// 查询条件的Hashtable
        /// </summary>
        public Hashtable quyConHush = new Hashtable();
        
        /// <summary>
        /// 查询条件组的Hashtable
        /// </summary>
        public Hashtable quyConGroupHush = new Hashtable();
        
        /// <summary>
        /// 条件序号
        /// </summary>
        public int quyConCnt;
        
        /// <summary>
        /// 查询条件组中条件序号
        /// </summary>
        public int quyConGrpCnt;

        /// <summary>
        /// 条件组序号
        /// </summary>
        public int groupCount;

        /// <summary>
        /// 构造函数，用于初始化条件查询的集合和条件JSON字符串
        /// </summary>
        public ClsQuyStrUtil() 
        {
            quyConHush = new Hashtable();
            quyConGroupHush = new Hashtable();
            ClsContext.quyConJsonStr = "";
            quyConCnt = 0;
            quyConGrpCnt = 0;
            groupCount = 1;
            
        }

        /// <summary>
        /// 初始化内部参数
        /// </summary>
        public void init() 
        {
            quyConHush = new Hashtable();
            quyConGroupHush = new Hashtable();
            ClsContext.quyConJsonStr = "";
            quyConCnt = 0;
            quyConGrpCnt = 0;
            groupCount = 1;
        }

        /// <summary>
        /// 向查询条件集合添加条件
        /// </summary>
        /// <param name="conName">条件名</param>
        /// <param name="fieldName">字段名</param>
        /// <param name="conValue">条件值</param>
        /// <param name="hyphen">条件连字符</param>
        /// <param name="specStr">特殊处理字符串</param>
        public void addQuyCon(string conName, string fieldName, string conValue, string hyphen, string specStr)
        {
            if (!isConValueEmpty(conValue))
            { 
                ////拼接json对象的字符串
                fieldName = fieldName.ToUpper();
                string contentStr = "\"" + conName + "\":{\"name\":\"" + fieldName + "\", \"value\":\"" + conValue + "\", \"hyphen\":\"" + hyphen + "\", \"alias\":\"\", \"specStr\":\"" + specStr + "\"}";
                ////将字符串添加到hash表中
                quyConHush.Add(quyConCnt.ToString(), contentStr);
                quyConCnt += 1;
            }
        }

        /// <summary>
        /// 向查询条件集合添加条件（默认字段名称为查询名称）
        /// </summary>
        /// <param name="fieldName">字段名</param>
        /// <param name="conValue">条件值</param>
        /// <param name="hyphen">条件连字符</param>
        public void addQuyCon(string fieldName, string conValue, string hyphen)
        {
            if (!isConValueEmpty(conValue))
            {
                fieldName = fieldName.ToUpper();
                ////拼接json对象的字符串
                string contentStr = "\"" + fieldName + "\":{\"name\":\"" + fieldName + "\", \"value\":\"" + conValue + "\", \"hyphen\":\"" + hyphen + "\", \"alias\":\"\", \"specStr\":\"\"}";
                ////将字符串添加到hash表中
                quyConHush.Add(quyConCnt.ToString(), contentStr);
                quyConCnt += 1;
            }
        }

        /// <summary>
        /// 向查询条件集合添加条件（无特殊条件）
        /// </summary>
        /// <param name="conName">条件名</param>
        /// <param name="fieldName">字段名</param>
        /// <param name="conValue">条件值</param>
        /// <param name="hyphen">条件连字符</param>
        public void addQuyCon(string conName, string fieldName, string conValue, string hyphen)
        {
            if (!isConValueEmpty(conValue))
            {
                fieldName = fieldName.ToUpper();
                ////拼接json对象的字符串
                string contentStr = "\"" + conName + "\":{\"name\":\"" + fieldName + "\", \"value\":\"" + conValue + "\", \"hyphen\":\"" + hyphen + "\", \"alias\":\"\", \"specStr\":\"\"}";
                ////将字符串添加到hash表中
                quyConHush.Add(quyConCnt.ToString(), contentStr);
                quyConCnt += 1;
            }
        }

        /////// <summary>
        /////// 向查询条件集合添加条件（无特殊条件）
        /////// </summary>
        /////// <param name="fieldName">字段名</param>
        /////// <param name="conValue">条件值</param>
        /////// <param name="hyphen">条件连字符</param>
        /////// <param name="specStr">特殊处理串</param>
        ////public void addQuyCon(string fieldName, string conValue, string hyphen, string specStr)
        ////{
        ////    if (!isConValueEmpty(conValue))
        ////    {
        ////        fieldName = fieldName.ToUpper();
        ////        ////拼接json对象的字符串
        ////        string contentStr = "\"" + fieldName + "\":{\"name\":\"" + fieldName + "\", \"value\":\"" + conValue + "\", \"hyphen\":\"" + hyphen + "\", \"alias\":\"\", \"specStr\":\"" + specStr + "\"}";
        ////        ////将字符串添加到hash表中
        ////        quyConHush.Add(quyConCnt.ToString(), contentStr);
        ////        quyConCnt += 1;
        ////    }
        ////}

        /// <summary>
        /// 向查询条件集合添加条件（根据传入控件值获取查询对象的值）
        /// </summary>
        /// <param name="name">查询条件关联的数据库对象</param>
        /// <param name="ctl">查询条件控件</param>
        /// <param name="hyphen">查询条件关系连字符</param>
        public void addQuyCon(string name, Control ctl, string hyphen) 
        {
            string ctlValue = getContrlValue(ctl);
            if (ctlValue != null && !("").Equals(ctlValue) && !("-9999").Equals(ctlValue)) 
            {
                name = name.ToUpper();
                ////拼接json对象的字符串
                string contentStr = "\"" + name + "\":{\"name\":\"" + name + "\", \"value\":\"" + ctlValue + "\", \"hyphen\":\"" + hyphen + "\", \"alias\":\"\", \"specStr\":\"\"}";
                ////将字符串添加到hash表中
                quyConHush.Add(quyConCnt.ToString(), contentStr);
                quyConCnt += 1;
            }
        }

        /// <summary>
        /// 向查询条件集合添加条件（根据传入控件值获取查询对象的值）
        /// </summary>
        /// <param name="name">查询条件关联的数据库对象</param>
        /// <param name="ctl">查询条件控件</param>
        /// <param name="hyphen">查询条件关系连字符</param>
        /// <param name="specStr">查询条件特殊处理符</param>
        public void addQuyCon(string name, Control ctl, string hyphen, string specStr) 
        {
            string ctlValue = getContrlValue(ctl);
            if (ctlValue != null && !("").Equals(ctlValue) && !("-9999").Equals(ctlValue))
            {
                name = name.ToUpper();
                ////拼接json对象的字符串
                string contentStr = "\"" + name + "\":{\"name\":\"" + name + "\", \"value\":\"" + ctlValue + "\", \"hyphen\":\"" + hyphen + "\", \"alias\":\"\", \"specStr\":\"" + specStr + "\"}";
                ////将字符串添加到hash表中
                quyConHush.Add(quyConCnt.ToString(), contentStr);
                quyConCnt += 1;
            }
        }



        /// <summary>
        /// 添加条件组，用于拼接复杂的sql
        /// </summary>
        /// <param name="fieldName">字段名</param>
        /// <param name="conValue">字段值</param>
        /// <param name="hyphen">关系符</param>
        public void addQuyConGroup(string fieldName, string conValue, string hyphen)
        {
            if (!isConValueEmpty(conValue)) 
            {
                fieldName = fieldName.ToUpper();
                ////拼接json对象的字符串
                string contentStr = "\"" + fieldName + "\":{\"name\":\"" + fieldName + "\", \"value\":\"" + conValue + "\", \"hyphen\":\"" + hyphen + "\", \"alias\":\"\", \"specStr\":\"\"}";
                ////将字符串添加到hash表中
                quyConGroupHush.Add(quyConGrpCnt.ToString(), contentStr);
                quyConGrpCnt += 1;
            }
        }

        /// <summary>
        /// 添加条件组，用于拼接复杂的sql
        /// </summary>
        /// <param name="fieldName">字段名</param>
        /// <param name="conValue">字段值</param>
        /// <param name="hyphen">关系符</param>
        /// <param name="specStr">特殊处理标志</param>
        public void addQuyConGroup(string fieldName, string conValue, string hyphen, string specStr)
        {
            if (!isConValueEmpty(conValue))
            {
                fieldName = fieldName.ToUpper();
                ////拼接json对象的字符串
                string contentStr = "\"" + fieldName + "\":{\"name\":\"" + fieldName + "\", \"value\":\"" + conValue + "\", \"hyphen\":\"" + hyphen + "\", \"alias\":\"\", \"specStr\":\"" + specStr + "\"}";
                ////将字符串添加到hash表中
                quyConGroupHush.Add(quyConGrpCnt.ToString(), contentStr);
                quyConGrpCnt += 1;
            }
        }

        /// <summary>
        /// 添加条件组
        /// </summary>
        /// <param name="groupHyphen">条件组关联符</param>
        public void addGroup(string groupHyphen) 
        {
            string jsoString = "\"quyGroup_" + groupCount + "\":{\"quyCons\":{";
            ////将条件组的Hash表
            for (int i = 0; i < quyConGrpCnt; i++) 
            {
                string jsoKey = (i).ToString();
                jsoString += quyConGroupHush[jsoKey] + ",";
            }
            
            jsoString = jsoString.Substring(0, (jsoString.Length - 1)) + "}, groupHyphen:\"" + groupHyphen + "\"}";

            ////将条件组解析成对象字符串放入HashTable中
            quyConHush.Add(quyConCnt.ToString(), jsoString);
            quyConCnt += 1;
            groupCount += 1;
            ////重置条件组信息
            quyConGrpCnt = 0;
            quyConGroupHush = new Hashtable();
        }

 
        /// <summary>
        /// 添加查询条件（审核状态）
        /// </summary>
        /// <param name="value">值</param>
        /// <param name="hyphen">连字符</param>
        public void addQuyConChkSta(string value, string hyphen) 
        {
            string contentStr = "\"checkState\":{\"name\":\"checkState\", \"value\":\"" + value + "\", \"hyphen\":\"" + hyphen + "\", \"alias\":\"\", \"specStr\":\"\"}";
            quyConHush.Add(quyConCnt.ToString(), contentStr);
        }


       /// <summary>
       /// 根据查询条件的集合，组装符合JSON对象规格的字符串。
       /// </summary>
       /// <param name="funcode">模块编号</param>
        public void buildQuyStr(string funcode)
        {
            ClsContext.quyConJsonStr = "qunConJsonObject{";
            setCheckStatus(funcode);
            ////setCheckBy(funcode);
            ////遍历hash表中所有的元素，组装json字符串
            for (int i = 0; i < quyConCnt; i++) 
            {
                ClsContext.quyConJsonStr = ClsContext.quyConJsonStr + quyConHush[i] + ",";
            }

            ClsContext.quyConJsonStr = ClsContext.quyConJsonStr.Substring(0, (ClsContext.quyConJsonStr.Length - 1)) + "}";
        }

        /// <summary>
        /// 根据查询条件的集合，组装符合JSON对象规格的字符串。(不加默认条件)
        /// </summary>
        /// <returns>生成的JSON对象字符串</returns>
        public string getQuyStr()
        {
            string jsoString = "";
            if (quyConHush.Count > 0) 
            {
                jsoString = "qunConJsonObject{";
                ////遍历hash表中所有的元素，组装json字符串
                for (int i = 0; i < quyConCnt; i++)
                {
                    string jsoKey = (i).ToString();
                    jsoString += quyConHush[jsoKey] + ",";
                }

                jsoString = jsoString.Substring(0, (jsoString.Length - 1)) + "}";

            }
            
            return jsoString;
        }

        /// <summary>
        /// 根据查询条件的集合，组装符合JSON对象规格的字符串。(加默认条件)
        /// </summary>
        /// <param name="funcode">模块编号</param>
        /// <returns>生成的JSON对象字符串</returns>
        public string getQuyStr(string funcode)
        {
            string jsoString = "qunConJsonObject{";
            setCheckStatus(funcode);
            ////遍历hash表中所有的元素，组装json字符串
            for (int i = 0; i < quyConCnt; i++)
            {
                string jsoKey = (i).ToString();
                jsoString += quyConHush[jsoKey] + ",";
            }
            
            jsoString = jsoString.Substring(0, (jsoString.Length - 1)) + "}";

            return jsoString;
        }

        /// <summary>
        /// 根据查询条件的集合，组装符合JSON对象规格的字符串。(不加默认条件)
        /// </summary>
        /// <returns>生成的JSON对象字符串</returns>
        public string getExtraStaStr()
        {
            string jsoString = "";
            if (quyConHush.Count > 0)
            {
                jsoString = "exStaJsonObject{";
                ////遍历hash表中所有的元素，组装json字符串
                for (int i = 0; i < quyConCnt; i++)
                {
                    string jsoKey = (i).ToString();
                    jsoString += quyConHush[jsoKey] + ",";
                }

                jsoString = jsoString.Substring(0, (jsoString.Length - 1)) + "}";

            }

            return jsoString;
        }

        /// <summary>
        /// 获取jobjec对象
        /// </summary>
        /// <returns>jobject对象</returns>
        public JObject getJObject() 
        {
            string jsoString = "";
            if (quyConHush.Count > 0)
            {
                jsoString = "{";
                ////遍历hash表中所有的元素，组装json字符串
                for (int i = 0; i < quyConCnt; i++)
                {
                    string jsoKey = (i).ToString();
                    jsoString += quyConHush[jsoKey] + ",";
                }

                jsoString = jsoString.Substring(0, (jsoString.Length - 1)) + "}";

            }

            JObject jso = new JObject();
            jso = JObject.Parse(jsoString);
            return jso;
        }

        /// <summary>
        /// 获取审核状态的查询对象
        /// </summary>
        /// <param name="chkState">审核状态枚举</param>
        /// <returns>jobject对象</returns>
        public JObject getStateQuyConObj(ClsEnums.CheckState chkState) 
        {
            JObject jobj = new JObject();

            jobj.Add("name", "N_CHECK_STATE");
            jobj.Add("alias", "");
            jobj.Add("specStr", "");
            switch (chkState) 
            {
                case ClsEnums.CheckState.AUDIT:
                    jobj.Add("value", ClsConstant._CHK_STA_VERIFIED);
                    jobj.Add("hyphen", ClsConstant.SQL_RA_HYPHEN_EQUAL);
                    break;
                case ClsEnums.CheckState.UNAUDIT:
                    jobj.Add("value", ClsConstant._CHK_STA_UNVERIFIED);
                    jobj.Add("hyphen", ClsConstant.SQL_RA_HYPHEN_EQUAL);
                    break;
                case ClsEnums.CheckState.ALL:
                    jobj.Add("value", ClsConstant._CHK_STA_UNVERIFIED);
                    jobj.Add("hyphen", ClsConstant.SQL_RA_HYPHEN_NOT_LESS_THAN);
                    break;
            }

            return jobj;
        }

        /// <summary>
        /// 组装查询条件对象时判断查询条件集合中是否有审核状态
        /// </summary>
        /// <param name="funcode">模块编号</param>
        private void setCheckStatus(string funcode)
        { 
            string checkStatusStr = "";
            ////如果是回收站模块，审核状态为-1,如果不是回收站模块，审核状态为〉=0
            if (funcode.Equals("recycle"))
            {
                checkStatusStr = "\"checkState\":{\"name\":\"n_check_state\", \"value\":\"" + ClsConstant._CHK_STA_RECYCLE + "\", \"hyphen\":\"=\", \"alias\":\"\", \"specStr\":\"\"}";
            }
            else
            {
                checkStatusStr = "\"checkState\":{\"name\":\"n_check_state\", \"value\":\"" + ClsConstant._CHK_STA_UNVERIFIED + "\", \"hyphen\":\">=\", \"alias\":\"\", \"specStr\":\"\"}";
            }

            quyConHush.Add(quyConCnt.ToString(), checkStatusStr);
            quyConCnt += 1;
        }

        /// <summary>
        /// 获取传入控件的值
        /// </summary>
        /// <param name="ctl">传入控件</param>
        /// <returns>控件值</returns>
        private string getContrlValue(Control ctl) 
        {
            string ctlValue = "";
            Type ctlType = ctl.GetType();
            if (ctlType == typeof(AutoComboBox))
            {
                AutoComboBox selCombox = ctl as AutoComboBox;
                ctlValue = selCombox.Value;
            }
            else if (ctlType == typeof(IDateTimeInterval))
            {
                IDateTimeInterval dateTimeInt = ctl as IDateTimeInterval;
                ctlValue = dateTimeInt.getBeginDateStr + "," + dateTimeInt.getEndDateStr;
            }
            else if (ctlType == typeof(YssTextBox))
            {
                YssTextBox textbox = ctl as YssTextBox;
                ctlValue = textbox.Text;
            }

            return ctlValue;
        }

        /// <summary>
        /// 判断传入条件值是否为空
        /// </summary>
        /// <param name="conValue">条件值</param>
        /// <returns>判断结果</returns>
        private bool isConValueEmpty(string conValue) 
        {
            bool emptyFlag = false;
            if (conValue == null) 
            {
                emptyFlag = true;
            }

            return emptyFlag;
        }
    }
}
