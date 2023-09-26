package com.hf.artwork.mapper;


import com.hf.artwork.model.vo.ArtworkIndex;
import com.hf.artwork.model.vo.ArtworkVO;
import com.hf.core.model.entity.Artwork;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArtworkMapper {

    Artwork selectById(Integer id);

    List<Artwork> selectArtworks(String keyword);

    List<ArtworkIndex> selectIndexByKeyword(String keyword);

    ArtworkVO selectArtworkVoById(Long id);

    List<ArtworkIndex> selectIndexByIds(@Param("ids") List<Long> ids);

}
