package presentation;

import java.util.Scanner;

public class ConsoleIO extends IO {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    void print(String message) {
        System.out.println(message);
    }

    @Override
    String readString(String prompt) {
        return scanner.nextLine();
    }

    @Override
    int readInt(String prompt) {
        return Integer.parseInt(scanner.nextLine());
    }
}
