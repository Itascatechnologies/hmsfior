package in.co.itasca.hms.api.service;

import in.co.itasca.hms.persistence.dao.DepartmentDAO;
import in.co.itasca.hms.persistence.dao.TestMasterDAO;
import in.co.itasca.hms.persistence.dao.TestMasterDAO;
import in.co.itasca.hms.persistence.dao.TestMasterDAO;
import in.co.itasca.hms.persistence.model.Department;
import in.co.itasca.hms.persistence.model.Testmaster;
import in.co.itasca.hms.persistence.model.utilities.IUtility;
import in.co.itasca.hms.persistence.model.utilities.TestMasterUtility;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TestMasterService extends BaseService{

 
	public TestMasterService(String role) {
		super(role);
		super.idao= new TestMasterDAO();
		super.utility= new TestMasterUtility();
	}

	@Override
	public List<Map<String, Object>> getRecords() {
		try {
				
			List<Map<String, Object>> collection = new ArrayList<Map<String, Object>>();
			TestMasterDAO dao = (TestMasterDAO) idao;
					
			List<Testmaster> list =dao.getAll();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Testmaster entity = (Testmaster) iterator.next();
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
			TestMasterDAO dao = (TestMasterDAO) idao;
				Testmaster entity =			dao.getById(id);
							Map<String, Object> map= new HashMap<String, Object>();
				return utility.convertModelTOEDM(map, entity);
		} catch (Exception e) {
return null;
		}
	}

	@Override
	public Map<String, Object> createNewRecord(Map<String, Object> record) {
		try {
			Testmaster entity = new Testmaster();
			TestMasterUtility ut = (TestMasterUtility) utility;
			ut.populate(record, entity);
			Integer idDepInt = (Integer) record.get(ut.IDDEPARTMENT);;
			int idDepartment = idDepInt.intValue();
			DepartmentDAO daoDepartment = new DepartmentDAO();
			Department entityDepartment =daoDepartment.getById(idDepartment);
			entity.setDepartment(entityDepartment);
			entity.setCreatedOn(new Date());
			entity.setCreatedBy("SANJAY");
			TestMasterDAO dao =(TestMasterDAO) super.idao;
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
			TestMasterDAO dao = (TestMasterDAO) idao;
			Testmaster entity ;
			entity =dao.getById(id);
			TestMasterUtility ut = (TestMasterUtility) utility;
			Boolean active = (Boolean) record.get(ut.ACTIVE);
			entity.setChangedBy("AMIT");
			entity.setChangedON(new Date());
			entity.setTestName((String) record.get(ut.TESTNAME));
			entity.setTestDescription((String) record.get(ut.TESTDESCRIPTION));
			Integer idDepInt = (Integer) record.get(ut.IDDEPARTMENT);
			int idDepartment = idDepInt.intValue();
						Map<String, Object> map= new HashMap<String, Object>();
		 			return ut.convertModelTOEDM(map, entity);
			 
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public boolean deleteUpdate(int id) {
		try {
			TestMasterDAO dao = (TestMasterDAO) idao;
			Testmaster entity =dao.getById(id);
		dao.delete(entity);
		return true;
		} catch (Exception e) {
			return false;
		}
 
	}

}
