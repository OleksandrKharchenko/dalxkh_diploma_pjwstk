package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="TelegramOperationalUser")
public abstract class TelegramOperationalUser extends TelegramUser {
    @Column(name="isOperational")
    private boolean isOperational;
    @Column(name="accessKey")
    private String accessKey;
    @Column(name="accessAddedBy")
    private int accessAddedBy;
    @Column(name="accessAddedBy")
    private int accessDisabledBy;

    public TelegramOperationalUser() {
    }

    public TelegramOperationalUser(int idUser, String email, int idTelegramUser, String displayName) {
        super(idUser, email, idTelegramUser, displayName);
        this.isOperational = false;
        this.accessKey = "none";
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
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
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
