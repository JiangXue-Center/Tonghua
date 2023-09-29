package com.hf.shopping.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class SpuIndexVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * SpuId
     */
    private Long spuId;

    /**
     * Spu标题
     */
    private String title;

    /**
     * Spu主图
     */
    private String mainImage;

    /**
     * 价格
     */
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

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SpuIndexDTO{" +
                "spuId=" + spuId +
                ", title='" + title + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", price=" + price +
                '}';
    }
}
