package dk.rim.is.abt.util

import org.hibernate.Session
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Repository

import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.EntityTransaction
import java.util.function.Predicate

import static java.util.stream.Collectors.toList

@Repository
@Scope(value = "prototype")
class GenericDao<T> {
    private final EntityManagerFactory managerFactory;
    private Class<T> type;

    public GenericDao(EntityManagerFactory managerFactory) {
        this.managerFactory = managerFactory;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

    public List<T> getAll() {
        EntityManager manager = managerFactory.createEntityManager();
        Session session = manager.unwrap(Session.class);
        return session.createQuery("SELECT e FROM " + type.getSimpleName() + " e", type).getResultList();
    }

    public List<T> getBy(Predicate<T> predicate) {
        EntityManager manager = managerFactory.createEntityManager();
        Session session = manager.unwrap(Session.class);
        return session.createQuery("SELECT e FROM " + type.getSimpleName() + " e", type)
                .stream()
                .filter(predicate)
                .collect(toList());
    }

    public List<T> getBy(Class<T> type, Predicate<T> predicate) {
        EntityManager manager = managerFactory.createEntityManager();
        Session session = manager.unwrap(Session.class);
        return session.createQuery("SELECT e FROM " + type.getSimpleName() + " e", type)
                .stream()
                .filter(predicate)
                .collect(toList());
    }

    public T getById(Serializable id) {
        EntityManager manager = managerFactory.createEntityManager();
        Session session = manager.unwrap(Session.class);
        return session.find(type, id);
    }

    public void update(T entity) {
        EntityManager manager = managerFactory.createEntityManager();
        EntityTransaction txn = manager.getTransaction();

        try {
            txn.begin();

            manager.merge(manager.merge(entity));

            txn.commit();
        } catch (RuntimeException var8) {
            txn.rollback();
            throw var8;
        } finally {
            manager.close();
        }

    }

    public void save(T entity) {
        EntityManager manager = managerFactory.createEntityManager();
        EntityTransaction txn = manager.getTransaction();

        try {
            txn.begin();

            manager.persist(entity);

            txn.commit();
        } catch (RuntimeException var8) {
            txn.rollback();
            throw var8;
        } finally {
            manager.close();
        }
    }

    public void save(List<T> entities) {
        EntityManager manager = managerFactory.createEntityManager();
        EntityTransaction txn = manager.getTransaction();

        try {
            txn.begin();

            for (T entity : entities) {
                manager.persist(entity);
            }

            txn.commit();
        } catch (RuntimeException var8) {
            txn.rollback();
            throw var8;
        } finally {
            manager.close();
        }
    }

    public void deleteEntity(T entity) {
        EntityManager manager = managerFactory.createEntityManager();
        EntityTransaction txn = manager.getTransaction();

        try {
            txn.begin();
            manager.remove(manager.merge(entity));
            txn.commit();
        } catch (RuntimeException var8) {
            txn.rollback();
            throw var8;
        } finally {
            manager.close();
        }
    }

    public void delete(Predicate<T> predicate) {
        EntityManager manager = managerFactory.createEntityManager();
        Session session = manager.unwrap(Session.class);
        List<T> result = session.createQuery("SELECT e FROM " + type.getName() + " e", type)
                .stream()
                .filter(predicate)
                .collect(toList());

        EntityTransaction txn = manager.getTransaction();

        try {
            txn.begin();

            for (T elem : result) {
                deleteEntity(elem);
            }

            txn.commit();
        } catch (RuntimeException var8) {
            txn.rollback();
            throw var8;
        } finally {
            manager.close();
        }
    }
}
