package org.webitems;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="Web2GiftCard")
public class Web2GiftCard extends Web2Item{
    @Column(name="platformName")
    private String platformName;
    @Column(name="valueUsd")
    private int valueUsd;

    public Web2GiftCard(String name, int usdPrice, long quantity, String redeemCode, String imgPath, String platformName, int valueUsd) {
        super(name, usdPrice, quantity, redeemCode, imgPath);
        this.platformName = platformName;
        this.valueUsd = valueUsd;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public int getValueUsd() {
        return valueUsd;
    }

    public void setValueUsd(int valueUsd) {
        this.valueUsd = valueUsd;
    }
}
