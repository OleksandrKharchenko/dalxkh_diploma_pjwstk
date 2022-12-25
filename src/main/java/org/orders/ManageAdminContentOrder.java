package org.orders;

import jakarta.persistence.*;
import org.users.*;
import org.users.TelegramAdminContentUserController;

import java.util.Set;

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
}
