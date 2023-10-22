package com.hf.core.model.entity.inventory;

import java.util.Date;

/**
 * Java entity class for the 'spec_param' table.
 */
public class SpecParam {
    /**
     * 主键
     */
    private long specParamId;

    /**
     * 商品分类id
     */
    private long categoryId;

    /**
     * 规格组ID
     */
    private long groupId;

    /**
     * 参数名
     */
    private String name;

    /**
     * 是否是数字类型参数，true或false
     */
    private boolean numeric;

    /**
     * 数字类型参数的单位，非数字类型可以为空
     */
    private String unit;

    /**
     * 是否是sku通用属性，true或false
     */
    private boolean generic;

    /**
     * 是否用于搜索过滤，true或 false
     */
    private boolean searching;

    /**
     * 数值类型参数，如果需要搜索，则添加分段间隔值，如CPU频率间隔：0.5-1.0
     */
    private String segments;

    /**
     * 添加时间
     */
    private Date gmtCreate;

    /**
     * 最后修改时间
     */
    private Date gmtModify;

    // Getters and setters for the above fields
    public long getSpecParamId() {
        return specParamId;
    }

    public void setSpecParamId(long specParamId) {
        this.specParamId = specParamId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNumeric() {
        return numeric;
    }

    public void setNumeric(boolean numeric) {
        this.numeric = numeric;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public boolean isGeneric() {
        return generic;
    }

    public void setGeneric(boolean generic) {
        this.generic = generic;
    }

    public boolean isSearching() {
        return searching;
    }

    public void setSearching(boolean searching) {
        this.searching = searching;
    }

    public String getSegments() {
        return segments;
    }

    public void setSegments(String segments) {
        this.segments = segments;
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
