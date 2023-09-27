package com.hf.core.model.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SkuSpecificationInfo {
    /**
     * 主键 ID
     */
    private Long skuId;

    /**
     * 产品主键 ID
     */
    private Long productId;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 规格
     */
    private String specification;

    /**
     * 规格图片 URL
     */
    private String specificationImage;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 创建时间戳
     */
    private Date gmtCreate;

    /**
     * 最后修改时间戳
     */
    private Date gmtModify;

    /**
     * 软删除标志 (0为活动，1为删除)
     */
    private Integer deleted;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getSpecificationImage() {
        return specificationImage;
    }

    public void setSpecificationImage(String specificationImage) {
        this.specificationImage = specificationImage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "SkuSpecificationInfo{" +
                "specId=" + skuId +
                ", productId=" + productId +
                ", status=" + status +
                ", specification='" + specification + '\'' +
                ", specificationImage='" + specificationImage + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", version=" + version +
                ", gmtCreate=" + gmtCreate +
                ", gmtModify=" + gmtModify +
                ", deleted=" + deleted +
                '}';
    }
}
