package com.banking.model;

public class Account {

    private Person owner;
    private String number;
    private int balance;


    public int cashOut(int value) {
        if (value > balance) {
            return -1;
        } else {
            balance -= value;
            return balance;
        }
    }

    public int cashIn(int value) {
        balance += value;
        return balance;
    }



    public Account(Person owner, String number, int balance) {
        this.owner = owner;
        this.number = number;
        this.balance = balance;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}