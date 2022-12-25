package org.payments;

import jakarta.persistence.*;
import org.orders.Order;

@Entity
@Table(name="Payments")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name="PaymentType",
        discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue(value="Payment")
public abstract class Payment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idPayment")
    private int idPayment;
    @OneToOne(targetEntity=Order.class, mappedBy="payment", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Order order;
}
