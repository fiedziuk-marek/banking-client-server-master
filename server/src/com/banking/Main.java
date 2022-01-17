package com.banking;


import com.banking.model.ValidAccounts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    private static ValidAccounts validAccounts;
    private static ServerSocket serverSocket;

    public static void main(String[] args) {
        try {
            validAccounts = new ValidAccounts();
            serverSocket = new ServerSocket(5000);

            while (true) {
                System.out.println("Waiting for connection...");

                Socket socket = serverSocket.accept();

                //odbierz numer konta
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String clientAccNumber = input.readLine();
                System.out.println("Connected to " + clientAccNumber);

                /*
                odbierz rodzaj(numer) operacji:
                1 - wpłata
                2 - wypłata
                3 - transfer na inne konto
                 */
                int operationType = Integer.parseInt(input.readLine());

                //odbierz kwotę
                int amount = Integer.parseInt(input.readLine());

                //wykonaj daną operację
                if (operationType == 1) {
                    validAccounts.cashIn(clientAccNumber, amount);
                    System.out.println("Cashed in " + amount);
                    outputSuccess(clientAccNumber, socket);

                } else if (operationType == 2) {
                    if (validAccounts.cashOut(clientAccNumber, amount) != -1) {
                        System.out.println("Cashed out " + amount);
                        outputSuccess(clientAccNumber, socket);
                    } else {
                        outputFailed(clientAccNumber, socket);
                    }
                } else if (operationType == 3) {
                    String receivingAccNumber = input.readLine();
                    if (validAccounts.transfer(clientAccNumber, receivingAccNumber, amount) != -1) {
                        System.out.println("Transferred " + amount + " PLN from " + clientAccNumber + " to " + receivingAccNumber);
                        outputSuccess(clientAccNumber, socket);
                    } else {
                        outputFailed(clientAccNumber, socket);
                    }
                } else {
                    System.out.println("Oops! Wrong operation type code.");
                }
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void outputSuccess(String clientAccNumber, Socket socket) throws Exception {
        BufferedWriter output = new BufferedWriter(new PrintWriter(socket.getOutputStream(), true));
        output.write("Success! Current balance is " + validAccounts.getBalance(clientAccNumber) + " PLN");
    }

    private static void outputFailed(String clientAccNumber, Socket socket) throws Exception {
        BufferedWriter output = new BufferedWriter(new PrintWriter(socket.getOutputStream(), true));
        System.out.println("Balance too low for " + clientAccNumber);
        output.write("Balance too low!");
    }
}