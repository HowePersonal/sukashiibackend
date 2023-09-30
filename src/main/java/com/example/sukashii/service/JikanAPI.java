package com.example.sukashii.service;

import com.example.sukashii.model.Anime;
import com.example.sukashii.proxy.JikanProxy;
import com.example.sukashii.repositories.AnimeRepository;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JikanAPI {
    private final JikanProxy jikanProxy;
    private final AnimeRepository animeRepository;

    public JikanAPI(JikanProxy jikanProxy, AnimeRepository animeRepository) {
        this.jikanProxy = jikanProxy;
        this.animeRepository = animeRepository;
    }

    public List<Anime> queryJikan(String query, Map<String, String> params) {
        // Gets the response from the Jikan API
        Map<String, Object> response = jikanProxy.searchAnime(params);
        List<Map<String, Object>> animeList = (List<Map<String, Object>>) response.get("data");

        // Transforms the response into a list of Anime objects
        List<Anime> transformedAnimeList = transformJikanList(animeList);

        return transformedAnimeList;
    }

    private List<Anime> transformJikanList(List<Map<String, Object>> animeList) {
        List<Anime> newAnimeList = new ArrayList<Anime>();

        for (Map<String, Object> anime: animeList) {
            // Gets the necessary data from the JSON response
            Map<String, Object> images = (Map<String, Object>) anime.get("images");
            Map<String, Object> imagesJPG = (Map<String, Object>) images.get("jpg");
            Map<String, Object> animeAired = (Map<String, Object>) anime.get("aired");
            Map<String, Object> animeProp = (Map<String, Object>) animeAired.get("prop");

            // Creates a new Anime object with the data from the JSON response
            Anime newAnime = new Anime();
            newAnime.setId((Integer) anime.get("id"));
            newAnime.setTitle((String) anime.get("title"));
            newAnime.setMedPicture((String) imagesJPG.get("image_url"));
            newAnime.setLargePicture((String) imagesJPG.get("large_image_url"));
            newAnime.setStartDate(parseAnimeDate((Map<String, Object>) animeProp.get("from")));
            newAnime.setEndDate(parseAnimeDate((Map<String, Object>) animeProp.get("to")));
            newAnime.setDescription((String) anime.get("synopsis"));
            newAnime.setNumEpisodes((Integer) anime.get("num_episodes"));
            newAnime.setSource((String) anime.get("source"));

            // Verifies if the anime exists in the database, if not, it inserts it
            verifyAnimeExistence(newAnime, newAnime.getId());
            newAnimeList.add(newAnime);
        }

        return newAnimeList;
    }

    private void verifyAnimeExistence(Anime animeObject, long id) {
        if (!animeRepository.existsById(id)) {
            animeRepository.insertAnime(animeObject);
        }
    }

    private Date parseAnimeDate(Map<String, Object> dateData) {
        // Parses the date from the JSON response into a Date object if valid, else return null
        try {
            int year = (int) dateData.get("year");
            int month = (int) dateData.get("month");
            int day = (int) dateData.get("day");
            return Date.valueOf(year + "-" + month + "-" + day);
        } catch (Exception e) {
            return null;
        }
    }
}
