package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="TelegramClientUser")
public class TelegramClientUser extends TelegramUser {
    @Column(name="discount")
    private int discount;
    @Column(name="cyptoWalletAdress")
    private String cyptoWalletAdress;
    @Column(name="creditCard")
    private int creditCard;
    @Column(name="banStatus")
    private boolean banStatus;
    @Column(name="bannedBy")
    private int bannedBy;


    public TelegramClientUser() {
    }

    public TelegramClientUser(String email, int idTelegramUser, String displayName) {
        super(email, idTelegramUser, displayName);
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getCyptoWalletAdress() {
        return cyptoWalletAdress;
    }

    public void setCyptoWalletAdress(String cyptoWalletAdress) {
        this.cyptoWalletAdress = cyptoWalletAdress;
    }

    public int getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(int creditCard) {
        this.creditCard = creditCard;
    }

    public boolean isBanStatus() {
        return banStatus;
    }

    public void setBanStatus(boolean banStatus) {
        this.banStatus = banStatus;
    }

    public int getBannedBy() {
        return bannedBy;
    }

    public void setBannedBy(int bannedBy) {
        this.bannedBy = bannedBy;
    }
}
