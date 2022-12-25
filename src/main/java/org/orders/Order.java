package org.orders;

import jakarta.persistence.*;
import org.payments.Payment;
import org.users.TelegramClientUser;
import org.webitems.WebItem;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="Orders")
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idOrder")
    private int idOrder;
    @ManyToOne
    @JoinColumn(name="idUser")
    private TelegramClientUser telegramClientUser;
    @OneToOne
    @JoinColumn(name="idItem")
    private WebItem webItem;
    @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="idPayment")
    private Payment payment;
    @Column(name="state")
    private String state;
    @Column(name="emailClient")
    private String emailClient;
    @Column(name="usdEquivalentPrice")
    private int usdEquivalentPrice;
    @Column(name="cryptoEquivalentPrice")
    private int cryptoEquivalentPrice;

    public Order() {
    }

    public Order(TelegramClientUser telegramClientUser, WebItem webItem, Payment payment, String state, String emailClient, int usdEquivalentPrice, int cryptoEquivalentPrice) {
        this.telegramClientUser = telegramClientUser;
        this.webItem = webItem;
        this.payment = payment;
        this.state = state;
        this.emailClient = telegramClientUser.getEmail();
        this.usdEquivalentPrice = usdEquivalentPrice;
        this.cryptoEquivalentPrice = cryptoEquivalentPrice;
    }

    public TelegramClientUser getTelegramClientUser() {
        return telegramClientUser;
    }

    public void setTelegramClientUser(TelegramClientUser telegramClientUser) {
        this.telegramClientUser = telegramClientUser;
    }

    public WebItem getWebItem() {
        return webItem;
    }

    public void setWebItem(WebItem webItem) {
        this.webItem = webItem;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    public int getUsdEquivalentPrice() {
        return usdEquivalentPrice;
    }

    public void setUsdEquivalentPrice(int usdEquivalentPrice) {
        this.usdEquivalentPrice = usdEquivalentPrice;
    }

    public int getCryptoEquivalentPrice() {
        return cryptoEquivalentPrice;
    }

    public void setCryptoEquivalentPrice(int cryptoEquivalentPrice) {
        this.cryptoEquivalentPrice = cryptoEquivalentPrice;
    }
}
