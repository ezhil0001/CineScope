package com.sabari.Movie_Xplore.Repo;


import com.sabari.Movie_Xplore.Entity.Cast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CastRepo extends JpaRepository<Cast, Integer> {
}

