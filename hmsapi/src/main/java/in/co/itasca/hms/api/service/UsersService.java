package in.co.itasca.hms.api.service;

import in.co.itasca.hms.persistence.dao.UsersDAO;
import in.co.itasca.hms.persistence.model.User;
import in.co.itasca.hms.persistence.model.utilities.UserUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UsersService extends BaseService {

	
	

	public UsersService(String role) {
		super(role);
		super.idao = new UsersDAO();
		super.utility= new UserUtility();
	}

	@Override
	public List<Map<String, Object>> getRecords() {
		try {
			
			
			List<Map<String, Object>> collection = new ArrayList<Map<String, Object>>();
			UsersDAO dao = (UsersDAO) idao;
					
			List<User> list =dao.getAll();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				User entity = (User) iterator.next();
							Map<String, Object> map= new HashMap<String, Object>();
				collection.add(utility.convertModelTOEDM(map,entity));
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> createNewRecord(Map<String, Object> record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> updateRecord(int id, Map<String, Object> record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUpdate(int id) {
		try {
			UsersDAO dao = (UsersDAO) idao;
			User entity =dao.getById(id);
		dao.delete(entity);
		return true;
		} catch (Exception e) {
			return false;
		}
 
	}

}
