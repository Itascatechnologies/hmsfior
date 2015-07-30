package in.co.itasca.hms.persistence.dao;

import java.util.List;
import java.util.Map;

import in.co.itasca.hms.api.service.BaseService;
import in.co.itasca.hms.persistence.manager.EntityManagerProvider;
import in.co.itasca.hms.persistence.manager.PersistenceManager;
import in.co.itasca.hms.persistence.model.Registration;

public class RegistrationDAO extends BasicDAO<Registration> {

	public RegistrationDAO() {
		super(PersistenceManager.getInstance().getEntityManagerProvider());
		super.idFieldName= "idregistration";
	}

	 

}
