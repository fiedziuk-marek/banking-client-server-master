package com.banking;

import com.banking.model.Account;
import com.banking.model.Person;

import java.util.Scanner;

public class Menu {

    private static Scanner scanner = new Scanner(System.in);

    public static Account chooseAccount() {
        System.out.println("Wybierz konto (1, 2, 3");
        switch (scanner.nextLine()) {
            case "1":
                Person person = new Person("Jan", "Kowalski", "11111111111");
                return new Account(person, "11 1111 1111 1111 1111 1111 1111", 1000);

            case "2":
                person = new Person("Maria", "Bednarz", "11111111112");
                return new Account(person, "11 1111 1111 1111 1111 1111 1112", 2000);

            case "3":
                person = new Person("Maciej", "Wielki", "11111111113");
                return new Account(person, "11 1111 1111 1111 1111 1111 1113", 3000);
        }

        return null;
    }
}
