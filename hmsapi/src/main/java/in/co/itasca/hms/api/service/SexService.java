package in.co.itasca.hms.api.service;

import in.co.itasca.hms.persistence.dao.SexDAO;
import in.co.itasca.hms.persistence.dao.SexDAO;
import in.co.itasca.hms.persistence.dao.UsersDAO;
import in.co.itasca.hms.persistence.model.Department;
import in.co.itasca.hms.persistence.model.Sex;
import in.co.itasca.hms.persistence.model.utilities.IUtility;
import in.co.itasca.hms.persistence.model.utilities.SexUtility;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SexService extends BaseService {
 
	public SexService(String role) {
		super(role);
		super.idao = new SexDAO();
		super.utility= new SexUtility();
	}

	@Override
	public List<Map<String, Object>> getRecords() {
		SexDAO dao = (SexDAO) idao;
		List<Sex> sexs = dao.getAll();
		 List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		for (Iterator iterator = sexs.iterator(); iterator.hasNext();) {
			Sex sex = (Sex) iterator.next();
			SexUtility ut = (SexUtility) utility;
			Map<String, Object> data = new HashMap<String, Object>();
			data.put(ut.ID,new Integer(sex.getIdsex()));
			data.put(ut.SEXTEXT, sex.getSexText());
			data.put(ut.DESCRIPTION, sex.getDescription());
		data.put(ut .CREATEDBY, sex.getCreatedBy());
 		data.put(ut .CREATEDON, sex.getCreatedOn());
 			data.put(ut.CHANGEDBY, (sex.getChangedBy()==null)?"NONE":sex.getChangedBy());
		data.put(ut.CHANGEDON, (sex.getChangedOn()==null?"NONE": sex.getChangedOn()));
			boolean active =false;
			if(sex.getActive()==1)
				active=true;
		data.put(ut.ACTIVE,active );
			list.add(data);
		}


		    return list;
	}

	@Override
	public List<Map<String, Object>> getAssociation(int id, String associatedEdm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getRecord(int id) {
 
		return null;
	}

//	Map<String, Object> createSex(int id, String sexName, String createdBy){
//		Map<String, Object> data = new HashMap<String, Object>();
//		data.put("Id", id);
//		data.put("sexText", sexName);
//		data.put("createdBy", createdBy);
//		return data;
//	}

	@Override
	public Map<String, Object> createNewRecord(Map<String, Object> record) {
		try {
			Sex entity = new Sex();
			SexUtility ut= (SexUtility) utility;
		String sexText =((String) record.get(ut.SEXTEXT));
		String description =(String) record.get(ut.DESCRIPTION);
			if(sexText==null || description==null)
				return null;
		entity.setActive(1);
		entity.setCreatedBy("sanjay");
//		entity.setChangedOn(new Date());
		entity.setCreatedOn(new Date());
		entity.setDescription(description);
		entity.setSexText(sexText);
		SexDAO dao = (SexDAO) idao;
		entity = dao.saveNew(entity);
					Map<String, Object> map= new HashMap<String, Object>();
				return ut.convertModelTOEDM(map, entity);
				 
			
		} catch (Exception e) {
			return null;
		}
 
	}

	@Override
	public Map<String, Object> updateRecord(int id, Map<String, Object> record) {
		try {
			SexDAO dao = (SexDAO) idao;
			Sex entity = dao.getById(id);
			SexUtility ut = (SexUtility) utility;
			entity.setChangedBy("aamit");
			entity.setChangedOn(new Date());
			entity.setSexText((String) record.get(ut.SEXTEXT));
			entity.setDescription((String) record.get(ut.DESCRIPTION));
			entity = dao.save(entity);
						Map<String, Object> map= new HashMap<String, Object>();
				 return utility.convertModelTOEDM(map, entity);			
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean deleteUpdate(int id) {
		try {
			SexDAO dao = (SexDAO) idao;
			Sex entity =dao.getById(id);
		dao.delete(entity);
		return true;
		} catch (Exception e) {
			return false;
		}
 
	}
}
