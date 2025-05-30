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

    // Setters
    public void setName(String name) { this.name = name; }
    public void setPetName(String petName) { this.petName = petName; }
    public void setBreed(String breed) { this.breed = breed; }
    public void setDateOfBirth(LocalDate dob) { this.dateOfBirth = dob; }
    public void setWeight(double weight) { this.weight = weight; }

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
