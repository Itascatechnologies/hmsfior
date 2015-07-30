package in.co.itasca.hms.persistence.model.utilities;

import in.co.itasca.hms.persistence.model.IDBEntity;
import in.co.itasca.hms.persistence.model.Sex;

import java.util.Map;

public class SexUtility implements IUtility {

	public SexUtility() {
		// TODO Auto-generated constructor stub
	}
	
	public  final String ID ="Id";
	public  final String SEXTEXT ="Name";
	public  final String DESCRIPTION ="Description";
	public  final String CREATEDBY ="CreatedBy";
	public  final String CREATEDON ="CreatedOn";
	public  final String CHANGEDBY ="ChangedBy";
	public  final String CHANGEDON ="ChangedOn";
	public  final String ACTIVE = "Active";
	@Override
	public Map<String, Object> convertModelTOEDM(Map<String, Object> map,
			IDBEntity lentity) {

		try {
			
		Sex entity = (Sex) lentity;
		map.put(ACTIVE, (entity.getActive()==1)? true : false);
		map.put(CHANGEDBY, (entity.getChangedBy()!=null)?entity.getChangedBy(): "");
		map.put(CREATEDBY, entity.getCreatedBy());
		map.put(CREATEDON, entity.getCreatedOn());
		map.put(ID, entity.getIdsex()  );
		map.put(SEXTEXT, entity.getSexText());
		return map;
		} catch (Exception e) {
			return null;	}
	}
	@Override
	public void populate(Map<String, Object> mapData, IDBEntity entity) {
		// TODO Auto-generated method stub
		
	}
	
	 



}
