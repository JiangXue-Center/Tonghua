package com.hf.painting.controller;

import com.hf.core.model.Result;
import com.hf.painting.model.dto.PaintPrompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import static com.hf.painting.constants.StringConstants.PAINTER_URL;

@RestController
@RequestMapping("painting")
public class PaintController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public Result<String> generatePaint(@RequestBody PaintPrompt prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PaintPrompt> request = new HttpEntity<>(prompt, headers);

        String imgUrl = restTemplate.postForObject(PAINTER_URL, request, String.class);
        return Result.success(imgUrl);
    }

}
