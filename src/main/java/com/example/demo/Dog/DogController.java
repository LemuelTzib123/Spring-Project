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

    // GET all dogs enrolled in daycare
    @GetMapping
    public List<Dog> getDogs() {
        return dogService.getDogs();
    }

    // POST register a new dog into daycare
    @PostMapping
    public void registerNewDog(@RequestBody Dog dog) {
        dogService.addNewDog(dog);
    }

    // DELETE remove a dog from daycare
    @DeleteMapping(path = "{dogId}")
    public void deleteDog(
            @PathVariable("dogId") Long dogId) {
        dogService.deleteDog(dogId);
    }

    // PUT update dog information
    @PutMapping(path = "{dogId}")
    public void updateDog(
            @PathVariable("dogId") Long dogId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String breed,
            @RequestParam(required = false) String ownerName) {
        dogService.updateDog(dogId, name, breed, ownerName);
    }
}