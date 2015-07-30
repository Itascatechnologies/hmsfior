package in.co.itasca.hms.persistence.model.utilities;

import java.util.Map;

import org.eclipse.persistence.internal.sessions.remote.SequencingFunctionCall.GetNextValue;

import in.co.itasca.hms.persistence.model.IDBEntity;
import in.co.itasca.hms.persistence.model.Menus;

public class MenusUtility implements IUtility {
	
	public MenusUtility() {
		// TODO Auto-generated constructor stub
	}

	
	public  final String ID ="Id";
	public  final String TITLE ="Title";
	public  final String  INFO ="Info";
	public  final String NUMBER ="Number";
	public  final String NUMBERUNIT="NumberUnit";
	public  final String ICON ="Icon";
	public  final String TARGETPAGE = "TargetPage";
	public  final long serialVersionUID = 1L;

	
	 


	@Override
	public Map<String, Object> convertModelTOEDM(Map<String, Object> map,
			IDBEntity lentity) {
		try {
			Menus entity = (Menus) lentity; 
			map.put(ID,entity.getId());
		    map.put(TITLE, entity.getTitle());
		    map.put(INFO,  entity.getInfo());
		    map.put(NUMBER, entity.getMenunumber());
		    map.put(NUMBERUNIT, entity.getNumberunit());
		     map.put(TARGETPAGE, entity.getTargetPage());
		    map.put(TITLE, entity.getTitle());
//		    map.put(key, value)
		    return map;
		} catch (Exception e) {
		return null;
		}
	}


	@Override
	public void populate(Map<String, Object> mapData, IDBEntity entity) {
		// TODO Auto-generated method stub
		
	}

	
}
