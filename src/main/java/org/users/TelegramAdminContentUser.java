package org.users;

import jakarta.persistence.*;
import org.orders.ManageAdminContentOrder;
import org.orders.Order;
import java.util.Set;

@Entity
@DiscriminatorValue(value="TelegramAdminSuperContentUser")
public class TelegramAdminContentUser extends TelegramOperationalUser {
    @OneToMany(targetEntity = ManageAdminContentOrder.class, mappedBy = "adminContentUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ManageAdminContentOrder> contentOrders;


    public TelegramAdminContentUser() {
    }

    public TelegramAdminContentUser(String email, int idTelegramUser, String displayName) {
        super(email, idTelegramUser, displayName);
    }

}
