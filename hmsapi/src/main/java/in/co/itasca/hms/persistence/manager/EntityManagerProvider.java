package in.co.itasca.hms.persistence.manager;

import javax.persistence.EntityManager;


public interface EntityManagerProvider {

    EntityManager get();

}
