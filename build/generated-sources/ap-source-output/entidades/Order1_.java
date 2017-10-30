package entidades;

import entidades.Customer;
import entidades.OrderDetail;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-30T13:18:56")
@StaticMetamodel(Order1.class)
public class Order1_ { 

    public static volatile SingularAttribute<Order1, Date> date;
    public static volatile CollectionAttribute<Order1, OrderDetail> orderDetailCollection;
    public static volatile SingularAttribute<Order1, Integer> orderId;
    public static volatile SingularAttribute<Order1, String> deliveryAddress;
    public static volatile SingularAttribute<Order1, Customer> customerId;

}