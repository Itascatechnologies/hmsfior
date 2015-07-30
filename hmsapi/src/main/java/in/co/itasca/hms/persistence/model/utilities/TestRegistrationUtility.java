package in.co.itasca.hms.persistence.model.utilities;

import java.util.Map;

import in.co.itasca.hms.persistence.model.IDBEntity;
import in.co.itasca.hms.persistence.model.Testregistration;

public class TestRegistrationUtility implements IUtility {

	public TestRegistrationUtility() {
		// TODO Auto-generated constructor stub
	}

	public   final String ID="Id";
	public  final String IDRATESMASTER="IdRatesMaster";
	@Override
	public Map<String, Object> convertModelTOEDM(Map<String, Object> map,
			IDBEntity lentity) {
		try {
			Testregistration entity = (Testregistration) lentity;
			
			
			map.put(ID,entity.getIdtest());
		//	map.put(IDRATESMASTER,entity.gettestratesmaster.getIdtestrates());
			return map;
			} catch (Exception e) {
				return null;	}
			
			}
	
	@Override
	public void populate(Map<String, Object> mapData, IDBEntity entity) {
		// TODO Auto-generated method stub
		
	}

}
