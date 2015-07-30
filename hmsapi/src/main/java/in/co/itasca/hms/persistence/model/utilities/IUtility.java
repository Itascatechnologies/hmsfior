package in.co.itasca.hms.persistence.model.utilities;

import in.co.itasca.hms.persistence.model.Department;
import in.co.itasca.hms.persistence.model.IDBEntity;

import java.util.Map;

public interface IUtility {
	public Map<String, Object> convertModelTOEDM(Map<String, Object> map, IDBEntity entity) ;
		
	public void populate(Map<String, Object> mapData, IDBEntity entity);
}
