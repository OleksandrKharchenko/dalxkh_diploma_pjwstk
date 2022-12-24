package org.webitems;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="Web2GameCode")
public class Web2GameCode extends Web2Item{
    @Column(name="gameName", unique = true)
    private String gameName;

    public Web2GameCode(String name, int usdPrice, long quantity, String redeemCode, String imgPath, String gameName) {
        super(name, usdPrice, quantity, redeemCode, imgPath);
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
