package org.users;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="TelegramOperationalUser")
public abstract class TelegramOperationalUser extends TelegramUser {
    @Column(name="isOperational")
    private boolean isOperational;
    @Column(name="accessType")
    private String accessType;
    @Column(name="accessAddedBy")
    private int accessAddedBy;
    @Column(name="accessDisabledBy")
    private int accessDisabledBy;

    public TelegramOperationalUser() {
    }

    public TelegramOperationalUser(String email, int idTelegramUser, String displayName) {
        super(email, idTelegramUser, displayName);
        this.isOperational = true;
        this.accessType = "none";
        this.accessAddedBy = 0;
        this.accessDisabledBy = 0;
    }

    public boolean isOperational() {
        return isOperational;
    }

    public void setOperational(boolean operational) {
        isOperational = operational;
    }

    public String getAccessKey() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public int getAccessAddedBy() {
        return accessAddedBy;
    }

    public void setAccessAddedBy(int accessAddedBy) {
        this.accessAddedBy = accessAddedBy;
    }

    public int getAccessDisabledBy() {
        return accessDisabledBy;
    }

    public void setAccessDisabledBy(int accessDisabledBy) {
        this.accessDisabledBy = accessDisabledBy;
    }
}
