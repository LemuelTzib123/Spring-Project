package com.example.demo.Dog;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Dog {

    @Id
    @SequenceGenerator(
            name = "dog_sequence",
            sequenceName = "dog_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "dog_sequence"
    )
    private Long id;

    private String name;
    private String breed;
    private String ownerPhone;
    private LocalDate birthDate;

    @Transient
    private Integer age;

    public Dog() {
    }

    public Dog(Long id,
               String name,
               String breed,
               String ownerPhone,
               LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.ownerPhone = ownerPhone;
        this.birthDate = birthDate;
    }

    public Dog(String name,
               String breed,
               LocalDate birthDate,
               String ownerPhone) {
        this.name = name;
        this.breed = breed;
        this.birthDate = birthDate;
        this.ownerPhone = ownerPhone;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", ownerPhone='" + ownerPhone + '\'' +
                ", birthDate=" + birthDate +
                ", age=" + getAge() +
                '}';
    }
}



