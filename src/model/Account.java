package model;

import javax.persistence.*;
import javax.print.attribute.standard.Severity;
import java.io.Serializable;
import java.util.Date;

/**
 * Model class of Abonent
 * @author Ekaterina Zhukova
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "Account.servicesForClient", query = "SELECT o.service FROM Account o WHERE o.abonent = ?1 and o.payed = false"),
        @NamedQuery(name = "Account.balanseForClient", query = "SELECT SUM(o.service.price) FROM Account o WHERE o.abonent = ?1 AND o.payed = false "),
        @NamedQuery(name = "Account.payForUser", query = "SELECT o FROM Account o WHERE o.abonent = ?1 AND o.id = ?2"),

})
@Table(name = "accounts")

public class Account implements Serializable {
    private static final long serialVersionUID = 1;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name="abonent", nullable = false)
    private Abonent abonent;

    @ManyToOne
    @JoinColumn(name="service", nullable = false)
    private Service service;

    @Column(name = "payed", nullable = false)
    private boolean payed;

    public Account() {}
    public Account(int id, Abonent abonent, Service service){
        this.id = id;
        this.abonent = abonent;
        this.service = service;
        this.payed = false;
    }

    public int getId() {
        return id;
    }

    public Abonent getAbonent() {
        return abonent;
    }

    public Service getService() {
        return service;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAbonent(Abonent abonent) {
        this.abonent = abonent;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Account)) return false;
        Account ac = (Account) obj;
        return (this.id == ac.getId());
    }

    @Override
    public int hashCode() {
        int result = (31 * getAbonent().hashCode() + 13 *getService().hashCode()) %11;
        return result;
    }
}
