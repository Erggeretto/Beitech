package entidades;

import entidades.Order1;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-30T13:18:56")
@StaticMetamodel(OrderDetail.class)
public class OrderDetail_ { 

    public static volatile SingularAttribute<OrderDetail, Integer> idOrderDetail;
    public static volatile SingularAttribute<OrderDetail, Order1> orderId;
    public static volatile SingularAttribute<OrderDetail, Long> price;
    public static volatile SingularAttribute<OrderDetail, String> productDescription;

}