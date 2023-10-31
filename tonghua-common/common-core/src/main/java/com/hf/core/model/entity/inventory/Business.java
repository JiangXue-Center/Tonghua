package com.hf.core.model.entity.inventory;

import java.util.Date;

/**
 * Java entity class for the 'business' table.
 */
public class Business {
    /**
     * 商家ID
     */
    private long businessId;

    /**
     * 商家名称
     */
    private String businessName;

    /**
     * 商标
     */
    private String businessLogo;

    /**
     * 商家地址
     */
    private String businessAddress;

    /**
     * 商家简介
     */
    private String businessDescription;

    /**
     * 注册时间
     */
    private Date gmtCreate;

    /**
     * 最后修改时间
     */
    private Date gmtModify;

    /**
     * 逻辑删除 (0 for active, 1 for deleted)
     */
    private boolean deleted;

    // Getters and setters for the above fields
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

    public String getBusinessLogo() {
        return businessLogo;
    }

    public void setBusinessLogo(String businessLogo) {
        this.businessLogo = businessLogo;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessDescription() {
        return businessDescription;
    }

    public void setBusinessDescription(String businessDescription) {
        this.businessDescription = businessDescription;
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
}
