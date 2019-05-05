package model;


/**
 * Additional help class for abonent with balance
 * @author Ekaterina Zhukova
 * @version 1.0
 */
public class AbonentWithBalance extends Abonent {
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AbonentWithBalance(Abonent ab, double balance) {
        super(ab);
        this.balance = balance;
    }
}
