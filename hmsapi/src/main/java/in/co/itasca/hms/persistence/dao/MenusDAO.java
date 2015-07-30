package in.co.itasca.hms.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import in.co.itasca.hms.persistence.manager.PersistenceManager;
import in.co.itasca.hms.persistence.model.Menus;

public class MenusDAO extends BasicDAO<Menus> {
public MenusDAO(){
	super(PersistenceManager.getInstance().getEntityManagerProvider());

}

public List<Menus> getRootMenu(){
	List<Menus> menus = new ArrayList<Menus>();
	try {
		EntityManager em = super.emProvider.get();
		
		Query query = em.createNamedQuery("Menus.getMenusFromRoot", Menus.class);
		//query.setParameter("para1", 1);
	//	Query query = em.createQuery("select m from Menus m where m.Menus_id=1");
		List<Menus> result =	query.getResultList();
	Menus menu = result.get(0);
		return menu.getMenuses();
	} catch (Exception e) {
		return menus;
	}
	
}
 
public List<Menus> getChildren(long id) {
	List<Menus> menus = new ArrayList<Menus>();
	try {
		EntityManager em = super.emProvider.get();
		
		Query query = em.createNamedQuery("Menus.findById", Menus.class);
		query.setParameter("id", id);
	//	Query query = em.createQuery("select m from Menus m where m.Menus_id=1");
		List<Menus> result =	query.getResultList();
 
		return result;
	} catch (Exception e) {
		return menus;
	}
}

 

}
