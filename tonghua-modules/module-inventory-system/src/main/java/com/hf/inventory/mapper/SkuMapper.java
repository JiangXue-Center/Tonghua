package com.hf.inventory.mapper;

import com.hf.core.model.entity.inventory.StockKeepingUnit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SkuMapper {

    StockKeepingUnit selectStockKeepingUnitById(@Param("id") Long id);

}
