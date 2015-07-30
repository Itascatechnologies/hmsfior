package in.co.itasca.hms.api.service;

import in.co.itasca.hms.persistence.dao.DepartmentDAO;
import in.co.itasca.hms.persistence.dao.UsersRolesDAO;
import in.co.itasca.hms.persistence.model.Department;

import java.util.List;
import java.util.Map;

public class UsersRolesServices extends BaseService{

	public UsersRolesServices(String role) {
		super(role);
//		super.idao= new UsersRolesDAO();
	}

	@Override
	public List<Map<String, Object>> getRecords() {
		// TODO Auto-generated method stub
		return null;
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
//		try {
//			UsersRolesDAO dao = (UsersRolesDAO) idao;
//			U entity =dao.getById(id);
//		dao.delete(entity);
//		return true;
//		} catch (Exception e) {
//			return false;
//		}
		return false;
// 
	}

}
