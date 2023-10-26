package com.example.sukashii.repositories;


import com.example.sukashii.model.Anime;
import feign.Param;
import jakarta.transaction.Transactional;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AnimeRepository extends JpaRepository<Anime, Long> {

    @Query("SELECT * FROM anime WHERE id = :id LIMIT 1")
    Anime findAnimeById(int id);

    boolean existsById(int animeid);
}
