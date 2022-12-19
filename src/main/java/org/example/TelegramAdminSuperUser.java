package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="TelegramAdminSuperUser")
public class TelegramAdminSuperUser extends TelegramOperationalUser{
    @Column(name="accessSuperKey")
    private String accessSuperKey;

    public TelegramAdminSuperUser() {

    }

    public TelegramAdminSuperUser(int idUser, String email, int idTelegramUser, String displayName, String accessSuperKey) {
        super(idUser, email, idTelegramUser, displayName);
        this.accessSuperKey = accessSuperKey;
    }


    public String getAccessSuperKey() {
        return accessSuperKey;
    }

    public void setAccessSuperKey(String accessSuperKey) {
        this.accessSuperKey = accessSuperKey;
    }
}
