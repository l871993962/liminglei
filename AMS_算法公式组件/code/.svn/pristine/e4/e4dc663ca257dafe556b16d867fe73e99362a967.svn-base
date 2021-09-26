package com.yss.ams.api.deploy.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.deploy.IAdmin;

public class AdminFactory {
	private static AdminFactory factory = null;
	
	public final static String ADMIN_TYPE_API="API";
	
	public static AdminFactory getInstance(){
		if(factory == null){
			factory = new AdminFactory();
		}
		
		return factory;
	}
	
	private AdminFactory(){}
	
	private Map<String, List<IAdmin>> admins = new HashMap<String,List<IAdmin>>();
	
	@SuppressWarnings("unchecked")
	public<T extends IAdmin> T getAdmin(String type){
		if(type.equalsIgnoreCase(ADMIN_TYPE_API)){
			List<IAdmin> bundleAdmins = this.admins.get(ADMIN_TYPE_API);
			if(bundleAdmins == null){
				bundleAdmins = new ArrayList<IAdmin>();
				this.admins.put(ADMIN_TYPE_API, bundleAdmins);
			}
			
			if(bundleAdmins.size() == 0){
				bundleAdmins.add(new DeployAdmin());
			}
			
			return (T) bundleAdmins.get(0);
		}
		
		return null;
	}
}
