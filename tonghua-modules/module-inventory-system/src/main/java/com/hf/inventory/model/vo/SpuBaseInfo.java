package com.hf.inventory.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class SpuBaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long spuId;

    private String title;

    private String spuImages;

    private BigDecimal price;

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpuImages() {
        return spuImages;
    }

    public void setSpuImages(String spuImages) {
        this.spuImages = spuImages;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
