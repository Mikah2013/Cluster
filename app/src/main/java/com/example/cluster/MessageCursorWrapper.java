package com.example.cluster;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.util.Log;

import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MessageCursorWrapper extends CursorWrapper {

    private MessageUtils mUtils;
    private SettingsActivity mActivity;
    private PieEntry pieEntry;
    private PieDataSet mPieDataSet;
    private List<Integer> mList;
    private List<Integer> paidList;
    private List<Integer> sentList;
    private List<Integer> withdrawalList;
    private int sum = 0;


    public MessageCursorWrapper(Cursor cursor) {
        super(cursor);
        mList = new ArrayList<>();
        paidList = new ArrayList<>();
        sentList = new ArrayList<>();
        withdrawalList = new ArrayList<>();

    }

    public Message getSingleMessage() {
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

        getAccountBalance(message);

        return message;
    }

    public Message getMessages() {
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

        Log.d("Summation", String.valueOf(sum));

        return message;
    }

    private void getPaidAmount(Message body) {
        String[] bodyChunks = body.getBody().split(" ");
        String transaction;
        String balance;
        String amount;
        String amountSent;
        mUtils = new MessageUtils();
        mActivity = new SettingsActivity();


        if (body.getBody().contains("sent")) {
            transaction = bodyChunks[4].replaceAll("[.]", "").trim();
            amountSent = transaction.replaceAll(",","");
            body.setAmount("You sent - UGX " + amountSent);

            balance = bodyChunks[16].replaceAll("[.]","").trim();
            body.setBalance("Balance - UGX " + balance.replaceAll(",",""));

            int newInt = Integer.parseInt(amountSent);
            sentList.add(newInt);
            for (int i : mList) {
                sum = sum + i;
            }
            body.setSentList(sentList);

        }

        if (body.getBody().contains("withdrawal") && !body.getBody().contains("initiated")) {
            transaction = bodyChunks[4].replaceAll("[.]", "").trim();
            amount = transaction.replaceAll(",","");
            body.setAmount("Withdrawal - UGX " + amount);
            balance = bodyChunks[14].replaceAll(",","").trim();
            body.setBalance("Balance - UGX " + balance.replaceAll("[.]", "").trim());

            int newInt = Integer.parseInt(amount);
            withdrawalList.add(newInt);
            for (int i : mList) {
                sum = sum + i;
            }
            body.setWithdrawalList(withdrawalList);

        }

        if (body.getBody().contains("Paid") && !body.getBody().contains("UMEME")) {
            transaction = bodyChunks[2].replaceAll("[.]", "").trim();
            amount = transaction.replaceAll(",","");
            body.setAmount("Paid - UGX " + amount);
            balance = bodyChunks[13].replaceAll(",","").trim();
            body.setBalance("Balance - UGX " + balance.replaceAll("[.]", "").trim());

            int newInt = Integer.parseInt(amount);
            paidList.add(newInt);
            for (int i : mList) {
                sum = sum + i;
            }
            body.setPaidList(paidList);

        }
    }

    private void getAccountBalance(Message body) {

        String[] bodyChunks = body.getBody().split(" ");
        String transaction;
        String balance;
        String amount;

        if (body.getBody().contains("Topup") && body.getBody().contains("Balance")) {
            balance = bodyChunks[9].replaceAll(",","").trim();
            body.setBalance("Your Account Balance - UGX " + balance);
        }
        if (body.getBody().contains("sent")) {
            balance = bodyChunks[16].replaceAll("[.]","").trim();
            body.setBalance("Your Account Balance - UGX " + balance.replaceAll(",",""));
        }

        if (body.getBody().contains("deposit")) {
            balance = bodyChunks[10].replaceAll(",","").trim();
            body.setBalance("Your Account Balance - UGX " + balance.replaceAll("[.]", "").trim());
        }

        if (body.getBody().contains("withdrawal") && !body.getBody().contains("initiated")) {
            balance = bodyChunks[14].replaceAll(",","").trim();
            body.setBalance("Your Account Balance - UGX " + balance.replaceAll("[.]", "").trim());
        }

        if (body.getBody().contains("received")) {
            balance = bodyChunks[13].replaceAll(",","").trim();
            body.setBalance("Your Account Balance - UGX " + balance.replaceAll("[.]", "").trim());
        }

        if (body.getBody().contains("payment")) {
            balance = bodyChunks[13].replaceAll(",","").trim();
            if (balance.endsWith(".Thank")) {
                balance = balance.substring(0, balance.length() - 6);
                body.setBalance("Your Account Balance - UGX " + balance);
            }
        }

        if (body.getBody().contains("Paid")) {
            balance = bodyChunks[8].replaceAll(",","").trim();
            body.setBalance("Your Account Balance - UGX " + balance.replaceAll("[.]", "").trim());
        }

    }

}
