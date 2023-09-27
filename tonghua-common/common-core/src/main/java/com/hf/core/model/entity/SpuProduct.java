package com.hf.core.model.entity;

import java.util.Date;

public class SpuProduct {
    /**
     * 主键 ID
     */
    private Long spuId;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 产品标题
     */
    private String title;

    /**
     * 分类 ID
     */
    private Long categoryId;

    /**
     * 状态 (0为下架，1为上架)
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 主图 URL
     */
    private String mainImage;

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

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
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
        return "SpuProduct{" +
                "spuId=" + spuId +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", categoryId=" + categoryId +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModify=" + gmtModify +
                ", deleted=" + deleted +
                '}';
    }
}
