package in.co.itasca.hms.persistence.dao;

import in.co.itasca.hms.persistence.manager.PersistenceManager;
import in.co.itasca.hms.persistence.model.Registrationfeemaster;

public class RegistrationFeeMasterDAO extends BasicDAO<Registrationfeemaster>{

	public RegistrationFeeMasterDAO( ) {
		super(PersistenceManager.getInstance().getEntityManagerProvider());
		super.idFieldName="id";
	}

}
