package in.co.itasca.hms.api.service;

import in.co.itasca.hms.persistence.dao.DepartmentDAO;
import in.co.itasca.hms.persistence.dao.TestRegistrationDAO;
import in.co.itasca.hms.persistence.dao.UsersDAO;
import in.co.itasca.hms.persistence.model.Department;
import in.co.itasca.hms.persistence.model.Testregistration;
import in.co.itasca.hms.persistence.model.User;
import in.co.itasca.hms.persistence.model.utilities.TestRegistrationUtility;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TestRegistrationService extends BaseService{

	public TestRegistrationService(String role) {
		super(role);
		super.idao = new TestRegistrationDAO();
		super.utility= new  TestRegistrationUtility();
	}

	@Override
	public List<Map<String, Object>> getRecords() {
try {
			
			
			List<Map<String, Object>> collection = new ArrayList<Map<String, Object>>();
			TestRegistrationDAO dao = (TestRegistrationDAO) idao;
					
			List<Testregistration> list =dao.getAll();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Testregistration entity = (Testregistration) iterator.next();
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
			TestRegistrationDAO dao = (TestRegistrationDAO) idao;
				Testregistration entity =			dao.getById(id);
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
			utility.populate(record, entity);
			entity.setIddepartment(0);
			entity.setCreatedOn(new Date());
			entity.setCreatedBy("SANJAY");
			DepartmentDAO dao =(DepartmentDAO) super.idao;
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
			TestRegistrationDAO dao = (TestRegistrationDAO) idao;
			
			Testregistration entity ;
			TestRegistrationUtility ut= (TestRegistrationUtility) utility;
			entity =dao.getById(id);
		//	boolean active = (boolean) record.get(ut.ACTIVE);
			entity.setDate(new Date());
		//	entity.setDescription((String) record.get(entity.DESCRIPTION));
		//	entity.setName((String) record.get(entity.NAME));
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
			TestRegistrationDAO dao = (TestRegistrationDAO) idao;
			Testregistration entity =dao.getById(id);
		dao.delete(entity);
		return true;
		} catch (Exception e) {
			return false;
		}
 
	}

}
