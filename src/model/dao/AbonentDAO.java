package model.dao;

import model.Abonent;

import javax.ejb.Remote;
import java.util.List;
/**
 * DAO interface for abonents table in database
 * @author Ekaterina Zhukova
 */
@Remote
public interface AbonentDAO {
    List<Abonent> getAll() ;
    Abonent getUserWithName(String name, String surname);
    Abonent getUserWithSurname(String surname, String phone);
    void  blockUser(Abonent user,Boolean block) ;
}