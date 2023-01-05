package org.users;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
    private long bannedBy;
    @OneToMany(targetEntity=Order.class, mappedBy="telegramClientUser", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Order> orderList;


    public TelegramClientUser() {
    }

    public TelegramClientUser(long idTelegramUser, String displayName) {
        super(idTelegramUser, displayName);
    }

    public TelegramClientUser(String email, long idTelegramUser, String displayName) {
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

    public long getBannedBy() {
        return bannedBy;
    }

    public void setBannedBy(long bannedBy) {
        this.bannedBy = bannedBy;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

}
