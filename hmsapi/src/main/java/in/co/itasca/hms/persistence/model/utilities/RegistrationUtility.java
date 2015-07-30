package in.co.itasca.hms.persistence.model.utilities;

import java.util.Map;
 

import in.co.itasca.hms.persistence.model.IDBEntity;
import in.co.itasca.hms.persistence.model.Registration;

public class RegistrationUtility implements IUtility {

	public RegistrationUtility() {
		// TODO Auto-generated constructor stub
	}
	
	public static final String ID="Id";
	public static final String IDDEPARTMENT="IdDepartment";

	public  final String NAMEDEPARTMENT="NameDepartment";
	
	public  final String NAME="PatientName";
	public  final String AGE="PatientAge";
	public  final String AGEINWORDS="PatientAgeIn";
	public  final String VALIDFROM="ValidFrom";
	public  final String VALIDTO="ValidTo";
	public  final String CREATEDBY="CreatedBy";
	public  final String CREATEDON="CreatedOn";
	public  final String CHANGEDBY="ChangedBy";
	public  final String CHANGEDON="ChangedOn";
	public  final String PRICE="Price";
	public  final String ACTIVE="Active";

	public  final String IDPAIENTCATEGORY="IdPatientCategory";
	public  final String NAMEPAIENTCATEGORY="NamePatientCategory";
	public  final String IDSEX="IdSex";
	public  final String NAMESEX="NameSex";
	@Override
	public Map<String, Object> convertModelTOEDM(Map<String, Object> map,
			IDBEntity lentity) {
		 try {
			 ConversionUtility cu = ConversionUtility.getInstance();
			 Registration entity = (Registration) lentity; 
		 	map.put(ACTIVE	, cu.active(entity.getActive()));
		 	map.put(ID,entity.getIdregistration());
		 	 map.put(CREATEDBY, entity.getCreatedBy());
		 	map.put(CREATEDON, entity.getCreatedOn());
		 	
		 	map.put(AGEINWORDS, entity.getAgein());
		 	map.put(AGE, entity.getAgedate());
		 	map.put(CHANGEDBY, entity.getChangedBy());
		 	map.put(CHANGEDON, entity.getChangedOn());
		 	map.put(NAME, entity.getName());
		 	map.put(PRICE, entity.getPrice());
		 	map.put(IDPAIENTCATEGORY, entity.getRegistrationfeemaster().getPatientscategorymaster().getIdpatientsType());
		 	map.put(NAMEPAIENTCATEGORY, entity.getRegistrationfeemaster().getPatientscategorymaster().getPatientTypeName());
		 	
		 	map.put(IDSEX, entity.getSexBean().getIdsex());
		 	map.put(NAMESEX, entity.getSexBean().getSexText());
		 	
		 	map.put(IDDEPARTMENT, entity.getDepartment().getIddepartment());
		 	map.put(NAMEDEPARTMENT, entity.getDepartment().getName());
		 	 	
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
