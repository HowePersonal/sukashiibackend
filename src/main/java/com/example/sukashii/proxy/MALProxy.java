package com.example.sukashii.proxy;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "mal-client", url = "https://api.myanimelist.net/v2/anime")
public interface MALProxy {

    String API_CLIENT_ID = "X-MAL-CLIENT-ID";

    Map<String, String> animeInfoParam = Map.of(
            "fields", "start_date,end_date,synopsis,num_episodes,source,studios"
    );

    @GetMapping("/ranking")
    Map<String, Object> retrieveRankedAnime(
            @RequestHeader(name = API_CLIENT_ID) String apiClientId,
            @RequestParam("params") Map<String, String> params);

    @GetMapping("/{animeId}")
    Map<String, Object> retrieveAnimeById(
            @RequestHeader(name = API_CLIENT_ID) String apiClientId,
            @RequestParam("params") Map<String, String> params,
            @PathVariable("animeId") int animeId
    );
}
