package model.dao;

import model.*;

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
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * DAO class for accounts table in database
 * @author Ekaterina Zhukova
 */

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class AccountDAOBean implements AccountDAO {

    private Logger logger = LogManager.getLogger("dao_layer");

    public AccountDAOBean() {
    }

    @PersistenceContext(unitName = "Test_Local")
    EntityManager manager;

    @Resource
    UserTransaction utx;

    public AccountDAOBean(EntityManagerFactory emf) {
        manager = emf.createEntityManager();
    }

    /**
     * Me5hod that returns all services for specific user
     * @param user user to show for
     * @return list of services
     */
    public List<Service> getServicesByUser(Abonent user) {
        if (user == null) {
            return null;
        }

        List<Service> services = new ArrayList<>();
        List<Account> acc = new ArrayList<>();

        try {
            CriteriaBuilder cb = manager.getCriteriaBuilder();
            CriteriaQuery<Account> q = cb.createQuery(Account.class);
            Root<Account> c = q.from(Account.class);
            q.select(c).where(cb.equal(c.get(Account_.abonent), user));
            acc = manager.createQuery(q).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }

        for (Account item : acc) {
            if (item.isPayed()) {
                continue;
            }
            services.add(item.getService());
        }
        return services;
    }

    /**
     * Method that calculates balance for specific user
     * @param user user to calculate balance for
     * @return
     */
    public double getBalanceFor(Abonent user) {
        List<Account> accounts = new ArrayList<>();

        Double sum = 0.0;
        try {
            CriteriaBuilder cb = manager.getCriteriaBuilder();
            CriteriaQuery<Account> q = cb.createQuery(Account.class);
            Root<Account> c = q.from(Account.class);
            q.select(c).where(cb.equal(c.get(Account_.abonent), user));
            TypedQuery<Account> query = manager.createQuery(q);
            accounts = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        for (Account item : accounts) {
            if (item.isPayed()) {
                continue;
            }
            sum += item.getService().getPrice();
        }
        return sum;
    }

    /**
     * Method that return account for specific user
     * @param user user to search for
     * @param id id of service
     * @return
     */
    public Account getAccount(Abonent user, int id) {
        Account account = null;
        try {
            CriteriaBuilder cb = manager.getCriteriaBuilder();
            CriteriaQuery<Account> q = cb.createQuery(Account.class);
            Root<Account> c = q.from(Account.class);
            q.select(c).where(cb.equal(c.get(Account_.abonent), user)).where(cb.equal(c.get(Account_.id), id));
            TypedQuery<Account> query = manager.createQuery(q);
            account = query.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
        return account;
    }

    /**
     * @param user user to show account for
     * @param sv service to show account for
     * @return account for user with special service
     */

    private Account getAccountWithService(Abonent user, Service sv) {
        Account account = null;
        try {
            CriteriaBuilder cb = manager.getCriteriaBuilder();
            CriteriaQuery<Account> q = cb.createQuery(Account.class);
            Root<Account> c = q.from(Account.class);
            q.select(c).where(cb.equal(c.get(Account_.abonent), user));
            TypedQuery<Account> query = manager.createQuery(q);
            List<Account> acc = query.getResultList();
            for (Account ac:acc) {
                if (ac.getService().getId() == sv.getId()) {
                    return ac;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
        return account;
    }



    /**
     * Method that pays a service
     * @param abonent to pay for
     * @param sv  service to pay
     */
    public void payForUser(Abonent abonent, Service sv) {
        try {
            utx.begin();
            Account account = getAccountWithService(abonent, sv);
            account.setPayed(true);
            manager.merge(account);
            utx.commit();
            logger.info(abonent.getName() + " " + abonent.getSurname() + "payed service " + sv.getName() );
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            ex.printStackTrace();
        }
    }


}
