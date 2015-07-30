package in.co.itasca.hms.persistence.model.utilities;

import java.util.Map;

import in.co.itasca.hms.persistence.model.IDBEntity;
import in.co.itasca.hms.persistence.model.Testmaster;

public class TestMasterUtility implements IUtility {
	

	public TestMasterUtility() {
		// TODO Auto-generated constructor stub
	}

	
	public  final String ID="Id";
	public  final String TESTNAME="Name";
	public  final String TESTDESCRIPTION="Description";
	public  final String CREATEDBY="CreateBy";
	public  final String CREATEDON="CreatedOn";
	public  final String CHANGEDBY="ChangedBy";
	public  final String CHANGEDON="ChangedOn";
	public  final String IDDEPARTMENT="IdDepartment";
	public  final String ACTIVE="Active";
	public  final String NAMEDEPARTMENT="NameDepartment";
	
	
	 
	
	public void populate(Map<String, Object> mapData, Testmaster entity){
		
		entity.setActive((1));
 		entity.setTestName((String) mapData.get(TESTNAME));
		entity.setTestDescription(TESTDESCRIPTION);
		
		
		
		
	}




	@Override
	public Map<String, Object> convertModelTOEDM(Map<String, Object> map,
			IDBEntity lentity) {

		try {
			
			Testmaster entity = (Testmaster) lentity;
		
		map.put(ACTIVE, (entity.getActive()==1)? true : false);
		map.put(CHANGEDBY, (entity.getChangedBy()!=null)?entity.getChangedBy(): "");
		map.put(CHANGEDON, entity.getChangedON());
		map.put(CREATEDBY, entity.getCreatedBy());
		map.put(CREATEDON, entity.getCreatedOn());
		map.put(IDDEPARTMENT, entity.getDepartment().getIddepartment());
		map.put(NAMEDEPARTMENT, entity.getDepartment().getName());
		map.put(ID, entity.getIdtests() );
		map.put(TESTDESCRIPTION, entity.getTestDescription());
		map.put(TESTNAME, entity.getTestName());
		return map;
		} catch (Exception e) {
			return null;	}
		
	}




	@Override
	public void populate(Map<String, Object> mapData, IDBEntity entity) {
		// TODO Auto-generated method stub
		
	}	



}
