package com.example.sukashii.service;

import com.example.sukashii.model.Anime;
import com.example.sukashii.proxy.JikanProxy;
import com.example.sukashii.proxy.MALProxy;
import com.example.sukashii.repositories.AnimeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.sql.Date;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class APIService {


    private final MalAPI malAPI;
    private final JikanAPI jikanAPI;
    private final AnimeRepository animeRepository;

    public APIService(MalAPI malAPI, JikanAPI jikanAPI, AnimeRepository animeRepository) {
        this.malAPI = malAPI;
        this.jikanAPI = jikanAPI;
        this.animeRepository = animeRepository;
    }

    @Cacheable(cacheNames = "searchAnime", key="#query")
    public List<Anime> getSearchAnime(String query) {
        Map<String, String> params = new HashMap<>();

        params.put("q", query);
        params.put("limit", "10");
        params.put("sfw", "");

        List<Anime> animeList = jikanAPI.queryJikan(query, params);

        return animeList;
    }

    @Cacheable(cacheNames = "topAnime", key = "#ranking_type")
    public List<Map<String, Object>> getTopAnime(String ranking_type) {
        Map<String, String> params = new HashMap<>();

        params.put("ranking_type", ranking_type);
        params.put("limit", "10");

        List<Map<String, Object>> animeList = malAPI.getAnimeRanking(params, ranking_type);
        return animeList;

    }





}
