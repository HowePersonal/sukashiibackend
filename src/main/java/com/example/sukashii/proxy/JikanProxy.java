package com.example.sukashii.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "jikan-client", url = "https://api.jikan.moe/v4")
public interface JikanProxy {

    @GetMapping("/anime")
    Map<String, Object> searchAnime(
            @RequestParam("params") Map<String, String> params
    );
}
