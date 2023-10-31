package com.hf.core.model.entity.inventory;

import java.util.Date;

/**
 * Java entity class for the 'spu_detail' table.
 */
public class SpuDetail {
    /**
     * SPU详情ID
     */
    private long spuDetailId;

    /**
     * SPU ID
     */
    private long spuId;

    /**
     * 默认展示的sku_id
     */
    private long defaultSkuId;


    /**
     * 商品描述信息
     */
    private String description;

    /**
     * 通用规格参数数据
     */
    private String genericSpec;

    /**
     * 特有规格参数及可选值信息，json格式
     */
    private String specialSpec;

    /**
     * 包装清单
     */
    private String packingList;

    /**
     * 售后服务
     */
    private String afterService;

    /**
     * Creation Timestamp
     */
    private Date gmtCreate;

    /**
     * Last Modification Timestamp
     */
    private Date gmtModify;

    // Getters and setters for the above fields
    public long getSpuDetailId() {
        return spuDetailId;
    }

    public void setSpuDetailId(long spuDetailId) {
        this.spuDetailId = spuDetailId;
    }

    public long getSpuId() {
        return spuId;
    }

    public void setSpuId(long spuId) {
        this.spuId = spuId;
    }

    public long getDefaultSkuId() {
        return defaultSkuId;
    }

    public void setDefaultSkuId(long defaultSkuId) {
        this.defaultSkuId = defaultSkuId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenericSpec() {
        return genericSpec;
    }

    public void setGenericSpec(String genericSpec) {
        this.genericSpec = genericSpec;
    }

    public String getSpecialSpec() {
        return specialSpec;
    }

    public void setSpecialSpec(String specialSpec) {
        this.specialSpec = specialSpec;
    }

    public String getPackingList() {
        return packingList;
    }

    public void setPackingList(String packingList) {
        this.packingList = packingList;
    }

    public String getAfterService() {
        return afterService;
    }

    public void setAfterService(String afterService) {
        this.afterService = afterService;
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
