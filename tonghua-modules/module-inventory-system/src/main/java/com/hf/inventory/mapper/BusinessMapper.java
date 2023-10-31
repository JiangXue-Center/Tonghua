package com.hf.inventory.mapper;

import com.hf.core.model.entity.inventory.Business;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BusinessMapper {

    Business selectBusinessById(@Param("id") Long id);

}
