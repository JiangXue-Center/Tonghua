package com.hf.shopping.service.impl;

import cn.hutool.core.util.StrUtil;
import com.hf.minio.service.MinIOService;
import com.hf.shopping.mapper.SkuMapper;
import com.hf.shopping.mapper.SpuMapper;
import com.hf.shopping.model.vo.SpuIndexVO;
import com.hf.shopping.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hf.minio.constant.MinIOConstant.SKU_FOLDER;
import static com.hf.minio.constant.MinIOConstant.SPU_BUCKET_NAME;

@Service
public class InventoryServiceImpl implements InventoryService {
    
    @Autowired
    private SpuMapper spuMapper;
    
    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private MinIOService minIOService;
    
    @Override
    public List<SpuIndexVO> selectSpuIndexVoByKeyword(String keyword) {
        List<SpuIndexVO> spuIndexVOS = spuMapper.selectSpuIndexByKeyword(keyword);
        for (SpuIndexVO spuIndexVO : spuIndexVOS) {
            if (!StrUtil.hasBlank(spuIndexVO.getMainImage())) {
                String link = minIOService.path2Link(spuIndexVO.getMainImage(), SPU_BUCKET_NAME, SKU_FOLDER);
                spuIndexVO.setMainImage(link);
            }
        }
        return spuIndexVOS;
    }

    @Override
    public void selectSpuDetailById(Long productId) {

    }


}
