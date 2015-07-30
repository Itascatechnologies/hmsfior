package in.co.itasca.hms.persistence.dao;

import in.co.itasca.hms.persistence.manager.EntityManagerProvider;
import in.co.itasca.hms.persistence.manager.PersistenceManager;
import in.co.itasca.hms.persistence.model.Department;

public class DepartmentDAO extends BasicDAO<Department> {

	public DepartmentDAO( ) {
		super(PersistenceManager.getInstance().getEntityManagerProvider());
		super.idFieldName="iddepartment";
	}
	

}
