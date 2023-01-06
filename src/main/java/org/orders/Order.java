package org.orders;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.payments.Payment;
import org.users.TelegramClientUser;
import org.webitems.Web2Item;
import org.webitems.Web3CryptoItem;
import org.webitems.WebItem;

import java.sql.Timestamp;
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
    @JoinColumn(name="idUser", nullable = false)
    private TelegramClientUser telegramClientUser;
    @ManyToOne
    @JoinColumn(name="idItem", nullable = false)
    private WebItem webItem;
    @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="idPayment")
    private Payment payment;
    @OneToMany(targetEntity=ManageAdminContentOrder.class, mappedBy="order", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<ManageAdminContentOrder> contentOrders;
    @Column(name="state")
    private String state;
    @Column(name="emailClient")
    private String emailClient;
    @Column(name="usdEquivalentPrice")
    private int usdEquivalentPrice;
    @Column(name="cryptoEquivalentPrice")
    private double cryptoEquivalentPrice;
    @Column(name="timeStamp")
    private Timestamp timeStamp;
    @Column(name="cryptoTxHashConfirmation")
    private String cryptoTxHashConfirmation;

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
        this.timeStamp = new Timestamp(System.currentTimeMillis());
    }

    public Order(TelegramClientUser telegramClientUser, WebItem webItem) {
        this.telegramClientUser = telegramClientUser;
        this.webItem = webItem;
        this.payment = null;
        this.state = "initiated";
        this.emailClient = telegramClientUser.getEmail();
        if (webItem instanceof Web2Item) {
            this.usdEquivalentPrice = (((Web2Item) webItem).getUsdPrice()) ;
        }
        if (webItem instanceof Web3CryptoItem) {
            this.cryptoEquivalentPrice = (((Web3CryptoItem) webItem).getCryptoPrice()) ;
        }
        this.timeStamp = new Timestamp(System.currentTimeMillis());
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

    public double getCryptoEquivalentPrice() {
        return cryptoEquivalentPrice;
    }

    public void setCryptoEquivalentPrice(double cryptoEquivalentPrice) {
        this.cryptoEquivalentPrice = cryptoEquivalentPrice;
    }

    public String getCryptoTxHashNFT() {
        return cryptoTxHashConfirmation;
    }

    public void setCryptoTxHashNFT(String cryptoTxHash) {
        this.cryptoTxHashConfirmation = cryptoTxHash;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public Timestamp getTimeStamp() { 
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getCryptoTxHashConfirmation() {
        return cryptoTxHashConfirmation;
    }

    public void setCryptoTxHashConfirmation(String cryptoTxHashConfirmation) {
        this.cryptoTxHashConfirmation = cryptoTxHashConfirmation;
    }
}
