package com.hf.core.model.entity.inventory;

import java.util.Date;

/**
 * Java entity class for the 'brand' table.
 */
public class Brand {
    /**
     * Primary Key
     */
    private long brandId;

    /**
     * Brand Name
     */
    private String brandName;

    /**
     * Logo URL
     */
    private String logo;

    /**
     * Brand Description
     */
    private String brandDescription;

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
    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBrandDescription() {
        return brandDescription;
    }

    public void setBrandDescription(String brandDescription) {
        this.brandDescription = brandDescription;
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
