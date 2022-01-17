package com.banking.model;

import java.util.ArrayList;

public class ValidAccounts {

    private ArrayList<Account> accounts;

    public ValidAccounts() {
        this.accounts = new ArrayList<>();
        fill();
    }


    //public methods

    public int cashOut(String accNum, int amount) {
        Account account = getAccount(accNum);
        return account.cashOut(amount);
    }

    public int cashIn(String accNum, int amount) {
        Account account = getAccount(accNum);
        return account.cashIn(amount);
    }

    public int transfer(String from, String to, int amount) {
        Account sender = getAccount(from);
        Account receiver = getAccount(to);

        if (sender.cashOut(amount) != -1) {
            return receiver.cashIn(amount);
        }
        return -1;
    }

    public int getBalance(String accNumber) {
        return getAccount(accNumber).getBalance();
    }


    //private methods

    private Account getAccount(String accNumber) {
        for (Account account : accounts) {
            if (account.getNumber().equals(accNumber));
            return account;
        }
        return null;
    }

    private void fill() {
        Person person = new Person("Jan", "Kowalski", "11111111111");
        Account account = new Account(person, "11 1111 1111 1111 1111 1111 1111", 1000);
        accounts.add(account);

        person = new Person("Maria", "Bednarz", "11111111112");
        account = new Account(person, "11 1111 1111 1111 1111 1111 1112", 2000);
        accounts.add(account);

        person = new Person("Maciej", "Wielki", "11111111113");
        account = new Account(person, "11 1111 1111 1111 1111 1111 1113", 3000);
        accounts.add(account);
    }

}
