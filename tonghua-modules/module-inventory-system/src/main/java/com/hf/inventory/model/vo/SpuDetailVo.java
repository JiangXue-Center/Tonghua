package com.hf.inventory.model.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class SpuDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * SPU详情ID
     */
    private long spuDetailId;

    /**
     * SPU ID
     */
    private long spuId;

    /**
     * 商品描述信息
     */
    private String description;

    /**
     * 通用规格参数数据
     * key: 参数组名
     * value: 参数名称: 参数值
     */
    private Map<String, Map<String, String>> genericSpec;

    /**
     * 特有规格参数及可选值信息，json格式
     */
    private Map<String, List<String>> specialSpec;

    /**
     * 包装清单
     */
    private String packingList;

    /**
     * 售后服务
     */
    private String afterService;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Map<String, String>> getGenericSpec() {
        return genericSpec;
    }

    public void setGenericSpec(Map<String, Map<String, String>> genericSpec) {
        this.genericSpec = genericSpec;
    }

    public Map<String, List<String>> getSpecialSpec() {
        return specialSpec;
    }

    public void setSpecialSpec(Map<String, List<String>> specialSpec) {
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

    @Override
    public String toString() {
        return "SpuDetailVo{" +
                "spuDetailId=" + spuDetailId +
                ", spuId=" + spuId +
                ", description='" + description + '\'' +
                ", genericSpec=" + genericSpec +
                ", specialSpec=" + specialSpec +
                ", packingList='" + packingList + '\'' +
                ", afterService='" + afterService + '\'' +
                '}';
    }
}
