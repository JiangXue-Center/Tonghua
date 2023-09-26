package com.hf.artwork.controller;


import com.hf.artwork.model.vo.ArtworkIndex;
import com.hf.artwork.model.vo.ArtworkVO;
import com.hf.artwork.service.ArtworkService;
import com.hf.core.model.Result;
import com.hf.core.model.entity.Artwork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/artwork")
public class ArtworkController {

    @Autowired
    private ArtworkService artworkService;

//    @GetMapping("/id/{id}")
//    public Result<Artwork> selectArtworkById(@PathVariable("id") Integer id) {
//        return Result.success(artworkService.selectById(id));
//    }

    @GetMapping("/vo/keyword/{keyword}")
    public Result<List<ArtworkIndex>> selectArtworks(@PathVariable("keyword") String keyword) {
        List<ArtworkIndex> artworkIndices = artworkService.selectArtworkIndexByKeyword(keyword);
        return Result.success(artworkIndices);
    }

    @GetMapping("/vo/id/{id}")
    public Result<ArtworkVO> selectArtwork(@PathVariable("id") Long id) {
        ArtworkVO artworkVO = artworkService.selectArtworkVO(id);
        return Result.success(artworkVO);
    }

    @GetMapping("/index")
    public Result<List<ArtworkIndex>> index() {
        List<ArtworkIndex> artworkIndices = artworkService.recommendArtworkIndex("");
        return Result.success(artworkIndices);
    }


}
