package com.hf.artwork.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.hf.artwork.mapper.artwork.ArtworkMapper;
import com.hf.artwork.model.vo.ArtworkIndex;
import com.hf.artwork.model.vo.ArtworkVO;
import com.hf.artwork.service.ArtworkService;
import com.hf.core.exception.ParamException;
import com.hf.core.model.entity.artwork.Artwork;
import com.hf.minio.service.MinIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArtworkServiceImpl implements ArtworkService {

    @Autowired
    private ArtworkMapper artworkMapper;

    @Autowired
    private MinIOService minIOService;

    @Override
    public Artwork selectById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            throw new RuntimeException("id不可为空");
        }
        if (id < 0) {
            throw new RuntimeException("id不可为负");
        }
        Artwork artwork = artworkMapper.selectById(id);
        return artwork;
    }

    @Override
    public List<ArtworkIndex> selectArtworkIndexByKeyword(String keyword) {
        if (StrUtil.hasBlank(keyword)) {
            throw new RuntimeException("关键词不可为空");
        }
        List<ArtworkIndex> artworkIndices = artworkMapper.selectIndexByKeyword(keyword);
        for (ArtworkIndex artworkIndex : artworkIndices) {
            String indexLink = artworkIndex.getIndexLink();
            String link = minIOService.path2Link(indexLink);
            artworkIndex.setIndexLink(link);
        }
        return artworkIndices;
    }

    @Override
    public ArtworkVO selectArtworkVO(Long id) {
        if (ObjectUtil.isNull(id)) {
            throw new ParamException();
        }
        ArtworkVO artworkVO = artworkMapper.selectArtworkVoById(id);
        List<String> imageCollection = artworkVO.getImageCollection();
        List<String> links = minIOService.paths2Links(imageCollection);
        artworkVO.setImageCollection(links);
        return artworkVO;
    }

    @Override
    public List<ArtworkIndex> recommendArtworkIndex(String id) {
        Map<Long, Integer> map = new HashMap<>();
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < 6; ) {
            long l = RandomUtil.randomLong(1, 11);
            if (!map.containsKey(l)) {
                map.put(l, i);
                ids.add(l);
                i++;
            }
        }
        System.out.println(ids);
        List<ArtworkIndex> artworkIndices = artworkMapper.selectIndexByIds(ids);
        for (ArtworkIndex artworkIndex : artworkIndices) {
            String link = minIOService.path2Link(artworkIndex.getIndexLink());
            artworkIndex.setIndexLink(link);
        }
        return artworkIndices;
    }

}
