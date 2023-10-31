package com.hf.inventory.model.vo;

import java.io.Serializable;

public class BusinessVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long businessId;

    private String logo;

    private String businessName;

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
}
