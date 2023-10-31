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
    public List<ArtworkIndex> selectArtworkIndexByKeyword(String keyword, Integer offset, Integer size) {
        if (StrUtil.hasBlank(keyword)) {
            throw new RuntimeException("关键词不可为空");
        }
        if (offset == null || size == null) {
            throw new RuntimeException("页面不可为空");
        }
        if (offset < 1 || size != 20) {
            throw new RuntimeException("参数错误");
        }
        List<ArtworkIndex> artworkIndices = artworkMapper.selectIndexByKeyword(keyword, offset - 1, size);
        for (ArtworkIndex artworkIndex : artworkIndices) {
            String indexLink = artworkIndex.getIndexLink();
            String link = minIOService.path2Link(indexLink);
            artworkIndex.setIndexLink(link);
            String authorAvatar = artworkIndex.getAuthorAvatar();
            String avatarUrl = minIOService.path2Link(authorAvatar);
            artworkIndex.setAuthorAvatar(avatarUrl);
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
        String avatarUrl = artworkVO.getAuthorAvatar();
        String avatarLink = minIOService.path2Link(avatarUrl);
        artworkVO.setImageCollection(links);
        artworkVO.setAuthorAvatar(avatarLink);
        return artworkVO;
    }

    @Override
    public List<ArtworkIndex> recommendArtworkIndex(String id) {
        Map<Long, Integer> map = new HashMap<>();
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < 20; ) {
            long l = RandomUtil.randomLong(1, 80);
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
            String avatar = minIOService.path2Link(artworkIndex.getAuthorAvatar());
            artworkIndex.setAuthorAvatar(avatar);
            artworkIndex.setIndexLink(link);
        }
        return artworkIndices;
    }

    @Override
    public List<ArtworkIndex> recommendArtworkIndexPage(String id, Integer offset, Integer size) {
        if (offset == null || size == null) {
            throw new RuntimeException();
        }
        if (offset < 1) {
            throw new RuntimeException();
        }
        List<ArtworkIndex> artworkIndices = artworkMapper.pageRecommend(offset - 1, size);
        for (ArtworkIndex artworkIndex : artworkIndices) {
            String link = minIOService.path2Link(artworkIndex.getIndexLink());
            String avatar = minIOService.path2Link(artworkIndex.getAuthorAvatar());
            artworkIndex.setAuthorAvatar(avatar);
            artworkIndex.setIndexLink(link);
        }
        return artworkIndices;
    }

}
