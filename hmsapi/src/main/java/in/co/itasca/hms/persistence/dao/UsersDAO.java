package in.co.itasca.hms.persistence.dao;

import in.co.itasca.hms.persistence.manager.EntityManagerProvider;
import in.co.itasca.hms.persistence.manager.PersistenceManager;
import in.co.itasca.hms.persistence.model.User;

public class UsersDAO extends BasicDAO<User>{

	public UsersDAO( ) {
		super(PersistenceManager.getInstance().getEntityManagerProvider());

	}

}
