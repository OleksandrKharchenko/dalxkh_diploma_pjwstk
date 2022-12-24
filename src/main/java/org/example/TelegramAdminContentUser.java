package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="TelegramAdminSuperContentUser")
public class TelegramAdminContentUser extends TelegramOperationalUser {

    public TelegramAdminContentUser() {

    }

    public TelegramAdminContentUser(String email, int idTelegramUser, String displayName) {
        super(email, idTelegramUser, displayName);
    }


}
