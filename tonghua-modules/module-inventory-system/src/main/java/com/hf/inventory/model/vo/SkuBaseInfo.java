package com.hf.inventory.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class SkuBaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long skuId;

    private BigDecimal price;

    private String image;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "SkuBaseInfo{" +
                "skuId=" + skuId +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }
}
