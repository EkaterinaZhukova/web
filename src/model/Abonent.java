package model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Model class of Abonent
 * @author Ekaterina Zhukova
 */

@Entity
@Table(name = "abonents")

public class Abonent implements Serializable {
    private static final long serialVersionUID = 1;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "blocked", nullable = false)
    private int blocked;

    public Abonent() {

    }

    public Abonent(int id, String name, String surname, String phone){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.blocked = 0;
    }

    public Abonent(Abonent ab) {
        this(ab.id,ab.name,ab.surname,ab.phone);
        this.blocked = ab.blocked;
    }
    public Abonent(int id, String name, String surname, String phone,int blocked){
        this(id,name,surname,phone);
        this.blocked = blocked;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public int isBlocked() {
        return blocked;
    }

    public void setBlocked(int blocked) {
        this.blocked = blocked;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Abonent)) return false;
        Abonent abonent = (Abonent) o;
        return (this.name.equals(abonent.getName()) && (this.surname.equals(abonent.getSurname()) && (this.phone.equals(abonent.getPhone()))));
    }

    @Override
    public int hashCode() {
        int result = 31 * getName().hashCode() + 13 * getSurname().hashCode() %11;
        return result;
    }

    @Override
    public String toString() {
        String res = "";
        String blocked = (this.blocked != 0) ? "YES" : "NO";
        res = "User #" + this.getId() +"\nName: "+this.getName() + " " +this.getSurname() + "\nPhone number: " + getPhone() +"\n" +"Blocked: " + blocked;
        return res;
    }
}
