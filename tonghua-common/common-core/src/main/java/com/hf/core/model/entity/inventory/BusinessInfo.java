package com.hf.core.model.entity.inventory;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Java entity class for the 'business_info' table.
 */
public class BusinessInfo {
    /**
     * 工商信息ID
     */
    private long businessInfoId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 注册号
     */
    private String registrationNumber;

    /**
     * 注册地址
     */
    private String registeredAddress;

    /**
     * 经营类型
     */
    private String businessType;

    /**
     * 法人
     */
    private String legalRepresentative;

    /**
     * 注册资金
     */
    private BigDecimal registeredCapital;

    /**
     * 注册日期
     */
    private Date establishmentDate;

    /**
     * 经营范围
     */
    private String businessScope;

    /**
     * 营业执照
     */
    private String businessLicense;

    /**
     * 所属商家
     */
    private long businessId;

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
    // (Getter and setter methods for BigDecimal, Date, and other fields)

    public long getBusinessInfoId() {
        return businessInfoId;
    }

    public void setBusinessInfoId(long businessInfoId) {
        this.businessInfoId = businessInfoId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegisteredAddress() {
        return registeredAddress;
    }

    public void setRegisteredAddress(String registeredAddress) {
        this.registeredAddress = registeredAddress;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public BigDecimal getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(BigDecimal registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public Date getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(Date establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(long businessId) {
        this.businessId = businessId;
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
