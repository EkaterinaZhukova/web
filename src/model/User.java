package model;

public class User {
    public Abonent abonentEntity;

    private Boolean admin;

    private Boolean guest;

    private Boolean abonent;

    public Boolean isAdmin() {
        return admin;
    }

    public Boolean isAbonent() {
        return abonent;
    }

    public Boolean isGuest() {
        return guest;
    }

    public void setAbonent(Boolean abonent) {
        this.abonent = abonent;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public void setGuest(Boolean guest) {
        this.guest = guest;
    }
}
