//Parent class for all animals in the system

import java.time.LocalDate;
import java.time.Period;

public abstract class Animal {
    private String id;
    private String name;
    private String petName;
    private String species;
    private String breed;
    private LocalDate dateOfBirth;
    private double weight;

    public Animal(String id, String name, String petName, String species, String breed, LocalDate dateOfBirth, double weight) {
        this.id = id;
        this.name = name;
        this.petName = petName;
        this.species = species;
        this.breed = breed;
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getPetName() { return petName; }
    public String getSpecies() { return species; }
    public String getBreed() { return breed; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public double getWeight() { return weight; }
    public String getClientId() { return id.substring(0, 3); }
    public String getOwnerId() { return id.substring(3, 6); }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setPetName(String petName) { this.petName = petName; }
    public void setBreed(String breed) { this.breed = breed; }
    public void setDateOfBirth(LocalDate dob) { this.dateOfBirth = dob; }
    public void setWeight(double weight) { this.weight = weight; }
    public String setSpecies(String species) {
        this.species = species;
        return species;
    }

    // Method for calcuating age using the date of birth
    // Returns the age in years and months

    public String getAge() {
        LocalDate now = LocalDate.now();
        Period p = Period.between(dateOfBirth, now);
        return p.getYears() + " years, " + p.getMonths() + " months";
    }

    public abstract String toFileString();

    @Override
    public String toString() {
        return toFileString();
    }
}
