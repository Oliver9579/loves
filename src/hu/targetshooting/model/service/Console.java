package hu.targetshooting.model.service;

import java.util.Scanner;

public class Console {

    private final Scanner scanner;

    public Console(Scanner scanner) {
        this.scanner = scanner;
    }

    public int raedInt(String text) {
        System.out.print(text);
        return scanner.nextInt();
    }
}
