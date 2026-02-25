package com.example.demo.Dog;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DogService {

    private final DogRepository dogRepository;

    @Autowired
    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public List<Dog> getDogs() {
        return dogRepository.findAll();
    }

    public void addNewDog(Dog dog) {
        Optional<Dog> dogOptional = dogRepository
                .findDogByOwnerPhone(dog.getOwnerPhone());

        if (dogOptional.isPresent()) {
            throw new IllegalStateException("Owner phone number already registered");
        }

        dogRepository.save(dog);
    }

    public void deleteDog(Long dogId) {
        boolean exists = dogRepository.existsById(dogId);

        if (!exists) {
            throw new IllegalStateException(
                    "Dog with id " + dogId + " does not exist");
        }

        dogRepository.deleteById(dogId);
    }

    @Transactional
    public void updateDog(Long dogId, Dog updatedDog) {

        Dog dog = dogRepository.findById(dogId)
                .orElseThrow(() ->
                        new IllegalStateException("Dog with id " + dogId + " does not exist"));

        if (updatedDog.getName() != null) {
            dog.setName(updatedDog.getName());
        }

        if (updatedDog.getBreed() != null) {
            dog.setBreed(updatedDog.getBreed());
        }

        if (updatedDog.getOwnerPhone() != null) {

            Optional<Dog> dogOptional =
                    dogRepository.findDogByOwnerPhone(updatedDog.getOwnerPhone());

            if (dogOptional.isPresent() &&
                    !dogOptional.get().getId().equals(dogId)) {
                throw new IllegalStateException("Owner phone already registered");
            }

            dog.setOwnerPhone(updatedDog.getOwnerPhone());
        }

        if (updatedDog.getBirthDate() != null) {
            dog.setBirthDate(updatedDog.getBirthDate());
        }
    }
}