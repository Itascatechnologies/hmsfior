package in.co.itasca.hms.persistence.dao;

import in.co.itasca.hms.persistence.manager.EntityManagerProvider;
import in.co.itasca.hms.persistence.manager.PersistenceManager;
import in.co.itasca.hms.persistence.model.Role;

public class RolesDAO  extends BasicDAO<Role>{

	public RolesDAO() {
		super(PersistenceManager.getInstance().getEntityManagerProvider());

	}

}
