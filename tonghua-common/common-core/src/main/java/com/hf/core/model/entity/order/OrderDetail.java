package com.hf.core.model.entity.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Java entity class for the 'order_detail' table.
 */
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单明细ID
     */
    private long orderDetailId;

    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * SPU ID
     */
    private long spuId;

    /**
     * SKU ID
     */
    private long skuId;

    /**
     * 商品SKU名称
     */
    private String skuName;

    /**
     * 商品SKU价格
     */
    private BigDecimal skuPrice;

    /**
     * 商品SKU图片
     */
    private String skuImage;

    /**
     * 购买数量
     */
    private int purchaseQuantity;

    /**
     * Creation Timestamp
     */
    private Date gmtCreate;

    /**
     * Last Modification Timestamp
     */
    private Date gmtModify;

    /**
     * Soft Delete Flag (0 for active, 1 for deleted)
     */
    private boolean deleted;

    // Getters and setters for the above fields
    // (Getter and setter methods for deleted should be boolean-based, not using isDeleted and setDeleted)

    public long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public long getSpuId() {
        return spuId;
    }

    public void setSpuId(long spuId) {
        this.spuId = spuId;
    }

    public long getSkuId() {
        return skuId;
    }

    public void setSkuId(long skuId) {
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

    public int getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(int purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderDetailId=" + orderDetailId +
                ", orderNumber='" + orderNumber + '\'' +
                ", spuId=" + spuId +
                ", skuId=" + skuId +
                ", skuName='" + skuName + '\'' +
                ", skuPrice=" + skuPrice +
                ", skuImage='" + skuImage + '\'' +
                ", purchaseQuantity=" + purchaseQuantity +
                ", gmtCreate=" + gmtCreate +
                ", gmtModify=" + gmtModify +
                ", deleted=" + deleted +
                '}';
    }
}
