package in.co.itasca.hms.persistence.dao;
 
import in.co.itasca.hms.persistence.manager.PersistenceManager;
import in.co.itasca.hms.persistence.model.Sex;

public class SexDAO extends BasicDAO<Sex> {
	 
	public SexDAO( ) {
		super(PersistenceManager.getInstance().getEntityManagerProvider());
		super.idFieldName="idsex";
	}

}
