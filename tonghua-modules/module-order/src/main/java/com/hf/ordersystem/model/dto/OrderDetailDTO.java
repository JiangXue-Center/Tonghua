package com.hf.ordersystem.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderDetailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * SPU ID
     */
    @NotNull(message = "spuId不可为空")
    private Long spuId;

    /**
     * SKU ID
     */
    @NotNull(message = "skuId不可为空")
    private Long skuId;

    /**
     * 商品SKU名称
     */
    @NotBlank(message = "skuName不可为空")
    private String skuName;

    /**
     * 商品SKU价格
     */
    @NotNull(message = "skuPrice不可为空")
    private BigDecimal skuPrice;

    /**
     * 商品SKU图片
     */
    @NotBlank(message = "skuImage不可为空")
    private String skuImage;

    /**
     * 购买数量
     */
    @NotNull(message = "purchaseQuantity不可为空")
    private Integer purchaseQuantity;

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public BigDecimal getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(BigDecimal skuPrice) {
        this.skuPrice = skuPrice;
    }

    public String getSkuImage() {
        return skuImage;
    }

    public void setSkuImage(String skuImage) {
        this.skuImage = skuImage;
    }

    public Integer getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(Integer purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }
}
