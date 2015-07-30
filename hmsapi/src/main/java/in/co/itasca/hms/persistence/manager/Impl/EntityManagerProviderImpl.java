package in.co.itasca.hms.persistence.manager.Impl;

import javax.persistence.EntityManager;

import  in.co.itasca.hms.persistence.manager.EntityManagerProvider;;

public class EntityManagerProviderImpl implements EntityManagerProvider {
    public static final ThreadLocal<EntityManager> ENTITY_MANAGER = new ThreadLocal<EntityManager>();

    public EntityManager get() {
        return ENTITY_MANAGER.get();
    }

    public void set(EntityManager entityManager) {
        ENTITY_MANAGER.set(entityManager);
    }

    public void remove() {
        ENTITY_MANAGER.remove();
    }
}
