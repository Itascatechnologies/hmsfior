package in.co.itasca.hms.api.service;
 
import in.co.itasca.hms.persistence.dao.IDAOEntity;
import in.co.itasca.hms.persistence.model.utilities.IUtility;

public abstract class BaseService   implements IService{
	private String role;
	protected IDAOEntity idao;

protected IUtility utility;
	//private T dao;
	public BaseService(String role){
	try {
		this.role=role;
		
//		Type genericSuperclass = this.getClass().getGenericSuperclass();
//	        ParameterizedType pt = (ParameterizedType) genericSuperclass.getClass().newInstance();
//	        Type type = pt.getActualTypeArguments()[0];
//	        dao =(T) type.getClass().newInstance();

	} catch (Exception e) {
		// TODO: handle exception
	}
			}

}
