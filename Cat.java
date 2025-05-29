import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Cat extends Animal {
    public Cat(String id, String name, String petName, String breed, LocalDate dateOfBirth, double weight) {
        super(id, name, petName, "Cat", breed, dateOfBirth, weight);
    }

    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return getId() + "|" + getName() + "|" + getPetName() + "|" + getSpecies() + "|" + breed + "|" + dateOfBirth.format(formatter) + "|" + getAge() + "|" + weight;
    }
}
