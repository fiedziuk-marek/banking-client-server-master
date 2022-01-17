package com.banking;

import com.banking.model.Account;
import com.banking.model.Person;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    private static Menu menu;
    private static Account account;
    private static Socket socket;

    public static void main(String[] args) {
        menu = new Menu();
//        account = menu.chooseAccount();

        Person person = new Person("Jan", "Kowalski", "11111111111");
        account = new Account(person, "11 1111 1111 1111 1111 1111 1111", 1000);

        try {
            socket = new Socket("localhost", 5000);

            System.out.println("utworzono socket");

            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            //pauza
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();

            //wpłata
            output.println(account.getNumber());
            output.println(1);
            output.println(5000);
            System.out.println("wpłata wykonana");

            //pauza
            scanner.nextLine();

            //wypłata
            output.println(account.getNumber());
            output.println(2);
            output.println(2000);
            System.out.println("wypłata wykonana");

            //pauza
            scanner.nextLine();

            //wypłata z błędem
            output.println(account.getNumber());
            output.println(2);
            output.println(22000);
            System.out.println("wypłata z błędem wykonana");

            //pauza
            scanner.nextLine();

            //transfer
            output.println(account.getNumber());
            output.println(3);
            output.println(3500);
            output.println("11 1111 1111 1111 1111 1111 1112");
            System.out.println("transfer wykonany");


            //pauza
            scanner.nextLine();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
