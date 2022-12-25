package org.users;

import jakarta.persistence.*;
import org.orders.Order;

import java.util.List;

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
    @OneToMany(targetEntity=Order.class, mappedBy="telegramClientUser", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Order> orderList;


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

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
