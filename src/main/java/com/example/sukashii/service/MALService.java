package com.example.sukashii.service;

import com.example.sukashii.model.Anime;
import com.example.sukashii.proxy.JikanProxy;
import com.example.sukashii.proxy.MALProxy;
import com.example.sukashii.repositories.AnimeRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MALService {
    private final String API_CLIENT_ID = "7d38b7060a59647709e287a23a48a2a3";

    private final MALProxy malProxy;
    private final JikanProxy jikanProxy;
    private final AnimeRepository animeRepository;

    public MALService(MALProxy malProxy, JikanProxy jikanProxy, AnimeRepository animeRepository) {
        this.malProxy = malProxy;
        this.jikanProxy = jikanProxy;
        this.animeRepository = animeRepository;
    }

    @Cacheable(cacheNames = "searchAnime", key="#query")
    public List<Map<String, Object>> getSearchAnime(String query) {
        Map<String, String> params = new HashMap<>();

        params.put("q", query);
        params.put("limit", "10");
        params.put("sfw", "");

        Map<String, Object> response = jikanProxy.searchAnime(params);
        List<Map<String, Object>> animeList = (List<Map<String, Object>>) response.get("data");

        verifyJikanAnimeList(animeList);

        return animeList;
    }

    @Cacheable(cacheNames = "topAnime", key = "#ranking_type")
    public List<Map<String, Object>> getTopAnime(String ranking_type) {
        Map<String, String> params = new HashMap<>();

        params.put("ranking_type", ranking_type);
        params.put("limit", "10");

        Map<String, Object> response = malProxy.retrieveRankedAnime(API_CLIENT_ID, params);
        List<Map<String, Object>> animeList = (List<Map<String, Object>>) response.get("data");

        verifyMALAnimeList(animeList);

        return animeList;

    }

    public void verifyMALAnimeList(List<Map<String, Object>> animeList) {
        for (Map<String, Object> anime: animeList) {
            Map<String, Object> animeNode = (Map<String, Object>) anime.get("node");
            int animeId = (int) animeNode.get("id");

            Anime animeObject = animeRepository.findAnimeById(animeId);

            if (animeObject == null) {
                String animeTitle = (String) animeNode.get("title");
                Map<String, Object> response = malProxy.retrieveAnimeById(API_CLIENT_ID, malProxy.animeInfoParam, animeId);

                Map<String, Object> main_picture = (Map<String, Object>) response.get("main_picture");
                String med_picture = (String) main_picture.get("medium");
                String large_picture = (String) main_picture.get("large");
                String start_date = (String) response.get("start_date");
                String end_date = (String) response.get("end_date");
                String description = (String) response.get("synopsis");
                int num_episodes = (int) response.get("num_episodes");
                String source = (String) response.get("source");

                Anime newAnime = new Anime();
                newAnime.setTitle(animeTitle);
                newAnime.setMed_picture(med_picture);
                newAnime.setLarge_picture(large_picture);
                newAnime.setStart_date(start_date);
                newAnime.setEnd_date(end_date);
                newAnime.setDescription(description);
                newAnime.setNum_episodes(num_episodes);
                newAnime.setSource(source);

                animeRepository.insertAnime(newAnime);
            }


        }
    }

    public List<Anime> transformJikanList(List<Map<String, Object>> animeList) {
        List<Anime> newAnimeList = new ArrayList<Anime>();

        for (Map<String, Object> anime: animeList) {
            String animeTitle = (String) anime.get("title");

            Map<String, Object> images = (Map<String, Object>) anime.get("images");
            Map<String, Object> imagesJPG = (Map<String, Object>) images.get("jpg");

            String med_picture = (String) imagesJPG.get("image_url");
            String large_picture = (String) imagesJPG.get("large_image_url");

            Map<String, Object> animeAired = (Map<String, Object>) anime.get("aired");
            Map<String, Object> animeProp = (Map<String, Object>) animeAired.get("prop");
            Map<String, Object> animeFromDate = (Map<String, Object>) animeProp.get("from");
            Map<String, Object> animeEndDate = (Map<String, Object>) animeProp.get("to");

            String start_date = String.valueOf(animeFromDate.get("year")) + "-" +
                    String.valueOf(animeFromDate.get("month")) + "-" + String.valueOf(animeFromDate.get("day"));

            String end_date = String.valueOf(animeEndDate.get("year")) + "-" +
                    String.valueOf(animeEndDate.get("month")) + "-" + String.valueOf(animeEndDate.get("day"));

            String description = (String) anime.get("synopsis");
            int num_episodes = (int) anime.get("episodes");
            String source = (String) anime.get("source");

            Anime newAnime = new Anime();
            newAnime.setTitle(animeTitle);
            newAnime.setMed_picture(med_picture);
            newAnime.setLarge_picture(large_picture);
            newAnime.setStart_date(start_date);
            newAnime.setEnd_date(end_date);
            newAnime.setDescription(description);
            newAnime.setNum_episodes(num_episodes);
            newAnime.setSource(source);

            newAnimeList.add(newAnime);
        }



        return newAnimeList;
    }

    public void verifyJikanAnimeList(List<Map<String, Object>> animeList) {
        List<Anime> newAnimeList = transformJikanList(animeList);
        for (Anime anime: newAnimeList) {
            int animeId = (int) anime.getId();

            Anime animeObject = animeRepository.findAnimeById(animeId);

            if (animeObject == null) {

                animeRepository.insertAnime(anime);
            }


        }
    }

}
