package in.co.itasca.hms.persistence.model.utilities;

import java.util.HashMap;
import java.util.Map;

import in.co.itasca.hms.persistence.model.Department;
import in.co.itasca.hms.persistence.model.IDBEntity;

public class DepartmentsUtility  implements IUtility{

	public  final String ID="Id";
	public  final String NAME="Name";
	public  final String DESCRIPTION="Description";
	public  final String CREATEDBY="CreatedBy";
	public  final String CREATEDON="CreatedOn";
	public  final String CHANGEDBY="ChangedBy";
	public  final String CHANGEDON="ChangedOn";
	public  final String ACTIVE="Active";
	private  final long serialVersionUID = 1L;
	
	 
	
	public void populate(Map<String, Object> mapData, Department entity){
		entity.setActive((1));
 
		entity.setChangedBy((String)mapData.get(CHANGEDBY));
		entity.setCreatedBy((String)mapData.get(CREATEDBY));
//		Calendar cal = (Calendar) mapData.get(CREATEDON);
		
	//	this.setCreatedOn(new Date(cal.get, month, date));
		entity.setDescription((String)mapData.get(DESCRIPTION));
	//	this.setIddepartment((int)mapData.get(ID));
		entity.setName((String)mapData.get(NAME));
		
		
	}	

	
	
	




	public DepartmentsUtility() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map<String, Object> convertModelTOEDM(Map<String, Object> map,
			IDBEntity lentity) {
		try {
			Department entity = (Department) lentity;
			map.put(ACTIVE, (entity.getActive()==1)? true : false);
			map.put(CHANGEDBY, (entity.getChangedBy()!=null)?entity.getChangedBy(): "");
			
	 	//map.put(CHANGEDON, "");
			
			
			map.put(CREATEDBY, entity.getCreatedBy());
			map.put(CREATEDON, entity.getCreatedOn());
			map.put(DESCRIPTION, entity.getDescription());
			map.put(ID, entity.getIddepartment());
			map.put(NAME, entity.getName());
			return map;
			} catch (Exception e) {
				return null;	}
			
	}

	@Override
	public void populate(Map<String, Object> mapData, IDBEntity entity) {
		// TODO Auto-generated method stub
		
	}

}
