package org.webitems;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="Web3CryptoItem")
public abstract class Web3CryptoItem extends WebItem {
    @Column(name="cryptoPrice")
    private int cryptoPrice;
    @Column(name="contractAddress", unique = true)
    private String contractAddress;
    @Column(name="blockchain")
    private String blockchain;
    @Column(name="tokenStandard")
    private String tokenStandard;

    public Web3CryptoItem() {
    }

    public Web3CryptoItem(String name, long quantity, int cryptoPrice, String contractAddress, String blockchain, String tokenStandard) {
        super(name, quantity);
        this.cryptoPrice = cryptoPrice;
        this.contractAddress = contractAddress;
        this.blockchain = blockchain;
        this.tokenStandard = tokenStandard;
    }

    public int getCryptoPrice() {
        return cryptoPrice;
    }

    public void setCryptoPrice(int cryptoPrice) {
        this.cryptoPrice = cryptoPrice;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getBlockchain() {
        return blockchain;
    }

    public void setBlockchain(String blockchain) {
        this.blockchain = blockchain;
    }

    public String getTokenStandard() {
        return tokenStandard;
    }

    public void setTokenStandard(String tokenStandard) {
        this.tokenStandard = tokenStandard;
    }
}
