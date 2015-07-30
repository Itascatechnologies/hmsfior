package in.co.itasca.hms.persistence.dao;

import in.co.itasca.hms.persistence.manager.EntityManagerProvider;
import in.co.itasca.hms.persistence.manager.PersistenceManager;
import in.co.itasca.hms.persistence.model.Patientscategorymaster;

public class PatientCategoryMasterDAO extends BasicDAO<Patientscategorymaster> implements IDAOEntity {

	public PatientCategoryMasterDAO( ) {
		super(PersistenceManager.getInstance().getEntityManagerProvider());
		idFieldName="idpatientsType";
	}

}
