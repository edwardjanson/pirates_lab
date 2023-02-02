package com.codeclan.example.pirateservice_d1_starter.repositories;

import com.codeclan.example.pirateservice_d1_starter.models.Pirate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PirateRepository extends JpaRepository<Pirate, Long> {

    List<Pirate> findPiratesByAgeGreaterThan(int age);

    List<Pirate> findPiratesByFirstName(String firstName);

    Pirate findPirateByLastName(String lastName);

    List<Pirate> findPiratesByRaidsId(Long raidId);
}
