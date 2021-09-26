package com.yss.uco.elecreco.er.reverse.map.kmrela.pojo;

import java.util.List;

import com.yss.uco.elecreco.er.reverse.map.kmmap.pojo.KmMap;
import com.yss.framework.api.common.co.AuditableParamPojo;

public class KmRelaRecord extends AuditableParamPojo  {
	private static final long serialVersionUID = 1L;

	/**
	 * 科目映射关系
	 * */
	private KmRela kmRela = null;

	/**
	 * 外部科目
	 * */
	private List<KmMap> list_KM_OUT = null;
	/**
	 * 内部科目
	 * */
	private List<KmMap> list_KM_INNER = null;

	
	public List<KmMap> getList_KM_OUT() {
		return list_KM_OUT;
	}
	public void setList_KM_OUT(List<KmMap> list_KM_OUT) {
		this.list_KM_OUT = list_KM_OUT;
	}
	public List<KmMap> getList_KM_INNER() {
		return list_KM_INNER;
	}
	public void setList_KM_INNER(List<KmMap> list_KM_INNER) {
		this.list_KM_INNER = list_KM_INNER;
	}
	public KmRela getKmRela() {
//		this.kmRela.setAuditDate(this.getAuditDate());
//		this.kmRela.setAuditState(getAuditState());
//		this.kmRela.setEndUseDate(getEndUseDate());
//		this.kmRela.setModifier(getModifier());
//		this.kmRela.setModifyDate(getModifyDate());
//		this.kmRela.setOperator(getOperator());
//		this.kmRela.setStartUseDate(getStartUseDate());
		return kmRela;
	}
	public void setKmRela(KmRela kmRela) {
		this.kmRela = kmRela;
	}

	public KmRela getKmRelaWithKmRelaRecord() {
		this.kmRela.setAuditDate(this.getAuditDate());
		this.kmRela.setAuditState(getAuditState());
		this.kmRela.setEndUseDate(getEndUseDate());
		this.kmRela.setModifier(getModifier());
		this.kmRela.setModifyDate(getModifyDate());
		this.kmRela.setOperator(getOperator());
		this.kmRela.setStartUseDate(getStartUseDate());
		return kmRela;
	}
	
	public void setKmRelaRecordWithKmRela(KmRela kmRela) {
		this.setAuditDate(kmRela.getAuditDate());
		this.setAuditState(kmRela.getAuditState());
		this.setEndUseDate(kmRela.getEndUseDate());
		this.setModifier(kmRela.getModifier());
		this.setModifyDate(kmRela.getModifyDate());
		this.setOperator(kmRela.getOperator());
		this.setStartUseDate(kmRela.getStartUseDate());
	}
	
}
