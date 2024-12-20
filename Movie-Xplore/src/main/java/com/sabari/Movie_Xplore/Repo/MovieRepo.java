package com.sabari.Movie_Xplore.Repo;

import com.sabari.Movie_Xplore.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Integer> {
}
