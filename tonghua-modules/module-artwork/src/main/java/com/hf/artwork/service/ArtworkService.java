package com.hf.artwork.service;

import com.hf.artwork.model.vo.ArtworkIndex;
import com.hf.artwork.model.vo.ArtworkVO;
import com.hf.core.model.entity.artwork.Artwork;

import java.util.List;

public interface ArtworkService {

    Artwork selectById(Integer id);

    List<ArtworkIndex> selectArtworkIndexByKeyword(String keyword, Integer offset, Integer size);

    ArtworkVO selectArtworkVO(Long id);

    List<ArtworkIndex> recommendArtworkIndex(String id);

    List<ArtworkIndex> recommendArtworkIndexPage(String id, Integer offset, Integer size);
}
