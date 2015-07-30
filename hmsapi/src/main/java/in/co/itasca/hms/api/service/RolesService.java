package in.co.itasca.hms.api.service;

import in.co.itasca.hms.persistence.dao.RolesDAO;
import in.co.itasca.hms.persistence.dao.RolesDAO;
import in.co.itasca.hms.persistence.model.Department;
import in.co.itasca.hms.persistence.model.Role;
import in.co.itasca.hms.persistence.model.utilities.IUtility;
import in.co.itasca.hms.persistence.model.utilities.RoleUtility;

import java.util.List;
import java.util.Map;

public class RolesService extends BaseService {

private IUtility utility;

	public RolesService(String role) {
		super(role);
		super.idao= new RolesDAO();
		super.utility= new RoleUtility();
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
		try {
			RolesDAO dao = (RolesDAO) idao;
			Role  entity =dao.getById(id);
		dao.delete(entity);
		return true;
		} catch (Exception e) {
			return false;
		}
 
	}

}
