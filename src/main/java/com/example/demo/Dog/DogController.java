package com.example.demo.Dog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/dogs")
public class DogController {

    private final DogService dogService;

    @Autowired
    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping
    public List<Dog> getDogs() {
        return dogService.getDogs();
    }

    @GetMapping(path = "{dogId}")
    public Dog getDogById(
            @PathVariable("dogId") Long dogId) {
        return dogService.getDogById(dogId);
    }

    @PostMapping
    public void registerNewDog(@RequestBody Dog dog) {
        dogService.addNewDog(dog);
    }

    @DeleteMapping(path = "{dogId}")
    public void deleteDog(
            @PathVariable("dogId") Long dogId) {
        dogService.deleteDog(dogId);
    }

    @PutMapping(path = "{dogId}")
    public void updateDog(
            @PathVariable("dogId") Long dogId,
            @RequestBody Dog updatedDog) {

        dogService.updateDog(dogId, updatedDog);
    }
}