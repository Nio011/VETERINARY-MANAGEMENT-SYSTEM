import java.time.LocalDate;
import java.time.Period;


public abstract class Animal {
    String id; // Client ID generated
    String name; //name of client
    String petName;
    String species;
    String breed;
    LocalDate dateOfBirth;
    double weight;

    public Animal(String id, String name, String petName, String species, String breed, LocalDate dateOfBirth, double weight) {
        this.name = name;
        this.petName = petName;
        this.species = species;
        this.breed = breed;
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
    }

    public String getAge() {
        LocalDate now = LocalDate.now();
        Period age = Period.between(dateOfBirth, now);
        return age.getYears() + " year(s), " + age.getMonths() + " month(s), and " + age.getDays() + " day(s)";
    }

    public abstract String toFileString();

}