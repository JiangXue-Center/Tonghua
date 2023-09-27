package com.hf.artwork.service;

import com.hf.artwork.model.vo.ArtworkIndex;
import com.hf.artwork.model.vo.ArtworkVO;
import com.hf.core.model.entity.Artwork;

import java.util.List;

public interface ArtworkService {

    Artwork selectById(Integer id);

    List<ArtworkIndex> selectArtworkIndexByKeyword(String keyword);

    ArtworkVO selectArtworkVO(Long id);

    List<ArtworkIndex> recommendArtworkIndex(String id);
}
