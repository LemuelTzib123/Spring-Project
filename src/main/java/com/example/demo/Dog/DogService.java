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
    public void updateDog(Long dogId,
                          String name,
                          String breed,
                          String ownerPhone) {

        Dog dog = dogRepository.findById(dogId)
                .orElseThrow(() ->
                        new IllegalStateException("Dog with id " + dogId + " does not exist"));

        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(dog.getName(), name)) {
            dog.setName(name);
        }

        if (breed != null &&
                breed.length() > 0 &&
                !Objects.equals(dog.getBreed(), breed)) {
            dog.setBreed(breed);
        }

        if (ownerPhone != null &&
                ownerPhone.length() > 0 &&
                !Objects.equals(dog.getOwnerPhone(), ownerPhone)) {

            Optional<Dog> dogOptional = dogRepository
                    .findDogByOwnerPhone(ownerPhone);

            if (dogOptional.isPresent()) {
                throw new IllegalStateException("Owner phone number already registered");
            }

            dog.setOwnerPhone(ownerPhone);
        }
    }
}