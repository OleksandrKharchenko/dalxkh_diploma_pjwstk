package org.users;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="TelegramUser")
public abstract class TelegramUser extends User {
    @Column(name="idTelegramUser", unique = true)
    private long idTelegramUser;
    @Column(name="displayName")
    private String displayName;

    public TelegramUser() {
    }

    public TelegramUser(long idTelegramUser, String displayName) {
        super();
        this.idTelegramUser = idTelegramUser;
        this.displayName = displayName;
    }
    public TelegramUser(String email, long idTelegramUser, String displayName) {
        super(email);
        this.idTelegramUser = idTelegramUser;
        this.displayName = displayName;
    }

    public long getIdTelegramUser() {
        return idTelegramUser;
    }

    public void setIdTelegramUser(long idTelegramUser) {
        this.idTelegramUser = idTelegramUser;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
