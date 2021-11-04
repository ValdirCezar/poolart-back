package com.valdir.poolart.repositories;

import com.valdir.poolart.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    Optional<Artist> findByCpf(String cpf);
    Optional<Artist> findByEmail(String email);
}
