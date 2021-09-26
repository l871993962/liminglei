package com.yss.uco.elecreco.support.util;

public enum FileTypeEnum {
	
	YB("03","月报"),
	JB("04","季报"),
	BNB("05","半年报"),
	NB("06","年报"),
	JYSJ("A01","交易数据"),

	YEB("1001", "余额表"), 
	GZB("1011", "估值表"), 
	KMB("1031", "科目表"), 
	ZCFZB("1701", "资产负债表"),
	LRB("1801","利润表"),
	ZCFZBXZZ("1711", "资产负债表（年金工行）"),
	JYYJBXZZ("1811","经营业绩表（年金工行）"),
	SGZB("1013","双估值表"),
	JZCBDB("1903","净资产变动表"),
	SYZQYB("1901","所有者权益表"),
	YHJJYCF("A001","银行间交易拆分");
 
	private final String key;
	private final String value;
 
	public String getKey() {
		return key;
	}
 
	public String getValue() {
		return value;
	}
 
	FileTypeEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
 
	/**
	 * 根据key获取value
	 * 
	 * @param key
	 *            : 键值key
	 * @return String
	 */
	public static String getValueByKey(String key) {
		FileTypeEnum[] enums = FileTypeEnum.values();
		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getKey().equalsIgnoreCase(key)) {
				return enums[i].getValue();
			}
		}
		return "";
	}
	public static void main(String[] args) {
		System.out.print(FileTypeEnum.getValueByKey("1001"));
	}
}
