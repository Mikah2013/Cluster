package com.example.cluster;

public class Message {
    String mId;
    String address;
    String amount;
    String body;
    String date;
    String balance;

    public Message() {
    }


    public Message(String id) {
        this.mId = id;
    }

    public Message(String id, String address, String amount, String body, String date, String balance) {
        this.mId = id;
        this.address = address;
        this.body = body;
        this.date = date;
        this.balance = balance;
    }

    public String getId() {
        return mId;
    }

    public String getAddress() {
        return address;
    }

    public String getAmount() {
        return amount;
    }

    public String getBody() {
        return body;
    }

    public String getDate() {
        return date;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
