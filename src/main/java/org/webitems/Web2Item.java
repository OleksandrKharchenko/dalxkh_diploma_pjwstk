package org.webitems;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="Web2Item")
public abstract class Web2Item extends WebItem{
    @Column(name="usdPrice")
    private int usdPrice;
    @Column(name="isAvailable")
    private boolean isAvailable;
    @Column(name="redeemCode")
    private String redeemCode;
    @Column(name="imgPath")
    private String imgPath;

    public Web2Item() {
    }

    public Web2Item(String name, long quantity, int usdPrice, String redeemCode, String imgPath) {
        super(name, quantity);
        this.usdPrice = usdPrice;
        this.isAvailable = true;
        this.redeemCode = redeemCode;
        this.imgPath = imgPath;
    }

    public int getUsdPrice() {
        return usdPrice;
    }

    public void setUsdPrice(int usdPrice) {
        this.usdPrice = usdPrice;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getRedeemCode() {
        return redeemCode;
    }

    public void setRedeemCode(String redeemCode) {
        this.redeemCode = redeemCode;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
