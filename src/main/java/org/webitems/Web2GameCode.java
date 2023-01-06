package org.webitems;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="Web2GameCode")
public class Web2GameCode extends Web2Item{
    @Column(name="gameName", unique = true)
    private String gameName;

    public Web2GameCode() {
    }

    public Web2GameCode(String name, long quantity, int usdPrice, String redeemCode, String imgPath, String platform, String gameName) {
        super(name, quantity, usdPrice,  redeemCode, imgPath, platform);
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
