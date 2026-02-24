package com.example.demo.Dog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DogRepository
        extends JpaRepository<Dog, Long> {

    @Query("SELECT d FROM Dog d WHERE d.ownerPhone = ?1")
    Optional<Dog> findDogByOwnerPhone(String ownerPhone);
}