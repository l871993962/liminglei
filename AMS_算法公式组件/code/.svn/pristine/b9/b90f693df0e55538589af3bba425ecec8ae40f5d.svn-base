package com.yss.ams.visaval.support.util.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * AlgoAPI.xml封装对象
 * 此对象仅供解析XML封装数据临时使用
 * @author 马向峰
 * 
 */
@XmlRootElement(name = "Api")
public class AlgoAPIU {

	/**
	 * 对应节点 algos
	 */
	@XmlElement(name = "algos")
	private AlgosU algos;

	@XmlElement(name = "functions")
	private APIFunsU apiFuns;
	
	@XmlTransient
	public AlgosU getAlgos() {
		return algos;
	}

	public void setAlgos(AlgosU algos) {
		this.algos = algos;
	}

	@XmlTransient
	public APIFunsU getApiFuns() {
		return apiFuns;
	}

	public void setApiFuns(APIFunsU apiFuns) {
		this.apiFuns = apiFuns;
	}

}
