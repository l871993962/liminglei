package com.yss.ifa.szt.tool.subscriber.cons;


public enum TopicCons {

		//BUG #357085 [300.7]static相关代码修改以便支持集群部署
		ER_INFO("ER_INFO"),
		
		NULL("");
		private String value;
		
		private TopicCons(String value){
			this.value=value;
		}
		
		public String toString(){
			return this.value.toString();
		}
		
		public TopicCons setValue(String value){
			for(TopicCons t:TopicCons.values()){
				if(t.value.equals(value))
					return t;
			}
			return null;
		}
}
