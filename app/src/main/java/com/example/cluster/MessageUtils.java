package com.example.cluster;


import java.util.ArrayList;
import java.util.List;

public class MessageUtils {

    public MessageUtils() {
    }

    public int sum(int amount) {
        int sum = 0;
        List<Integer> mList = new ArrayList<Integer>();
        mList.add(amount);
        for (int i : mList)
            sum = sum + i;
        return sum;
    }
}
