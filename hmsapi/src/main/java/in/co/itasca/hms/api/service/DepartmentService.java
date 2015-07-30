package in.co.itasca.hms.api.service;

import in.co.itasca.hms.persistence.dao.DepartmentDAO;
import in.co.itasca.hms.persistence.dao.MenusDAO;
import in.co.itasca.hms.persistence.model.Department;
import in.co.itasca.hms.persistence.model.Menus;
import in.co.itasca.hms.persistence.model.Testmaster;
import in.co.itasca.hms.persistence.model.utilities.DepartmentsUtility;
import in.co.itasca.hms.persistence.model.utilities.IUtility;
import in.co.itasca.hms.persistence.model.utilities.UtilityFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



import static  in.co.itasca.hms.api.Constants.ENTITYSET_DEPARTMENT;
import static  in.co.itasca.hms.api.Constants.ENTITYSET_TESTMASTER;;

public class DepartmentService extends BaseService{
	


	public DepartmentService(String role) {
		super(role);
		super.idao= new DepartmentDAO(); 
		super.utility= UtilityFactory.getInstance().getInstance(ENTITYSET_DEPARTMENT);
	}

	@Override
	public List<Map<String, Object>> getRecords() {
		try {
			
	
		List<Map<String, Object>> collection = new ArrayList<Map<String, Object>>();
		DepartmentDAO dao = (DepartmentDAO) idao;
		
		List<Department> list =dao.getAll();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Department entity = (Department) iterator.next();
			collection.add(utility.convertModelTOEDM(new HashMap<String, Object>(), entity));
	
		}
		 return collection;
		} catch (Exception e) {
		return  null;
		}
		 	
		   
	}

	
	

	@Override
	public List<Map<String, Object>> getAssociation(int id, String associatedEdm) {

		List<Map<String, Object>> collection = new ArrayList<Map<String, Object>>();
		try {
			DepartmentDAO dao = (DepartmentDAO) idao;
			Department entity = dao.getById(id);
			if ( associatedEdm.equals(ENTITYSET_TESTMASTER)){
				IUtility testIUtility = UtilityFactory.getInstance().getInstance(ENTITYSET_TESTMASTER);
				List<Testmaster> coll =entity.getTestmasters();
				for (Iterator iterator = coll.iterator(); iterator.hasNext();) {
					Testmaster testmaster = (Testmaster) iterator.next();
					Map<String, Object> map = new HashMap<String, Object>();
					Map<String, Object> map1 =testIUtility.convertModelTOEDM(map, testmaster);
					collection.add(map1);
				}
			}
			
			
			return collection;
		} catch (Exception e) {
			return null;
		}
		// TODO Auto-generated method stub
	 
	}

	@Override
	public Map<String, Object> getRecord(int id) {
		try {
			DepartmentDAO dao = (DepartmentDAO) idao;
				Department entity =			dao.getById(id);
				Map<String, Object> map = new HashMap<String, Object>();
				return utility.convertModelTOEDM(map, entity);
		} catch (Exception e) {
return null;
		}
	}

	@Override
	public Map<String, Object> createNewRecord(Map<String, Object> record) {
		try {
			Department entity = new Department();
			DepartmentsUtility utility = new DepartmentsUtility();
			utility.populate(record, entity);
			entity.setIddepartment(0);
			entity.setCreatedOn(new Date());
			entity.setCreatedBy("SANJAY");
			DepartmentDAO dao =(DepartmentDAO) super.idao;
			entity =dao.saveNew(entity);
			Map<String, Object> map = new HashMap<String, Object>();
			return utility.convertModelTOEDM(map, entity);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Map<String, Object> updateRecord(int id, Map<String, Object> data) {
		try {
			DepartmentDAO dao = (DepartmentDAO) idao;
			Department entity ;
			entity =dao.getById(id);
			DepartmentsUtility du= (DepartmentsUtility) utility;
			Boolean active = (Boolean) data.get(du.ACTIVE);
			entity.setChangedBy("AMIT");
			entity.setDescription((String) data.get(du.DESCRIPTION));
			entity.setName((String) data.get(du.NAME));
			entity =dao.save(entity);
			Map<String, Object> map = new HashMap<String, Object>();
			return du.convertModelTOEDM(map, entity);
			 
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public boolean deleteUpdate(int id) {
		try {
			DepartmentDAO dao = (DepartmentDAO) idao;
			Department entity =dao.getById(id);
		dao.delete(entity);
		return true;
		} catch (Exception e) {
			return false;
		}
 
	}

	
}
