package org.webitems;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="Web3NFT")
public class Web3NFT extends Web3CryptoItem{
    @Column(name="NFTTokenId", unique = true)
    private String NFTTokenId;
    @Column(name="quality")
    private String quality;
    @Column(name="imgPath")
    private String imgPath;

    public Web3NFT() {
    }

    public Web3NFT(String name, int cryptoPrice, String contractAddress, String blockchain, String tokenStandard, String NFTTokenId, String quality, String imgPath) {
        super(name, cryptoPrice, contractAddress, blockchain, tokenStandard);
        this.NFTTokenId = NFTTokenId;
        this.quality = quality;
        this.imgPath = imgPath;
    }

    public String getNFTTokenId() {
        return NFTTokenId;
    }

    public void setNFTTokenId(String NFTTokenId) {
        this.NFTTokenId = NFTTokenId;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}