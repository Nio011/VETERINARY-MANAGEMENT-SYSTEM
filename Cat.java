//Child class Cat extending Animal

import java.time.LocalDate;

public class Cat extends Animal {
    public Cat(String id, String clientName, String petName, String breed, LocalDate dateOfBirth, double weight) {
        super(id, clientName, petName, "Cat", breed, dateOfBirth, weight);
    }

    @Override
    public String toFileString() {
        return String.format("Cat | ID: %s | Client: %s | Pet: %s | Breed: %s | DOB: %s | Weight: %.2f kg | Age: %s",
                getId(),
                getName(),
                getPetName(),
                getBreed(),
                getDateOfBirth().toString(),
                getWeight(),
                getAge());
    }
}
