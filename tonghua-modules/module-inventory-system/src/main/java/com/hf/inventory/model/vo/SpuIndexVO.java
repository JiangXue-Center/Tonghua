package com.hf.inventory.model.vo;

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
    private String subTitle;

    /**
     * Spu主图
     */
    private String mainImage;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 商家id
     */
    private long businessId;

    /**
     * 商家名称
     * @return
     */
    private String businessName;

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
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

    public long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(long businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    @Override
    public String toString() {
        return "SpuIndexVO{" +
                "spuId=" + spuId +
                ", subTitle='" + subTitle + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", price=" + price +
                ", businessId=" + businessId +
                ", businessName='" + businessName + '\'' +
                '}';
    }
}
