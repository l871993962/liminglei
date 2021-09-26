package com.yss.uco.elecreco.er.erunport.service;

import java.util.List;
import java.util.Set;

import com.yss.framework.api.mvc.biz.IServiceBus;

public interface IErUnPortService extends IServiceBus {

	public String insertListByPortCodes(List<String> portCodeList);

	public String deletByPortCodes(List<String> portCodeList);

	/**
	 * ���˵��Ѿ�����������˵���ϴ���
	 * BUG244963���Ӷ��˹������������ҳ��ѡ��ݵ��������˿��Ա����������������
	 * @param portCode
	 * @return Set<String> δ����������˵����
	 */
	public Set<String> filterUnPortCodes(List<String> portCodeList);
	
	/**
	 * 判断是手工对账还是电子对账
	 * @param portCode
	 * @return
	 */
	public boolean queryByCode(String portCode);
}
