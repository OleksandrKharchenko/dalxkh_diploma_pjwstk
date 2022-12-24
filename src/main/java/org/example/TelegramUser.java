package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="TelegramUser")
public abstract class TelegramUser extends User {
    @Column(name="idTelegramUser", unique = true)
    private int idTelegramUser;
    @Column(name="displayName")
    private String displayName;

    public TelegramUser() {
    }

    public TelegramUser(String email, int idTelegramUser, String displayName) {
        super(email);
        this.idTelegramUser = idTelegramUser;
        this.displayName = displayName;
    }

    public int getIdTelegramUser() {
        return idTelegramUser;
    }

    public void setIdTelegramUser(int idTelegramUser) {
        this.idTelegramUser = idTelegramUser;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
