package in.co.itasca.hms.api.service;

 

import in.co.itasca.hms.persistence.dao.TestRatesMasterDAO;
import in.co.itasca.hms.persistence.dao.PatientCategoryMasterDAO;
import in.co.itasca.hms.persistence.dao.TestMasterDAO;
import in.co.itasca.hms.persistence.dao.TestRatesMasterDAO;
import in.co.itasca.hms.persistence.model.Department;
import in.co.itasca.hms.persistence.model.Patientscategorymaster;
import in.co.itasca.hms.persistence.model.Testmaster;
import in.co.itasca.hms.persistence.model.Testratesmaster;
import in.co.itasca.hms.persistence.model.utilities.DepartmentsUtility;
import in.co.itasca.hms.persistence.model.utilities.TestMasterUtility;
import in.co.itasca.hms.persistence.model.utilities.TestRatesMasterUtility;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TestRatesMasterService extends BaseService{

	
	public TestRatesMasterService(String role) {
		super(role);
		super.idao= new TestRatesMasterDAO();
		super.utility= new TestMasterUtility();
	}

	@Override
	public List<Map<String, Object>> getRecords() {
		try {
			List<Map<String, Object>> collection = new ArrayList<Map<String, Object>>();
			TestRatesMasterDAO dao = (TestRatesMasterDAO) idao;
					
			List<Testratesmaster> list =dao.getAll();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Department entity = (Department) iterator.next();
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
			TestRatesMasterDAO dao = (TestRatesMasterDAO) idao;
				Testratesmaster entity =			dao.getById(id);
			Map<String, Object> map= new HashMap<String, Object>();
			 return utility.convertModelTOEDM(map, entity);	
			 } catch (Exception e) {
return null;
		}
	}

	@Override
	public Map<String, Object> createNewRecord(Map<String, Object> record) {
		try {
			Department entity = new Department();
						Map<String, Object> map= new HashMap<String, Object>();
			utility.populate(map, entity);
			DepartmentsUtility ut = (DepartmentsUtility) utility;
			entity.setIddepartment(0);
			entity.setCreatedOn(new Date());
			entity.setCreatedBy("SANJAY");
			TestRatesMasterDAO dao =(TestRatesMasterDAO) super.idao;
		//	entity =dao.saveNew(entity);
			 			Map<String, Object> map1= new HashMap<String, Object>();
				 return utility.convertModelTOEDM(map1, entity);
			
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Map<String, Object> updateRecord(int id, Map<String, Object> record) {
		try {
			TestRatesMasterDAO dao = (TestRatesMasterDAO) idao;
			Testratesmaster entity ;
			entity =dao.getById(id);
			TestRatesMasterUtility ut = (TestRatesMasterUtility) utility;
			Boolean active = (Boolean) record.get(ut.ACTIVE);
			Integer idPatCatInt	=  (Integer) record.get(ut.IDPATIENTCATEGORYMASTER);
			int idPatientCategory =idPatCatInt.intValue();
			PatientCategoryMasterDAO daoPatientcategory = new PatientCategoryMasterDAO();
			Patientscategorymaster entityPatientCategory =daoPatientcategory.getById(idPatientCategory);
			entity.setPatientscategorymaster(entityPatientCategory);
			Integer idTestMastInt = (Integer) record.get(ut.IDTESTMASTER);
			int idTestMaster = idTestMastInt.intValue();
			TestMasterDAO daoTestMaster = new TestMasterDAO();
			Testmaster entityTestMaster =daoTestMaster.getById(idTestMaster);
			entity.setTestmaster(entityTestMaster);
			BigDecimal rateBigDecimal = (BigDecimal) record.get(ut.RATE);
			float rate = rateBigDecimal.floatValue();
			entity.setRate(rate);
			entity.setValidFrom(new Date());
			entity.setValidTo(new Date());
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
			TestRatesMasterDAO dao = (TestRatesMasterDAO) idao;
			Testratesmaster entity =dao.getById(id);
		dao.delete(entity);
		return true;
		} catch (Exception e) {
			return false;
		}
 
	}

}
