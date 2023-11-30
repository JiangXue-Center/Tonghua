package com.hf.inventory.mapper;

import com.hf.core.model.entity.inventory.StockKeepingUnit;
import com.hf.inventory.model.vo.SkuBaseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SkuMapper {

    StockKeepingUnit selectStockKeepingUnitById(@Param("id") Long id);

    List<SkuBaseInfo> selectSkuBaseInfoBySpuId(@Param("spuId") long spuId);

    Integer selectStockBySkuId(@Param("skuId") long skuId);

    Integer updateStockBySkuId(@Param("skuId") long skuId, @Param("num") int num);

}
