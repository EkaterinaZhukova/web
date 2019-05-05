package model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Abonent.class)
public class Abonent_ {
    public static volatile SingularAttribute<Abonent, Long> id;
    public static volatile SingularAttribute<Abonent, String> name;
    public static volatile SingularAttribute<Abonent, String> surname;
    public static volatile SingularAttribute<Abonent, String> phone;
    public static volatile SingularAttribute<Abonent, Long> blocked;

}
