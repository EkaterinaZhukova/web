package model;

public class User {
    public Abonent abonentEntity;

    private Boolean admin;

    private Boolean guest;

    private Boolean abonent;

    public Boolean isAdmin() {
        return admin;
    }

    public User() {
        admin = false;
        guest = false;
        admin = false;
    }
    public Boolean isAbonent() {
        return abonent;
    }

    public Boolean isGuest() {
        return guest;
    }

    public void setAbonent(Boolean abonent) {
        this.abonent = abonent;
        this.admin = false;
        this.guest = false;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
        this.abonent = false;
        this.guest = false;
    }

    public void setGuest(Boolean guest) {
        this.guest = guest;
        this.abonent = false;
        this.guest = false;
    }
}
