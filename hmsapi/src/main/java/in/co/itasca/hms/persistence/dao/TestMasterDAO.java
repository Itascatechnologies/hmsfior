package in.co.itasca.hms.persistence.dao;
 
import in.co.itasca.hms.persistence.manager.PersistenceManager;
import in.co.itasca.hms.persistence.model.Testmaster;

public class TestMasterDAO extends BasicDAO<Testmaster>{

	public TestMasterDAO() {
		super(PersistenceManager.getInstance().getEntityManagerProvider());

	}

}
