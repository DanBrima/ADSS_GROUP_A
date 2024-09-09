package presentation;

import java.util.Scanner;

public class ConsoleIO extends IO {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    @Override
    public int readInt(String prompt) {
        return Integer.parseInt(readString(prompt));
    }

    @Override
    public boolean readBoolean(String prompt) {
        return Boolean.parseBoolean(readString(prompt));
    }
}
