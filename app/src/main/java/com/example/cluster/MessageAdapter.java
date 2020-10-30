package com.example.cluster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private List<Message> mMessages;


    public MessageAdapter(List<Message> messages) {
        mMessages = messages;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.message_layout, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        final CardView cardView = holder.mCardView;
        Message message = mMessages.get(position);
        holder.bind(message);

    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView mCardView;
        private TextView mTextViewAddress;
        private TextView mTextViewAmount;
        private TextView mTextViewBalance;
        private TextView mTextViewDate;
        private Message mMessage;


        public ViewHolder(@NonNull View cardView) {
            super(cardView);
            mCardView = cardView.findViewById(R.id.card_view);
            mTextViewAddress = cardView.findViewById(R.id.textViewAddress);
            mTextViewAmount = cardView.findViewById(R.id.textViewAmount);
            mTextViewBalance = cardView.findViewById(R.id.textViewBalance);
            mTextViewDate = cardView.findViewById(R.id.textViewDate);
        }

        public void bind(Message message) {
            mMessage = message;
            mTextViewAddress.setText(mMessage.getAddress());
            mTextViewAmount.setText(mMessage.getAmount());
            mTextViewDate.setText(mMessage.getDate());
            mTextViewBalance.setText(mMessage.getBalance());

        }
    }
}

