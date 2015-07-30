package in.co.itasca.hms.api.service;

import static in.co.itasca.hms.api.Constants.ENTITYSET_MENUS;
import in.co.itasca.hms.persistence.dao.MenusDAO;
import in.co.itasca.hms.persistence.dao.MenusDAO;
import in.co.itasca.hms.persistence.dao.MenusDAO;
import in.co.itasca.hms.persistence.model.Department;
import in.co.itasca.hms.persistence.model.Menus;
import in.co.itasca.hms.persistence.model.utilities.IUtility;
import in.co.itasca.hms.persistence.model.utilities.MenusUtility;
import in.co.itasca.hms.persistence.model.utilities.UtilityFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MenusService extends BaseService{
 
	public MenusService(String role) {
		super(role);
		super.idao= new MenusDAO();
		super.utility= UtilityFactory.getInstance().getInstance(ENTITYSET_MENUS);
	}

	@Override
	public List<Map<String, Object>> getRecords() {
		List<Map<String, Object>> collection = new ArrayList<Map<String, Object>>();
		MenusDAO dao = (MenusDAO) idao;
				
		List<Menus> list =dao.getRootMenu();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Menus menus = (Menus) iterator.next();
			Map<String, Object > map = new HashMap<String, Object>();
			collection.add(utility.convertModelTOEDM(map, menus));
		}

		 	
		    return collection;
	}

