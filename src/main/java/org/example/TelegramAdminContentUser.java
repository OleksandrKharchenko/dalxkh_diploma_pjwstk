package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="TelegramAdminSuperContentUser")
public class TelegramAdminContentUser extends TelegramOperationalUser implements TelegramAdminContentUserController{

    public TelegramAdminContentUser() {

    }

    public TelegramAdminContentUser(String email, int idTelegramUser, String displayName) {
        super(email, idTelegramUser, displayName);
    }

    @Override
    public String deleteWebItem(){

        return "Item deleted.";
    }

    @Override
    public String addWebItem(){

        return "Item added.";
    }

    @Override
    public String editWebItem() {

        return "Item updated.";
    }

}
