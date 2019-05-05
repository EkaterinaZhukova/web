package model.dao;

import model.Account;
import model.Account_;
import model.Service;
import model.Service_;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for services table in database
 * @author Ekaterina Zhukova
 */

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServiceDAOBean implements ServiceDAO {

    public ServiceDAOBean() {}

    @PersistenceContext(unitName = "Test_Local")
    EntityManager manager;

    private Logger logger = LogManager.getLogger("dao_layer");



    /**
     * Method that returns all available services
     * @return list of services
     */
    public List<Service> getAvailibleServices() {
        List<Service> services = new ArrayList<>();
        try {
            CriteriaBuilder cb = manager.getCriteriaBuilder();
            CriteriaQuery<Service> q = cb.createQuery(Service.class);
            Root<Service> c = q.from(Service.class);
            q.select(c);
            TypedQuery<Service> query = manager.createQuery(q);
            services = query.getResultList();
            logger.info("getAvailibleServices method succeed");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
        return services;
    }


    /**
     * Method that returns service with special ID
     * @param id services id
     * @return service object
     */

    public Service getServiceWithId(Integer id) {
        try {
            CriteriaBuilder cb = manager.getCriteriaBuilder();
            CriteriaQuery<Service> q = cb.createQuery(Service.class);
            Root<Service> c = q.from(Service.class);
            q.select(c).where(cb.equal(c.get(Service_.id), id.longValue()));
            TypedQuery<Service> query = manager.createQuery(q);
            Service sv = query.getResultList().get(0);
            return sv;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    /**
     * Creates new service
     * @param name service name
     * @param price service surname
     * @return
     */

    public Service createService(String name, Double price)  {
        Service service = new Service();
        service.setName(name);
        service.setPrice(price);
        return service;
    }
}
