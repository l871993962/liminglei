using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;
using FAST.Core.Context;
using System;
using System.Collections.Generic;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Datastructure;


namespace YssVisAval.Pojo.AA
{
    /// <summary>
    /// 高级算法
    /// </summary>
    [NodeDesc(ParentNode = "C_DV_ALGO_TYPE", TreeNode = "C_ALGO_CODE")]
    public class AdvAlgo : EnclosurePojo
    {
         /// <summary>
        /// 算法代码
        /// </summary>
        private string c_ALGO_CODE = null;

        /// <summary>
        /// 算法名称
        /// </summary>
        private string c_ALGO_NAME = null;

        /// <summary>
        /// 算法公式
        /// </summary>
        private string c_ALGO_FORMULA = null;

        /// <summary>
        /// 算法公式对应中文
        /// </summary>
        private string c_ALGO_FORMULA_ZH = null;

        /// <summary>
        /// 对应树的APIcode
        /// </summary>
        private string c_ALGO_API_CODE = null;

        /// <summary>
        /// 描述
        /// </summary>
        private string c_DESC = null;

        /// <summary>
        /// 原算法代码
        /// </summary>
        private string c_OLD_ALGO_CODE = null;

        /// <summary>
        /// 算法类型
        /// </summary>
        private string c_DV_ALGO_TYPE = null;

        /// <summary>
        /// 算法表达式
        /// </summary>
        private string c_ALGO_FORMULA_TRANSFORM = null;

        /// <summary>
        /// 所有参数集合字符串
        /// </summary>
        private string c_ALGO_PARAMS = null;

        /// <summary>
        /// 对应多个下拉框参数
        /// </summary>
        private string c_ALGO_FUN_MORE = null;

       
        /// <summary>
        /// 对应单个下拉框参数
        /// </summary>
        private string c_ALGO_FUN_ONE = null;

        /// <summary>
        /// 记录参数
        /// </summary>
        private string c_ALGO_FUN_PARAMS = null;

        /// <summary>
        /// 新旧算法标记，True为新算法，False为旧算法
        /// </summary>
        private string c_ALGO_IS_NEW = null;

        /// <summary>
        /// 更新目标：是set界面更新还是list界面更新
        /// </summary>
        private string c_ALGO_UPDATE_TARGET = null;

        /// <summary>
        /// 更新目标：是set界面更新还是list界面更新
        /// </summary>
        [JsonProperty(PropertyName = "c_ALGO_UPDATE_TARGET")]
        public string C_ALGO_UPDATE_TARGET
        {
            get { return c_ALGO_UPDATE_TARGET; }
            set { c_ALGO_UPDATE_TARGET = value; }
        }

        /// <summary>
        /// 新旧算法标记，True为新算法，False为旧算法
        /// </summary>
        [JsonProperty(PropertyName = "c_ALGO_IS_NEW")]
        public string C_ALGO_IS_NEW
        {
            get { return c_ALGO_IS_NEW; }
            set { c_ALGO_IS_NEW = value; }
        }

        /// <summary>
        /// 记录参数
        /// </summary>
        [JsonProperty(PropertyName = "c_ALGO_FUN_PARAMS")]
        public string C_ALGO_FUN_PARAMS
        {
            get { return c_ALGO_FUN_PARAMS; }
            set { c_ALGO_FUN_PARAMS = value; }
        }

        /// <summary>
        /// 对应单个下拉框参数
        /// </summary>
        [JsonProperty(PropertyName = "c_ALGO_FUN_ONE")]
        public string C_ALGO_FUN_ONE
        {
            get { return c_ALGO_FUN_ONE; }
            set { c_ALGO_FUN_ONE = value; }
        }

        /// <summary>
        /// 对应多个下拉框参数
        /// </summary>
        [JsonProperty(PropertyName = "c_ALGO_FUN_MORE")]
        public string C_ALGO_FUN_MORE
        {
            get { return c_ALGO_FUN_MORE; }
            set { c_ALGO_FUN_MORE = value; }
        }

        /// <summary>
        /// 对应树的APIcode
        /// </summary>
        [JsonProperty(PropertyName = "c_ALGO_API_CODE")]
        public string C_ALGO_API_CODE
        {
            get { return c_ALGO_API_CODE; }
            set { c_ALGO_API_CODE = value; }
        }

        /// <summary>
        /// 所有参数集合字符串
        /// </summary>
        [JsonProperty(PropertyName = "c_ALGO_PARAMS")]
        public string C_ALGO_PARAMS
        {
            get { return c_ALGO_PARAMS; }
            set { c_ALGO_PARAMS = value; }
        }

        /// <summary>
        /// 算法公式对应中文
        /// </summary>
        [JsonProperty(PropertyName = "c_ALGO_FORMULA_ZH")]
        public string C_ALGO_FORMULA_ZH
        {
            get { return c_ALGO_FORMULA_ZH; }
            set { c_ALGO_FORMULA_ZH = value; }
        }

        /// <summary>
        /// 属性: 算法代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_ALGO_CODE")]
        public string C_ALGO_CODE
        {
            set { c_ALGO_CODE = value; }

            get { return c_ALGO_CODE; }
        }

        /// <summary>
        /// 属性: 算法名称 
        /// </summary>
        [JsonProperty(PropertyName = "c_ALGO_NAME")]
        public string C_ALGO_NAME
        {
            set { c_ALGO_NAME = value; }

            get { return c_ALGO_NAME; }
        }

        /// <summary>
        /// 属性: 算法公式 
        /// </summary>
        [JsonProperty(PropertyName = "c_ALGO_FORMULA")]
        public string C_ALGO_FORMULA
        {
            set { c_ALGO_FORMULA = value; }

            get { return c_ALGO_FORMULA; }
        }

        /// <summary>
        /// 属性: 描述 
        /// </summary>
        [JsonProperty(PropertyName = "c_DESC")]
        public string C_DESC
        {
            set { c_DESC = value; }

            get { return c_DESC; }
        }

        /// <summary>
        /// 属性: 原算法代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_OLD_ALGO_CODE")]
        public string C_OLD_ALGO_CODE
        {
            set { c_OLD_ALGO_CODE = value; }

            get { return c_OLD_ALGO_CODE; }
        }

        /// <summary>
        /// 属性: 算法类型 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_ALGO_TYPE")]
        public string C_DV_ALGO_TYPE
        {
            set { c_DV_ALGO_TYPE = value; }

            get { return c_DV_ALGO_TYPE; }
        }

        /// <summary>
        /// 属性: 算法表达式 
        /// </summary>
        [JsonProperty(PropertyName = "c_ALGO_FORMULA_TRANSFORM")]
        public string C_ALGO_FORMULA_TRANSFORM
        {
            set { c_ALGO_FORMULA_TRANSFORM = value; }

            get { return c_ALGO_FORMULA_TRANSFORM; }
        }


    }
}


