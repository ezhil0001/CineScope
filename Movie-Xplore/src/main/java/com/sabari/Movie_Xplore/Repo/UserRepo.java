package com.sabari.Movie_Xplore.Repo;

import com.sabari.Movie_Xplore.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {

    boolean existsByEmail(String email);

    Optional<Users> findByEmail(String email);
}
