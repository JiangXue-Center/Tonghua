package com.hf.shopping.mapper;

import com.hf.core.model.entity.SpuProduct;
import com.hf.shopping.model.vo.SpuIndexVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SpuMapper {

    List<SpuIndexVO> selectSpuIndexByKeyword(@Param("keyword") String keyword);

    @Insert("INSERT INTO spu_product (spu_name, title, category_id, status, remark, main_image, gmt_create, gmt_modify, deleted) " +
            "VALUES (#{spuName}, #{title}, #{categoryId}, #{status}, #{remark}, #{mainImage}, #{gmtCreate}, #{gmtModify}, #{deleted})")
    void insertSPUProduct(SpuProduct spuProduct);
}

