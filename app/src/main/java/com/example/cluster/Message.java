package com.example.cluster;

import java.util.List;

public class Message {
    String mId;
    String address;
    String amount;
    String body;
    String date;
    String balance;
    List<Integer> paidList;
    List<Integer> sentList;
    List<Integer> withdrawalList;

    public Message() {
    }

    public Message(String id) {
        this.mId = id;
    }

    public Message(String mId, String address, String amount, String body, String date, String balance, List<Integer> paidList, List<Integer> sentList, List<Integer> withdrawalList) {
        this.mId = mId;
        this.address = address;
        this.amount = amount;
        this.body = body;
        this.date = date;
        this.balance = balance;
        this.paidList = paidList;
        this.sentList = sentList;
        this.withdrawalList = withdrawalList;
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

    public List<Integer> getPaidList() {
        return paidList;
    }

    public void setPaidList(List<Integer> paidList) {
        this.paidList = paidList;
    }

    public List<Integer> getSentList() {
        return sentList;
    }

    public void setSentList(List<Integer> sentList) {
        this.sentList = sentList;
    }

    public List<Integer> getWithdrawalList() {
        return withdrawalList;
    }

    public void setWithdrawalList(List<Integer> withdrawalList) {
        this.withdrawalList = withdrawalList;
    }
}
