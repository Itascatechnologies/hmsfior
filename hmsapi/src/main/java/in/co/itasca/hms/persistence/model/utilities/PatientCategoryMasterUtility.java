package in.co.itasca.hms.persistence.model.utilities;

import java.util.Map;

import in.co.itasca.hms.persistence.model.IDBEntity;
import in.co.itasca.hms.persistence.model.Patientscategorymaster;

public class PatientCategoryMasterUtility implements IUtility {

	public PatientCategoryMasterUtility() {
		
	}

	
	 

	public  final String ID="Id";
	public  final String PATIENTTYPENAME="Name";
	public  final String DESCRIPTION="Description";
	public  final String CREATEDBY="CreatedBy";
   public  final String CREATEDON="CreatedOn";
   public  final String CHANGEDBY="ChangedBy";
   public  final String CHANGEDON="ChangedOn";
   public  final String ACTIVE="Active";
	 


 
	@Override
	public void populate(Map<String, Object> mapData, IDBEntity entity) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public Map<String, Object> convertModelTOEDM(Map<String, Object> map,
			IDBEntity entity1) {

		try {
			Patientscategorymaster entity = (Patientscategorymaster) entity1;
		
		map.put(ACTIVE, (entity.getActive()==1)? true : false);
		map.put(CHANGEDBY, (entity.getChangedBy()!=null)?entity.getChangedBy(): "");
		map.put(CREATEDBY, entity.getCreatedBy());

		map.put(ID, entity.getIdpatientsType());
		map.put(DESCRIPTION, entity.getPatientsTypeDescription());
		map.put(PATIENTTYPENAME,entity.getPatientTypeName());
		map.put(CREATEDON, entity.getCreatedOn());
		return map;
		} catch (Exception e) {
			return null;	}
		
	}


}
