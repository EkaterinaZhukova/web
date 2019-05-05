package model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Model class of Abonent
 * @author Ekaterina Zhukova
 */
@Entity
@NamedQuery(name = "Service.getAllAvailableServices", query = "SELECT c from Service c")
@Table(name = "services")

public class Service implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    public Service() {}

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Service(int id, String name, double price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Service)) return false;
        Service service = (Service) o;
        return (this.name.equals(service.getName()) && (this.price == service.price));
    }

    @Override
    public int hashCode() {
        int result = (31 * getName().hashCode() + 13 ) %11;
        return result;
    }
}
