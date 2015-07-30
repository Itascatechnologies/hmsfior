package in.co.itasca.hms.persistence.dao;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.co.itasca.hms.persistence.manager.EntityManagerProvider;
import in.co.itasca.hms.persistence.model.IDBEntity;


public abstract class BasicDAO<T extends IDBEntity> implements IDAOEntity  {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
protected   String idFieldName;


    protected EntityManagerProvider emProvider;

    public BasicDAO(EntityManagerProvider emProvider) {
        this.emProvider = emProvider;
    }

    public void refresh(T object) {
        final EntityManager em = emProvider.get();
        em.refresh(object);
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        final List<T> result = new ArrayList<T>();
        final EntityManager em = emProvider.get();
        result.addAll((Collection<? extends T>) em.createQuery("select t from " + getTableName() + " t",
                this.getClass().getGenericSuperclass().getClass()).getResultList());
        return result;
    }

    public T save(T entity) {
        final EntityManager em = emProvider.get();
        em.getTransaction().begin();

        final T merge = em.merge(entity);
 
        em.getTransaction().commit();
        return merge;
    }


    
    public T saveNew(T entity) {
        final EntityManager em = emProvider.get();
        em.getTransaction().begin();
        em.persist(entity);
 
        em.getTransaction().commit();
        return entity;
    }


    public void delete(T entity) {
        final EntityManager em = emProvider.get();
        em.getTransaction().begin();
em.remove(entity);
        em.getTransaction().commit();
        return;
    }

  
    
    
    public T getById(long id) {
        final EntityManager em = emProvider.get();
       
        return getById(id, em);
    }
    
  //  protected abstract String getidFieldName();

    @SuppressWarnings("unchecked")
    private T getById(long id, EntityManager em) {
        T t = null;

        try {
            //Query query = em.createQuery("select u from " + getTableName() + " u where u."+getidFieldName() +" = :id"); //$NON-NLS-1$ //$NON-NLS-2$
            Type type = getActualType();
              
        	Query query = em.createQuery("select u from " + getTableName() + " u where u."+idFieldName  +" = :id"); //$NON-NLS-1$ //$NON-NLS-2$
            
            query.setParameter("id", id); //$NON-NLS-1$
            t = (T) query.getSingleResult();
            refresh(t);
        } catch (NoResultException e) {
            logger.error("Could not retrieve entity {} from table {}.", id, getTableName()); //$NON-NLS-1$
        } catch (NonUniqueResultException e) {
            logger.error("More than one entity {} from table {}.", id, getTableName()); //$NON-NLS-1$
        }

        return t;
    }

    private Type getActualType() {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) genericSuperclass;
        Type type = pt.getActualTypeArguments()[0];

        return type;
    }

    private String getTableName() {
        String actualType = getActualType().toString();
        
        return actualType.substring(actualType.lastIndexOf('.') + 1);

    }

    


}
