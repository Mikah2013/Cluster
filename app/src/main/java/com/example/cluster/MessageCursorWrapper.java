package com.example.cluster;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class MessageCursorWrapper extends CursorWrapper {

    private MessageUtils mUtils;


    public MessageCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Message getMessage() {
        String idString = getString(getColumnIndex("_id"));
        String body = getString(getColumnIndex("body"));
        String address = getString(getColumnIndex("address"));
        long date = getLong(getColumnIndex("date"));

        Message message = new Message(idString);
        message.setAddress(address);
        message.setBody(body.trim());
        Date mmDate = new Date(date);
        SimpleDateFormat sfd = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US);
        String dateFormat = sfd.format(mmDate);
        message.setDate(dateFormat);
        getPaidAmount(message);

        return message;
    }

    private void getPaidAmount(Message body) {
        String[] bodyChunks = body.getBody().split(" ");
        String transaction;
        String balance;
        int amount;
        mUtils = new MessageUtils();

        if (body.getBody().contains("Topup")) {
            transaction = bodyChunks[3].replaceAll(",","").trim();
            body.setAmount(transaction.replaceAll("[.]", "").trim());
            balance = bodyChunks[9].replaceAll(",","").trim();
            body.setBalance("Topped Up - " +balance);
        }
        if (body.getBody().contains("sent")) {
            transaction = bodyChunks[4].replaceAll("[.]", "").trim();
            body.setAmount(transaction.replaceAll(",",""));
            balance = bodyChunks[15].replaceAll(",","").trim();
            body.setBalance("Sent - " +balance);
        }

        if (body.getBody().contains("deposit")) {
            transaction = bodyChunks[4].replaceAll("[.]", "").trim();
            body.setAmount(transaction.replaceAll(",",""));
            balance = bodyChunks[10].replaceAll(",","").trim();
            body.setBalance("Deposited - " +balance.replaceAll("[.]", "").trim());
        }

        if (body.getBody().contains("withdrawal")) {
            transaction = bodyChunks[4].replaceAll("[.]", "").trim();
            body.setAmount(transaction.replaceAll(",",""));
            balance = bodyChunks[15].replaceAll(",","").trim();
            body.setBalance("Withdrawn - " + balance);
        }

        if (body.getBody().contains("received")) {
            transaction = bodyChunks[4].replaceAll("[.]", "").trim();
            body.setAmount(transaction.replaceAll(",",""));

        }

        if (body.getBody().contains("payment")) {
            transaction = bodyChunks[4].replaceAll("[.]", "").trim();
            body.setAmount(transaction.replaceAll(",",""));
            balance = bodyChunks[13].replaceAll(",","").trim();
            if (balance.endsWith(".Thank")) {
                balance = balance.substring(0, balance.length() - 6);
                body.setBalance("Payment - " + balance);
            }

        }

        if (body.getBody().contains("Dear")) {
            transaction = bodyChunks[6].replaceAll("[.]", "").trim();
            body.setAmount(transaction.replaceAll(",",""));
        }

        if (body.getBody().contains("3GB")) {
            transaction = "10000";
            body.setAmount(transaction);
        }

        if (body.getBody().contains("Paid")) {
            transaction = bodyChunks[2].replaceAll("[.]", "").trim();
            body.setAmount(transaction.replaceAll(",",""));
        }
    }

}
