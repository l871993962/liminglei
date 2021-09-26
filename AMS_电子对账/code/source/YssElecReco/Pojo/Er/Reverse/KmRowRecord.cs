using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;
using Yss.KTable.Models;
using System.Collections;
using Yss.KTable.Collections;

namespace YssElecReco.Pojo.Er.Reverse
{
    /// <summary>
    /// 科目表行记录
    /// </summary>
    public class KmRowRecord  
    {
        /// <summary>
        /// 内部科目行
        /// </summary>
        private List<Row> innerRows = null;

        /// <summary>
        /// 外部科目行
        /// </summary>
        private List<Row> outRows = null;

        /// <summary>
        /// 内部行
        /// </summary>
        public List<Row> InnerRows
        {
            set { innerRows = value; }
            get { return innerRows; }
        }

        /// <summary>
        /// 外部行
        /// </summary>
        public List<Row> OutRows
        {
            set { outRows = value; }
            get { return outRows; }
        }
        
    }
}