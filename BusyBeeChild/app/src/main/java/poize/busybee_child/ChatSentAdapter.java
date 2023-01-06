package poize.busybee_child;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatSentAdapter extends RecyclerView.Adapter<ChatSentAdapter.ChatSentViewHolder>{

    ArrayList<String> sentMessages;
    @NonNull
    @Override
    public ChatSentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View chatsentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_sent_messages,parent,false);
        return new ChatSentViewHolder(chatsentView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatSentViewHolder holder, int position) {
        holder.tv_sentMessage.setText(sentMessages.get(position));
    }

    @Override
    public int getItemCount() {
        return sentMessages.size();
    }

    class ChatSentViewHolder extends RecyclerView.ViewHolder{
        TextView tv_sentMessage;

        public ChatSentViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_sentMessage = itemView.findViewById(R.id.textMessage);
        }
    }
}