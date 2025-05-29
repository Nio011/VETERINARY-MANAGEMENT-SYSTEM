import java.time.LocalDate;
import java.time.Period;

public abstract class Animal {
    private String id; // Client ID generated
    private String name; //name of client
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

    public string getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPetName() {
        return petName;
    }

    public String getSpecies() {
        return species;
    }

    public String getBreed() {
        return breed;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public double getWeight() {
        return weight;
    }

    public String getAge() {
        LocalDate now = LocalDate.now();
        Period age = Period.between(dateOfBirth, now);
        return age.getYears() + " year(s), " + age.getMonths() + " month(s), and " + age.getDays() + " day(s)";
    }

    public abstract String toFileString();

 @Override
public String toString() {
    return "Client ID: " + id +
           " | Client Name: " + name +
           " | Pet Name: " + petName +
           " | Species: " + species;
}

}