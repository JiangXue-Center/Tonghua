package com.hf.artwork.controller;

import com.hf.minio.service.MinIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fs")
public class PhotoController {

    @Autowired
    private MinIOService minIOService;

    @PostMapping("/photo")
    public String uploadPhoto(@RequestParam("photo") MultipartFile file) {
        try {
            String url = minIOService.uploadFile("tonghua-artwork","artwork", file);
            return url;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @PostMapping("/link")
    public String getPhotoLink(@RequestParam("url") String url) {
        String link = minIOService.generateNewSignedUrl("tonghua-artwork", "", url);
        return link;
    }

}
