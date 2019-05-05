package model.dao;

import model.Service;

import javax.ejb.Remote;
import java.util.List;
/**
 * DAO interface for services table in database
 * @author Ekaterina Zhukova
 */
@Remote
public interface ServiceDAO {
    List<Service> getAvailibleServices();
    Service getServiceWithId(Integer id);
    Service createService(String name, Double price);
}
