package com.hf.core.model.entity.inventory;

import java.util.Date;

/**
 * Java entity class for the 'spec_group' table.
 */
public class SpecGroup {
    /**
     * 主键
     */
    private long specGroupId;

    /**
     * 商品分类id，一个分类下有多个规格组
     */
    private long categoryId;

    /**
     * 规格组的名称
     */
    private String name;

    /**
     * 添加时间
     */
    private Date gmtCreate;

    /**
     * 最后修改时间
     */
    private Date gmtModify;

    // Getters and setters for the above fields
    public long getSpecGroupId() {
        return specGroupId;
    }

    public void setSpecGroupId(long specGroupId) {
        this.specGroupId = specGroupId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
