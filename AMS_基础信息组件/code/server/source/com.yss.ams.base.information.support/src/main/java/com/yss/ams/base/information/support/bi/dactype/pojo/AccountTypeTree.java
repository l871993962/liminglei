package com.yss.ams.base.information.support.bi.dactype.pojo;

public class AccountTypeTree extends AccountType{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		//zhangyongzhao  产品生命周期树形结构需要
		/**
		 * 层级
		 */
		private int level ;
		
		/**
		 * 是否是叶子节点 0否 1是
		 * @return
		 */
		private int connect_by_isleaf ;
		
		/**
		 * 父节点
		 */
		private String pId = "";

		/**
		 * 名称
		 * @return
		 */
		private String name = "";
		
		private int n_ISACCOUNT = 0 ; 
		
		//是否是叶子节点
		private boolean leaf = true;
		
		public int getN_ISACCOUNT() {
			return n_ISACCOUNT;
		}

		public void setN_ISACCOUNT(int n_ISACCOUNT) {
			this.n_ISACCOUNT = n_ISACCOUNT;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getpId() {
			return pId;
		}

		public void setpId(String pId) {
			this.pId = pId;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}

		public int getConnect_by_isleaf() {
			return connect_by_isleaf;
		}

		public void setConnect_by_isleaf(int connect_by_isleaf) {
			this.connect_by_isleaf = connect_by_isleaf;
		}

		public boolean isLeaf() {
			return leaf;
		}

		public void setLeaf(boolean leaf) {
			this.leaf = leaf;
		}

}
