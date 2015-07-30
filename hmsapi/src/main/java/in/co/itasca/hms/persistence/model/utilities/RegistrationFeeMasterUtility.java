package in.co.itasca.hms.persistence.model.utilities;

import in.co.itasca.hms.persistence.model.IDBEntity;
import in.co.itasca.hms.persistence.model.Registrationfeemaster;

import java.util.Map;

public class RegistrationFeeMasterUtility implements IUtility {

	public RegistrationFeeMasterUtility() {
		// TODO Auto-generated constructor stub
	}
	
	public  final String ID="Id";
	public  final String NAME="Name";
	public  final String FEE="Fee";
	public  final String CREATEDON="CreatedOn";
	public  final String CREATEDBY="CreatedBy";
	public  final String ACTIVE="Active";
	public  final String APPROVEDBY="ApprovedBy";
	public  final String APPROVEDON="Approvedby";
	public  final String VALIDITYDAYS="ValidityDays";
	public  final String IDPAIENTCATEGORY= "IdPatientCategory";
	public  final String NAMEPATIENTCATEGORY="NamePatientCategory";
	@Override
	public Map<String, Object> convertModelTOEDM(Map<String, Object> map,
			IDBEntity lentity) {
		 try {
			 ConversionUtility cu = ConversionUtility.getInstance();
			 Registrationfeemaster entity = (Registrationfeemaster) lentity;
			 
		 	map.put(ACTIVE	, cu.active(entity.getActive()));
		 	map.put(ID,entity.getId());
		 	map.put(APPROVEDBY, entity.getApprovedBy());
		 	map.put(APPROVEDON, entity.getApprovedOn());
		 	map.put(CREATEDBY, entity.getCreatedBy());
		 	map.put(CREATEDON, entity.getCreatedOn());
		 	
		 	map.put(FEE, entity.getFee());
//		 	map.put(NAME, ge)
		 	map.put(VALIDITYDAYS, entity.getValidityDays());
		 	map.put(IDPAIENTCATEGORY, entity.getPatientscategorymaster().getIdpatientsType());
		 	map.put(NAMEPATIENTCATEGORY, entity.getPatientscategorymaster().getPatientTypeName());
		 	 return map;
		 } catch (Exception e) {
		 return null;
		 }
	}
	@Override
	public void populate(Map<String, Object> mapData, IDBEntity entity) {
		// TODO Auto-generated method stub
		
	}
	 

}
