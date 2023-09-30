package com.example.sukashii.controller;


import com.example.sukashii.model.Anime;
import com.example.sukashii.repositories.AnimeRepository;
import com.example.sukashii.service.APIService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/anime")
public class AnimeController {
    private final APIService APIService;
    private final AnimeRepository animeRepository;

    public AnimeController(APIService APIService, AnimeRepository animeRepository) {
        this.APIService = APIService;
        this.animeRepository = animeRepository;
    }

    @GetMapping("/ranked")
    public List<Map<String, Object>> getTrendingAnime(
            @RequestParam String ranking_type) {
        return APIService.getTopAnime(ranking_type);
    }

    @GetMapping("/search")
    public List<Anime> getSearchAnime(
            @RequestParam String query) {
        return APIService.getSearchAnime(query);
    }

    @GetMapping("/info")
    public Anime getAnimeInfo(
            @RequestParam int id) {
        return animeRepository.findAnimeById(id);

    }

}
