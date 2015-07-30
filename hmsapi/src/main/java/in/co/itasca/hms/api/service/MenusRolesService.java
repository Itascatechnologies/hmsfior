package in.co.itasca.hms.api.service;

import in.co.itasca.hms.persistence.dao.MenusRolesDAO;
import in.co.itasca.hms.persistence.model.utilities.MenusRolesUtility;

import java.util.List;
import java.util.Map;

public class MenusRolesService extends BaseService{
	public static final String ID="Id";
	public static final String TITLE="title";
	public static final String INFO="info";
	public static final String NUMBER="number";
	public static final String NUMBERUNIT="numberUnit";
	public static final String ICON="icon";
	public static final String TARGETPAGE="targetPage";


	public MenusRolesService(String role) {
		super(role);
		super.idao= new MenusRolesDAO();
		super.utility= new MenusRolesUtility();
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
		// TODO Auto-generated method stub
		return false;
	}


}
