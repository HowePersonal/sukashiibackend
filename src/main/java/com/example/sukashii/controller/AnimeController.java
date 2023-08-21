package com.example.sukashii.controller;


import com.example.sukashii.model.Anime;
import com.example.sukashii.repositories.AnimeRepository;
import com.example.sukashii.service.MALService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/anime")
public class AnimeController {
    private final MALService malService;
    private final AnimeRepository animeRepository;

    public AnimeController(MALService malService, AnimeRepository animeRepository) {
        this.malService = malService;
        this.animeRepository = animeRepository;
    }

    @GetMapping("/ranked")
    public List<Map<String, Object>> getTrendingAnime(
            @RequestParam String ranking_type) {
        return malService.getTopAnime(ranking_type);
    }

    @GetMapping("/search")
    public List<Map<String, Object>> getSearchAnime(
            @RequestParam String query) {
        return malService.getSearchAnime(query);
    }


    @GetMapping("/info")
    public Anime getAnimeInfo(
            @RequestParam int id) {
        return animeRepository.findAnimeById(id);

    }

}
