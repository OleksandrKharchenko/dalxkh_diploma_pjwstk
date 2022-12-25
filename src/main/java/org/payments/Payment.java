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
    @Column(name="state")
    private String state;
    @Column(name="amount")
    private int amount;

    public Payment() {
    }

    public Payment(int idPayment, Order order, int amount) {
        this.idPayment = idPayment;
        this.order = order;
        this.state = "not payed";
        this.amount = amount;
    }

    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
