package model.dao;

import exceptions.DAOException;
import model.Abonent;
import model.Account;
import model.Service;

import javax.ejb.Remote;
import java.util.List;
/**
 * DAO interface for accounts table in database
 * @author Ekaterina Zhukova
 */
@Remote
public interface AccountDAO {
    List<Service> getServicesByUser(Abonent user);
    double getBalanceFor(Abonent user);
    Account getAccount(Abonent user, int id);
    void payForUser(Abonent abonent,Service sv);
    List<Account> getAll();
    void createObject(Object obj) throws DAOException;
}
