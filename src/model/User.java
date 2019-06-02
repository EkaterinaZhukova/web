package model;

public class User {

    public String name;

    public String phone;

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;
        User user = (User) obj;
        if (this.abonent == user.abonent) {
            if (this.guest == user.guest) {
                if (this.admin == user.admin) {
                    if (user.abonent || user.admin) {
                        return this.name.equals(user.name) && this.phone.equals(user.phone);
                    }
                }
            }
        }
        return false;
    }
}
