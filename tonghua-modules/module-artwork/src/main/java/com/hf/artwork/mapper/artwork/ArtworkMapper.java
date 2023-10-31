package com.hf.artwork.mapper.artwork;


import com.hf.artwork.model.vo.ArtworkIndex;
import com.hf.artwork.model.vo.ArtworkVO;
import com.hf.core.model.entity.artwork.Artwork;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArtworkMapper {

    Artwork selectById(Integer id);

//    List<Artwork> selectArtworks(String keyword);

    List<ArtworkIndex> selectIndexByKeyword(
            @Param("keyword") String keyword,
            @Param("offset") int offset,
            @Param("size") int size);

    ArtworkVO selectArtworkVoById(Long id);

    List<ArtworkIndex> selectIndexByIds(@Param("ids") List<Long> ids);

    List<Artwork> selectAllByIdList(Long id);

    int updateImageCollectionInt(@Param("id") Long id, @Param("img") String img);

    List<Artwork> selectAll();

    List<ArtworkIndex> pageRecommend(
            @Param("offset") int offset,
            @Param("size") int size
    );

}
