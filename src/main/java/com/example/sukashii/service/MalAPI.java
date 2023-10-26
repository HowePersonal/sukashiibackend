package com.example.sukashii.service;

import com.example.sukashii.model.Anime;
import com.example.sukashii.proxy.MALProxy;

import com.example.sukashii.repositories.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@Component
public class MalAPI {

    @Value("${mal.api.client_id}")
    private String API_CLIENT_ID;

    @Autowired
    private MALProxy malProxy;

    @Autowired
    private AnimeRepository animeRepository;


    public List<Map<String, Object>> getAnimeRanking(Map<String, String> params, String ranking_type) {
        Map<String, Object> response = malProxy.retrieveRankedAnime(API_CLIENT_ID, params);
        List<Map<String, Object>> animeList = (List<Map<String, Object>>) response.get("data");

        return animeList;
    }
}
