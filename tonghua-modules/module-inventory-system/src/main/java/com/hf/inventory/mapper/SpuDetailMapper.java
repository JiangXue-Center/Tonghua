package com.hf.inventory.mapper;

import com.hf.core.model.entity.inventory.SpecGroup;
import com.hf.core.model.entity.inventory.SpecParam;
import com.hf.core.model.entity.inventory.SpuDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface SpuDetailMapper {

    SpuDetail selectSpuDetailBySpuId(long id);

    List<SpecParam> selectSpecParam(@Param("set") Set<String> set);

    List<SpecGroup> selectSpecGroup(@Param("list") List<Long> list);

}
