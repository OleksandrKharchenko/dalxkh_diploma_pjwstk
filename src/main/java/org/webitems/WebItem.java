package org.webitems;


import jakarta.persistence.*;
import org.orders.Order;


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
    private int addedBy;
    @OneToOne(targetEntity=Order.class, mappedBy="webItem", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Order order;

    public WebItem() {
    }

    public WebItem(String name) {
        this.name = name;
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

    public int getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(int addedBy) {
        this.addedBy = addedBy;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
