package org.webitems;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="Web2GiftCard")
public class Web2GiftCard extends Web2Item{
    @Column(name="valueUsd")
    private int valueUsd;

    public Web2GiftCard() {
    }

    public Web2GiftCard(String name, long quantity, int usdPrice,  String redeemCode, String imgPath, String platform, int valueUsd) {
        super(name, quantity, usdPrice, redeemCode, imgPath, platform);
        this.valueUsd = valueUsd;
    }

    public int getValueUsd() {
        return valueUsd;
    }

    public void setValueUsd(int valueUsd) {
        this.valueUsd = valueUsd;
    }
}
