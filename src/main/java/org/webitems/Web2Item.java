package org.webitems;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="Web2Item")
public abstract class Web2Item extends WebItem{
    @Column(name="usdPrice")
    private int usdPrice;
    @Column(name="redeemCode")
    private String redeemCode;
    @Column(name="imgPath")
    private String imgPath;
    @Column(name="platform")
    private String platform;

    public Web2Item() {
    }

    public Web2Item(String name, long quantity, int usdPrice, String redeemCode, String imgPath, String platform) {
        super(name, quantity);
        this.usdPrice = usdPrice;
        this.redeemCode = redeemCode;
        this.imgPath = imgPath;
        this.platform = platform;
    }

    public int getUsdPrice() {
        return usdPrice;
    }

    public void setUsdPrice(int usdPrice) {
        this.usdPrice = usdPrice;
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

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
