using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;
namespace YssElecReco.Pojo.Er
{
	public class ErSplitRela : AuditableParamPojo  
	{

		///<summary>
		///投资组合
		///</summary>
		private string c_PORT_CODE = "";
		///<summary>
		///托管银行
		///</summary>
		private string c_TGH_CODE = "";
		///<summary>
		///拆分代码
		///</summary>
		private string c_SPLIT_CODE = "";
		///<summary>
		///生效日期
		///</summary>
		private string d_START_DATE = "";
		///<summary>
		///失效日期
		///</summary>
		private string d_END_DATE = "";
		///<summary>
		///
		///</summary>
		[JsonProperty(PropertyName = "c_PORT_CODE")]
		public string C_PORT_CODE{
			set { c_PORT_CODE = value; }
			get { return c_PORT_CODE; }
		}
		///<summary>
		///
		///</summary>
		[JsonProperty(PropertyName = "c_TGH_CODE")]
		public string C_TGH_CODE{
			set { c_TGH_CODE = value; }
			get { return c_TGH_CODE; }
		}
		///<summary>
		///
		///</summary>
		[JsonProperty(PropertyName = "c_SPLIT_CODE")]
		public string C_SPLIT_CODE{
			set { c_SPLIT_CODE = value; }
			get { return c_SPLIT_CODE; }
		}
		///<summary>
		///
		///</summary>
		[JsonProperty(PropertyName = "d_START_DATE")]
		public string D_START_DATE{
			set { d_START_DATE = value; }
			get { return d_START_DATE; }
		}
		///<summary>
		///
		///</summary>
		[JsonProperty(PropertyName = "d_END_DATE")]
		public string D_END_DATE{
			set { d_END_DATE = value; }
			get { return d_END_DATE; }
		}
	}
}