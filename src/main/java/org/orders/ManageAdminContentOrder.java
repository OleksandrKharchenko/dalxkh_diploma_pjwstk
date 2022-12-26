package org.orders;

import jakarta.persistence.*;
import org.users.*;

@Entity
public class ManageAdminContentOrder {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idAdminContentOrder")
    private int idAdminContentOrder;
    @Column(name="comment")
    private String comment;
    @ManyToOne
    @JoinColumn(name="idOrder")
    private Order order;
    @ManyToOne
    @JoinColumn(name="idUser")
    private TelegramAdminContentUser adminContentUser;

    public ManageAdminContentOrder() {
    }

    public ManageAdminContentOrder(String comment, Order order, TelegramAdminContentUser adminContentUser) {
        this.comment = comment;
        this.order = order;
        this.adminContentUser = adminContentUser;
    }

    public int getIdAdminContentOrder() {
        return idAdminContentOrder;
    }

    public void setIdAdminContentOrder(int idAdminContentOrder) {
        this.idAdminContentOrder = idAdminContentOrder;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public TelegramAdminContentUser getAdminContentUser() {
        return adminContentUser;
    }

    public void setAdminContentUser(TelegramAdminContentUser adminContentUser) {
        this.adminContentUser = adminContentUser;
    }
}
