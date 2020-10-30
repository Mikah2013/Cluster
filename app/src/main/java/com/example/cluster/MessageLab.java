package com.example.cluster;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class MessageLab {

    private static MessageLab mMessageLab;
    private Context mContext;
    private ContentResolver mContentResolver;


    public static MessageLab get(Context context) {
        if (mMessageLab == null) {
            mMessageLab = new MessageLab(context);
        }
        return mMessageLab;
    }

    public MessageLab(Context context) {
        mContext = context.getApplicationContext();
        mContentResolver = mContext.getContentResolver();
    }

    public List<Message> getMessages() {
        List<Message> mMessages = new ArrayList<>();
        MessageCursorWrapper smsInboxCursor = querySMSMessages(
                "address = ?",
                new String[] {"AirtelMoney"},
                "_id DESC limit 20"
        );
        try {
            smsInboxCursor.moveToFirst();
            while (!smsInboxCursor.isAfterLast()) {
                mMessages.add(smsInboxCursor.getMessage());
                smsInboxCursor.moveToNext();
            }
        } finally {
                smsInboxCursor.close();
        }
        return mMessages;
    }

    public Message getMessage(String id) {
        MessageCursorWrapper cursor = querySMSMessages(
                "_id = ?",
                new String[]{id.toString()}, null
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getMessage();
        } finally {
            cursor.close();
        }
    }

    private MessageCursorWrapper querySMSMessages(String whereClause, String[] whereArgs, String sortOrder) {
        Cursor cursor = mContentResolver.query(
                Uri.parse("content://sms"),
                null,
                whereClause,
                whereArgs,
                sortOrder
        );
        return new MessageCursorWrapper(cursor);
    }

}
