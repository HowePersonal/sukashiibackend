package com.example.sukashii.repositories;


import com.example.sukashii.model.Anime;
import feign.Param;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnimeRepository extends CrudRepository<Anime, Long> {

    @Query("SELECT * FROM anime WHERE id = :id LIMIT 1")
    Anime findAnimeById(int id);

    @Modifying
    @Query("INSERT INTO anime (id, title, med_picture, large_picture, " +
            "start_date, end_date, description, num_episodes, source) " +
            "VALUES (:#{#anime.id}, :#{#anime.title}, :#{#anime.med_picture}," +
            ":#{#anime.large_picture}, :#{#anime.start_date}, :#{#anime.end_date}," +
            ":#{#anime.description}, :#{#anime.num_episodes}, :#{#anime.source})")
    void insertAnime(Anime anime);

    @Modifying
    @Query("INSERT INTO anime_studio (animeid, studioid) VALUES (:animeid, studioid)")
    void insertAnimeStudio(int animeid, int studioid);

    @Modifying
    @Query("INSERT INTO studio (id, name) VALUES (:id, :name)")
    void insertStudio(int id, String name);

    boolean existsById(int animeid);
}
