package in.co.itasca.hms.persistence.dao;

import in.co.itasca.hms.persistence.manager.EntityManagerProvider;
import in.co.itasca.hms.persistence.manager.PersistenceManager;
import in.co.itasca.hms.persistence.model.Testregistration;

public class TestRegistrationDAO extends BasicDAO<Testregistration>{

	public TestRegistrationDAO( ) {
		super(PersistenceManager.getInstance().getEntityManagerProvider());

	}

}