//	private Map<String, Object> createMenu(Menus menu) {
//		    Map<String, Object> data = new HashMap<String, Object>();
//
//		    data.put("Id", menu.getId());
//		    data.put("title", menu.getTitle());
//		    data.put("info", menu.getInfo());
//		    data.put("number", menu.getNumber());
//		    data.put("numberUnit", menu.getNumberunit());
//		    data.put("icon", menu.getIcon());
//		    data.put("targetPage","No p age");
//		    
//		    return data;
//		  }

	@Override
	public List<Map<String, Object>> getAssociation(int id, String associatedEdm) {
		List<Map<String, Object>> subMenuList = new ArrayList<Map<String,Object>>();
		MenusDAO dao = (MenusDAO) idao;
 
		List<Menus> collectionModel =dao.getChildren(id);
		for (Iterator iterator = collectionModel.iterator(); iterator.hasNext();) {
			Menus menus = (Menus) iterator.next();
			Map<String, Object > map = new HashMap<String, Object>();

			subMenuList.add(utility.convertModelTOEDM(map, menus));
		}
		return subMenuList;
//		
//		List<Map<String, Object>> subMenuList = new ArrayList<Map<String,Object>>();
//		
//		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
//		Map<String, Object> data = new HashMap<String, Object>();
//		result.add(data);
//		if(id==1){
//			Map<String, Object> subMenu = new HashMap<String, Object>();
//			data.put("Id", 1);
//		    data.put("title", "Home");
//		    data.put("info", "HomelabelHeading");
//		    data.put("number", "HomelabelSubheading");
//		    data.put("numberUnit", "HomeserviceURL");
//		    data.put("icon", "icon1");
//		    data.put("SubMenus" , subMenuList );
//			
//			subMenu.put("Id", 4);
//			subMenu.put("title", "Daily Report");
//			subMenu.put("info", "Registered Patients Report");
//			subMenu.put("number", "Four");
//			subMenu.put("numberUnit", "Nos");
//			subMenu.put("icon", "icon4");
//			subMenu.put("targetPage", "DailyReportView");
//			subMenuList.add(subMenu);
//			Map<String, Object> subMenu1 = new HashMap<String, Object>();
//			subMenu1.put("Id", 5);
//			subMenu1.put("title", "Monthly Report");
//			subMenu1.put("info", "Monthly Report of OPD");
//			subMenu1.put("number", "Five");
//			subMenu1.put("numberUnit", "Nos");
//			subMenu1.put("icon", "icon5");
//			subMenu1.put("targetPage", "MonthlyReportView");
//			subMenuList.add(subMenu1);			
//			
//		} else if (id==2){
//			Map<String, Object> subMenu = new HashMap<String, Object>();
//						subMenu.put("Id", 6);
//			subMenu.put("title", "New Registration");
//			subMenu.put("info", "Add a new Patient");
//			subMenu.put("number", "Six");
//			subMenu.put("numberUnit", "Nos");
//			subMenu.put("icon", "icon6");
//			subMenu.put("targetPage", "NewRegView");
//			subMenuList.add(subMenu);
//			Map<String, Object> subMenu1 = new HashMap<String, Object>();
//			subMenu1.put("Id", 7);
//			subMenu1.put("title", "Renew Registration");
//			subMenu1.put("info", "Renew Existing Patient");
//			subMenu1.put("number", "Seven");
//			subMenu1.put("numberUnit", "Nos");
//			subMenu1.put("icon", "icon7");
//			subMenu1.put("targetPage", "RenewRegistration");
//			subMenuList.add(subMenu1);			
//			
//			
//		} else if (id==3){
//			Map<String, Object> subMenu = new HashMap<String, Object>();
//			subMenu.put("Id", 9);
//subMenu.put("title", "Add New Test");
//subMenu.put("info", "Add Patient for a new Test");
//subMenu.put("number", "Nine");
//subMenu.put("numberUnit", "Nos");
//subMenu.put("icon", "icon9");
//subMenu.put("targetPage", "NewTestView");
//subMenuList.add(subMenu);
//Map<String, Object> subMenu1 = new HashMap<String, Object>();
//subMenu1.put("Id", 10);
//subMenu1.put("title", "Daily Report");
//subMenu1.put("info", "Daily Report");
//subMenu1.put("number", "Ten");
//subMenu1.put("numberUnit", "Nos");
//subMenu1.put("icon", "icon10");
//subMenu1.put("targetPage", "TestDailyReport");
//subMenuList.add(subMenu1);			
//
//		} else if (id==4){
//			Map<String, Object> subMenu = new HashMap<String, Object>();
//			subMenu.put("Id", 11);
//subMenu.put("title", "Sex");
//subMenu.put("info", "Patient Sex");
//subMenu.put("number", "Eleven");
//subMenu.put("numberUnit", "Nos");
//subMenu.put("icon", "icon11");
//subMenu.put("targetPage", "SexView");
//subMenuList.add(subMenu);
//Map<String, Object> subMenu1 = new HashMap<String, Object>();
//subMenu1.put("Id", 12);
//subMenu1.put("title", "Department");
//subMenu1.put("info", "Manage Departments");
//subMenu1.put("number", "Twelve");
//subMenu1.put("numberUnit", "Nos");
//subMenu1.put("icon", "icon12");
//subMenu1.put("targetPage", "DepartmentView");
//subMenuList.add(subMenu1);			
//
//		}
//		return subMenuList;
	}

	@Override
	public Map<String, Object> getRecord(int id) {
		MenusDAO dao = new MenusDAO();
		Menus dataModel =dao.getById(id);
		Map<String, Object > map = new HashMap<String, Object>();

		return utility.convertModelTOEDM(map, dataModel);
//		
//		Map<String, Object> data = new HashMap<String, Object>();
//		
//		//		if(id==1){
////			Map<String, Object> subMenu = new HashMap<String, Object>();
////			data.put("Id", 1);
////		    data.put("title", "Home");
////		    data.put("info", "HomelabelHeading");
////		    data.put("number", "HomelabelSubheading");
////		    data.put("numberUnit", "HomeserviceURL");
////		    data.put("icon", "HomeserviceURL");
//////		    data.put("items" , subMenuList );
////
////
////			subMenu.put("Id", 3);
////			subMenu.put("menuName", "SubHome");
////			subMenu.put("labelHeading", "SubHomelabelHeading");
////			subMenu.put("labelSubheading", "SubHomelabelSubheading");
////			subMenu.put("serviceURL", "SubHomeserviceURL");
////	
////			data.put("items", subMenu);
////		}
//		return data;
	}

	@Override
	public Map<String, Object> createNewRecord(Map<String, Object> record) {
		try {
			
			Menus entity = new Menus();
			MenusUtility ut = (MenusUtility) super.utility;
			entity.setInfo((String) record.get(ut.INFO));
//			
//			entity.setKey(record.get(ut.menukey);
			entity.setNumberunit((String) record.get(ut.NUMBER));
//			entity.setNumberunit(record.get(ut.NUMBERUNIT));
			entity.setTargetPage((String) record.get(ut.TARGETPAGE));
			entity.setTitle((String) record.get(ut.TITLE));
			
			
			
		
			MenusDAO dao =(MenusDAO) super.idao;
			entity =dao.saveNew(entity);
						Map<String, Object> map= new HashMap<String, Object>();
			
			return ut.convertModelTOEDM(map, entity);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Map<String, Object> updateRecord(int id, Map<String, Object> record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUpdate(int id) {
		try {
			MenusDAO dao = (MenusDAO) idao;
			Menus entity =dao.getById(id);
		dao.delete(entity);
		return true;
		} catch (Exception e) {
			return false;
		}
 
	}
 
 
}
