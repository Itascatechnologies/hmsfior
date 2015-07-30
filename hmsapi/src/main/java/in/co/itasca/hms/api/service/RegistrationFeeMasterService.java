package in.co.itasca.hms.api.service;

import in.co.itasca.hms.persistence.dao.RegistrationFeeMasterDAO;
import in.co.itasca.hms.persistence.dao.PatientCategoryMasterDAO;
import in.co.itasca.hms.persistence.dao.RegistrationFeeMasterDAO;
import in.co.itasca.hms.persistence.model.Department;
import in.co.itasca.hms.persistence.model.Patientscategorymaster;
import in.co.itasca.hms.persistence.model.Registrationfeemaster;
import in.co.itasca.hms.persistence.model.utilities.IUtility;
import in.co.itasca.hms.persistence.model.utilities.RegistrationFeeMasterUtility;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RegistrationFeeMasterService extends BaseService {

	 
	public RegistrationFeeMasterService(String role) {
		super(role);
		super.idao= new RegistrationFeeMasterDAO();
 super.utility= new  RegistrationFeeMasterUtility();
	}

	@Override
	public List<Map<String, Object>> getRecords() {
		try {
			List<Map<String, Object>> collection = new ArrayList<Map<String, Object>>();
			RegistrationFeeMasterDAO	 dao = (RegistrationFeeMasterDAO) idao;
				List<Registrationfeemaster> list =dao.getAll();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Registrationfeemaster entity = (Registrationfeemaster) iterator.next();
						Map<String, Object> map= new HashMap<String, Object>();
				collection.add(utility.convertModelTOEDM(map, entity));
			}
			 return collection;
			} catch (Exception e) {
			return  null;
			}
	}

	@Override
	public List<Map<String, Object>> getAssociation(int id, String associatedEdm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getRecord(int id) {
		try {
			RegistrationFeeMasterDAO dao = (RegistrationFeeMasterDAO) idao;
				Registrationfeemaster entity =			dao.getById(id);
							Map<String, Object> map= new HashMap<String, Object>();
							return utility.convertModelTOEDM(map, entity);
							
		} catch (Exception e) {
return null;
		}
	}

	@Override
	public Map<String, Object> createNewRecord(Map<String, Object> record) {
		try {
			Registrationfeemaster entity = new Registrationfeemaster();
			RegistrationFeeMasterUtility ut = (RegistrationFeeMasterUtility) utility;
			entity.setCreatedOn(new Date());
			entity.setCreatedBy("SANJAY");
			java.math.BigDecimal fee= (BigDecimal) record.get(ut.FEE);
			
			entity.setFee(fee.floatValue());
			
			Object obj = record.get(ut.IDPAIENTCATEGORY);
			Integer intvalue= (Integer) obj;
			
			PatientCategoryMasterDAO daoPatientCategoryMaster = new PatientCategoryMasterDAO();
			Patientscategorymaster patientCategoryMasterEntity =daoPatientCategoryMaster.getById(intvalue.intValue());
			entity.setPatientscategorymaster(patientCategoryMasterEntity);
//			entity.set
			RegistrationFeeMasterDAO dao =(RegistrationFeeMasterDAO) idao;
			entity =dao.saveNew(entity);
						Map<String, Object> map= new HashMap<String, Object>();
			return utility.convertModelTOEDM(map, entity);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Map<String, Object> updateRecord(int id, Map<String, Object> record) {
		try {
			RegistrationFeeMasterUtility ut = (RegistrationFeeMasterUtility) utility;

			RegistrationFeeMasterDAO dao = (RegistrationFeeMasterDAO) idao;
			Registrationfeemaster entity ;
			entity =dao.getById(id);
			Integer objIdPatCat = (Integer) record.get(ut.IDPAIENTCATEGORY);
			int idPatientCategory = objIdPatCat.intValue();
			PatientCategoryMasterDAO patientDAO = new PatientCategoryMasterDAO();
			Patientscategorymaster patientCategoryEntity =patientDAO.getById(idPatientCategory);
			entity.setPatientscategorymaster(patientCategoryEntity);
			BigDecimal feeBigDec = (BigDecimal) record.get(ut.FEE);
			float feefloat = feeBigDec.floatValue();
			entity.setFee(feefloat);
			entity =dao.save(entity);
						Map<String, Object> map= new HashMap<String, Object>();
			return utility.convertModelTOEDM(map, entity);
			 
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public boolean deleteUpdate(int id) {
		try {
			RegistrationFeeMasterDAO dao = (RegistrationFeeMasterDAO) idao;
			Registrationfeemaster entity =dao.getById(id);
		dao.delete(entity);
		return true;
		} catch (Exception e) {
			return false;
		}
 
	}

}
