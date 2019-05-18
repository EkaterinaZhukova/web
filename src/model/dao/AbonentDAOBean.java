package model.dao;

import exceptions.DAOException;
import model.Abonent;
import model.Abonent_;
import model.dao.AbonentDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import java.util.List;


/**
 * DAO class for abonents table in database
 * @author Ekaterina Zhukova
 */


@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class AbonentDAOBean implements AbonentDAO {

    private Logger logger = LogManager.getLogger("dao_layer");

    /**
     * Constructor for class
     */
    @Resource
    UserTransaction utx;

    @PersistenceContext(unitName = "Test_Local")
    EntityManager manager;


    public AbonentDAOBean(EntityManagerFactory emf) {
        manager = emf.createEntityManager();
    }

    public AbonentDAOBean() {
    }

    /**
     * Method returns all abonents in database
     * @return list of users
     */
    public List<Abonent> getAll() {
        List clients = null;
        try {
            CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Abonent.class);
            Root ab = criteriaQuery.from(Abonent.class);

            clients = manager.createQuery(criteriaQuery)
                    .getResultList();
            logger.info("getAll method succeed!");

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());

        }
        return clients;
    }

    /**
     * Method to get specific user
     * @param name name of user
     * @param surname surname of user
     * @return user
     */
    public Abonent getUserWithName(String name, String surname) {
        Abonent user = null;
        try {
            CriteriaBuilder cb = manager.getCriteriaBuilder();
            CriteriaQuery<Abonent> q = cb.createQuery(Abonent.class);
            Root<Abonent> c = q.from(Abonent.class);
            q.select(c).where(cb.equal(c.get(Abonent_.name), name)).where(cb.equal(c.get(Abonent_.surname), surname));
            TypedQuery<Abonent> query = manager.createQuery(q);
            user = query.getResultList().get(0);
            logger.info("getUserWithName succeed!");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
        return user;
    }

    /**
     * Get abonent with specific surname and phone number (use in loging)
     * @param surname surname of user
     * @param phone users phone number
     * @return abonent or null if not found
     */
    public Abonent getUserWithSurname(String surname, String phone) {
        Abonent user = null;
        try {
            CriteriaBuilder cb = manager.getCriteriaBuilder();
            CriteriaQuery<Abonent> q = cb.createQuery(Abonent.class);
            Root<Abonent> c = q.from(Abonent.class);
            q.select(c).where(cb.equal(c.get(Abonent_.surname), surname)).where(cb.equal(c.get(Abonent_.phone), phone));
            TypedQuery<Abonent> query = manager.createQuery(q);
            user = query.getResultList().get(0);
            logger.info("getUserWithSurname succeed!");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
        return user;
    }

    /**
     * Method that blocks/unblocks user
     * @param user  to block
     * @param block flag to block or to unblock
     */
    public void blockUser(Abonent user, Boolean block) {
        Long toSet = (long) (block ? 1 : 0);
        try {
            utx.begin();
            user.setBlocked(toSet.intValue());
            manager.merge(user);
            utx.commit();
            logger.info("blockUser succeed!");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
    }

    /**
     * add new object to database
     * @param obj
     * @throws DAOException
     */

    public void createObject(Object obj) throws DAOException{
        try {
            utx.begin();
            manager.persist(obj);
            utx.commit();
        } catch (Exception e) {
            throw new DAOException("Can't create object" + obj.toString());
        }

    }
}
