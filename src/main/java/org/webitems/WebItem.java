package org.webitems;


import jakarta.persistence.*;
import org.orders.Order;

import java.util.Set;


@Entity
@Table(name="WebItems")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name="WebItemType",
        discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue(value="WebItem")
public abstract class WebItem {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idItem")
    private int idItem;
    @Column(name="name")
    private String name;
    @Column(name="addedBy")
    private long addedBy;
    @OneToMany(targetEntity=Order.class, mappedBy="webItem", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Order> order;
    @Column(name="quantity")
    private long quantity;

    public WebItem() {
    }

    public WebItem(String name, long quantity) {
        this.name = name;
        this.quantity = quantity;
        this.addedBy = 0;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(long addedBy) {
        this.addedBy = addedBy;
    }

    public Set<Order> getOrder() {
        return order;
    }

    public void setOrder(Set<Order> order) {
        this.order = order;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
