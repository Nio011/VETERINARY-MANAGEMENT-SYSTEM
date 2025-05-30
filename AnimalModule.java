import java.util.Scanner;

public class AnimalModule {

    public static void showAnimalMenu(Scanner sc) {
        AnimalManager am = new AnimalManager(sc);
        am.showAnimalMenu();
    }
}
