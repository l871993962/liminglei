using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using System;
using System.Collections.Generic;
using System.Text;
using System.Text.RegularExpressions;

namespace YssVisAval.Pojo.AA
{
    /// <summary>
    /// 公式配置关键字
    /// </summary>
    public class ClsKeyWord
    {
        /// <summary>
        /// 关键字代码
        /// </summary>
        private string _keyWordCode = null;

        /// <summary>
        /// 关键字名称
        /// </summary>
        private string _keyWordName = null;

        /// <summary>
        /// 关键字类型
        /// </summary>
        private string _keyWordTYpe = null;

        /// <summary>
        /// 关键字描述
        /// </summary>
        private string _keyWordDesc = null;

        /// <summary>
        /// 关键字的使用
        /// </summary>
        private string _keyWordUsed = null;

        /// <summary>
        /// 前台展示
        /// </summary>
        private string _keyWordShow = null;

        /// <summary>
        /// 前台展示
        /// </summary>
        public string KeyWordShow
        {
            get { return _keyWordShow; }
            set { _keyWordShow = value; }
        }

        /// <summary>
        /// 关键字的使用
        /// </summary>
        public string KeyWordUsed
        {
            get { return _keyWordUsed; }
            set { _keyWordUsed = value; }
        }

        /// <summary>
        /// 关键字描述
        /// </summary>
        public string KeyWordDesc
        {
            get { return _keyWordDesc; }
            set { _keyWordDesc = value; }
        }

        /// <summary>
        /// 关键字类型
        /// </summary>
        public string KeyWordTYpe
        {
            get { return _keyWordTYpe; }
            set { _keyWordTYpe = value; }
        }

        /// <summary>
        /// 关键字代码
        /// </summary>
        public string KeyWordCode
        {
            get { return _keyWordCode; }
            set { _keyWordCode = value; }
        }

        /// <summary>
        /// 关键字名称
        /// </summary>
        public string KeyWordName
        {
            get { return _keyWordName; }
            set { _keyWordName = value; }
        }

        /// <summary>
        /// 解析后台传递过来的字符串
        /// </summary>
        /// <param name="respStr">respStr</param>
        public void parseStr(string respStr)
        {
            // / 将后台传来的字符串封装成实体类
            string[] tmpAry = Regex.Split(respStr, "\t");
            _keyWordCode = tmpAry[0];
            _keyWordName = tmpAry[1];
            _keyWordTYpe = tmpAry[2];
            _keyWordDesc = tmpAry[3];
            _keyWordUsed = tmpAry[4];
            _keyWordShow = tmpAry[5];
        }
    }
}


