using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;
namespace YssElecReco.Pojo.Er
{
    public class ErSplitRule : AuditableParamPojo  
	{

		///<summary>
		///关联的拆分映射关系
		///</summary>
		private string c_IDEN_RELA = "";
		///<summary>
		///科目代码
		///</summary>
		private string c_KM_CODE = "";
		///<summary>
		///科目名称
		///</summary>
		private string c_KM_NAME = "";
		///<summary>
		///
		///</summary>
		[JsonProperty(PropertyName = "c_IDEN_RELA")]
		public string C_IDEN_RELA{
			set { c_IDEN_RELA = value; }
			get { return c_IDEN_RELA; }
		}
		///<summary>
		///
		///</summary>
		[JsonProperty(PropertyName = "c_KM_CODE")]
		public string C_KM_CODE{
			set { c_KM_CODE = value; }
			get { return c_KM_CODE; }
		}
		///<summary>
		///
		///</summary>
		[JsonProperty(PropertyName = "c_KM_NAME")]
		public string C_KM_NAME{
			set { c_KM_NAME = value; }
			get { return c_KM_NAME; }
		}
	}
}