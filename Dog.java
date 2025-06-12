// Child Class Dog extending Animal

import java.time.LocalDate;

public class Dog extends Animal {
    public Dog(String id, String clientName, String petName, String breed, LocalDate dateOfBirth, double weight) {
        super(id, clientName, petName, "Dog", breed, dateOfBirth, weight);
    }

    @Override
    public String toFileString() {
        return String.format("Dog | ID: %s | Client: %s | Pet: %s | Breed: %s | DOB: %s | Weight: %.2f kg | Age: %s",
                getId(),
                getName(),
                getPetName(),
                getBreed(),
                getDateOfBirth().toString(),
                getWeight(),
                getAge());
    }
}
