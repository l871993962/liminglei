package com.yss.uco.elecreco.er.reverse.map.kmrela.service.impl;
import com.yss.uco.elecreco.er.reverse.map.kmrela.dao.KmRelaDao;
import com.yss.uco.elecreco.er.reverse.map.kmrela.dao.KmRelaSqlBuilder;
import com.yss.uco.elecreco.er.reverse.map.kmrela.service.IKmRelaService;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;

public class KmRelaService extends ServiceBus<KmRelaService> implements IKmRelaService {

	private KmRelaDao serviceDao = null;
	//private Connection conn;
	public KmRelaService() throws Exception {
		serviceDao = new KmRelaDao(DbPoolFactory.getInstance().getPool(),new KmRelaSqlBuilder());
		dao = serviceDao;
	}
//	/***
//	 * 供KmRelaRecordService控制事物使用
//	 * @param conn
//	 * @throws Exception
//	 */
//	public KmRelaService(Connection conn) throws Exception {
//		serviceDao = new KmRelaDao(DbPoolFactory.getInstance().getPool(),new KmRelaSqlBuilder());
//		dao = serviceDao;
//		this.conn = conn;
//	}
	
	
	
	

}