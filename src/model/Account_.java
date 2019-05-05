package model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Account.class)

public class Account_ {
    public static volatile SingularAttribute<Account, Long> id;
    public static volatile SingularAttribute<Account, Boolean> payed;
    public static volatile SingularAttribute<Account,Abonent> abonent;
    public static volatile SingularAttribute<Account,Service> service;

}
