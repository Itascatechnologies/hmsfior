package in.co.itasca.hms.api.service;

import in.co.itasca.hms.persistence.dao.PatientCategoryMasterDAO;
import in.co.itasca.hms.persistence.dao.PatientCategoryMasterDAO;
import in.co.itasca.hms.persistence.model.Department;
import in.co.itasca.hms.persistence.model.Patientscategorymaster;
import in.co.itasca.hms.persistence.model.Registrationfeemaster;
import in.co.itasca.hms.persistence.model.utilities.IUtility;
import in.co.itasca.hms.persistence.model.utilities.PatientCategoryMasterUtility;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static in.co.itasca.hms.api.Constants.ENTITYSET_REGISTRATIONFEEMASTER;
public class PatientCategoryMasterService extends BaseService{
	
//	public static final String ID="Id";
//	public static final String PATIENTTYPENAME="patientTypeName";
//	public static final String PATIENTTYPEDESCRIPTION="patientTypeDescription";
//	public static final String CREATEDBY="createdBy";
//    public static final String CREATEDON="createdOn";
//    public static final String CHANGEDBY="changedBy";
//    public static final String CHANGEDON="changedOn";
//    public static final String ACTIVE="active";

 
	public PatientCategoryMasterService(String role) {
		super(role);
		super.idao= new PatientCategoryMasterDAO();
		super.utility= new PatientCategoryMasterUtility();
	}

	@Override
	public List<Map<String, Object>> getRecords() {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		PatientCategoryMasterUtility ut = (PatientCategoryMasterUtility) super.utility;
		try {
			
			PatientCategoryMasterDAO dao = (PatientCategoryMasterDAO) idao;
			List<Patientscategorymaster> coll = dao.getAll();
			for (Iterator iterator = coll.iterator(); iterator.hasNext();) {
				Patientscategorymaster entity = (Patientscategorymaster) iterator
						.next();
			Map<String, Object> data = new HashMap<String, Object>();
//			data.put(ut.ACTIVE, entity.getActive());
			data.put(ut.CHANGEDBY, (entity.getChangedBy()==null?"" : entity.getChangedBy()));
			//data.put(entity.CHANGEDBY, entity.getChangedBy());
			data.put(ut.CHANGEDON, (entity.getChangedOn()!=null)?entity.getChangedOn() : null);
			data.put(ut.CREATEDBY, (entity.getCreatedBy()==null?"" : entity.getChangedBy()));
			data.put(ut.CREATEDON, (entity.getCreatedOn()!=null)?entity.getChangedOn() : null);
			data.put(ut.DESCRIPTION, entity.getPatientsTypeDescription())	;
			data.put(ut.PATIENTTYPENAME, entity.getPatientTypeName());
			data.put(ut.ID, entity.getIdpatientsType());
			result.add(data);
			}
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> getAssociation(int id, String associatedEdm) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		
		try {

			if(associatedEdm.equals(ENTITYSET_REGISTRATIONFEEMASTER))
			{
				PatientCategoryMasterDAO dao = (PatientCategoryMasterDAO) idao;
				Patientscategorymaster entity =dao.getById(id);
				Registrationfeemaster entityRegistrationFee =entity.getRegistrationfeemasters().get(0);
					Map<String, Object> map= new HashMap<String, Object>();
				result.add(utility.convertModelTOEDM(map, entityRegistrationFee));
			}
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Map<String, Object> getRecord(int id) {
		try {
			PatientCategoryMasterDAO dao = (PatientCategoryMasterDAO) idao;
				Patientscategorymaster entity =			dao.getById(id);
							Map<String, Object> map= new HashMap<String, Object>();
				return utility.convertModelTOEDM(map , entity);
		} catch (Exception e) {
return null;
		}
	}

	@Override
	public Map<String, Object> createNewRecord(Map<String, Object> record) {
		try {
			
			
			Patientscategorymaster entity = new Patientscategorymaster();
			PatientCategoryMasterUtility ut = (PatientCategoryMasterUtility) utility;
			entity.setActive(1);
			entity.setPatientsTypeDescription((String) record.get(ut.DESCRIPTION));
			entity.setPatientTypeName((String) record.get(ut.PATIENTTYPENAME));
			entity.setCreatedOn(new Date());
			entity.setCreatedBy("SANJAY");
			PatientCategoryMasterDAO dao =(PatientCategoryMasterDAO) super.idao;
			entity =dao.saveNew(entity);
						Map<String, Object> map= new HashMap<String, Object>();
			return ut.convertModelTOEDM(map, entity);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Map<String, Object> updateRecord(int id, Map<String, Object> record) {
		try {
			PatientCategoryMasterDAO dao =(PatientCategoryMasterDAO) super.idao;
			PatientCategoryMasterUtility ut = (PatientCategoryMasterUtility) utility;
			Patientscategorymaster entity = dao.getById(id);
			entity.setActive(1);
			entity.setPatientsTypeDescription((String) record.get(ut.DESCRIPTION));
			entity.setPatientTypeName((String) record.get(ut.PATIENTTYPENAME));
			entity.setChangedBy("amit");
			entity.setChangedOn(new Date());
					entity =dao.save(entity);
						Map<String, Object> map= new HashMap<String, Object>();
			return utility.convertModelTOEDM(map, entity);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean deleteUpdate(int id) {
		try {
			PatientCategoryMasterDAO dao = (PatientCategoryMasterDAO) idao;
			Patientscategorymaster entity =dao.getById(id);
			
		dao.delete(entity);
		return true;
		} catch (Exception e) {
			return false;
		}
 
	}

}
