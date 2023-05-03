package com.practice.shoutreview.repositories;

import com.practice.shoutreview.entities.Movie;
import com.practice.shoutreview.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {
    boolean existsByTitle(String title);
    public Movie findByTitle(String title);

    public List<Movie> findByGenre(Genre genre);
}
