package poize.busybee_child;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ChatReceivedAdapter extends RecyclerView.Adapter<ChatReceivedAdapter.ChatReceivedViewHolder>{

    ArrayList<String> receivedMessages;

    public ChatReceivedAdapter(ArrayList<String> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    @NonNull
    @Override
    public ChatReceivedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View receivedmessageView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_received_messages,parent,false);
        return new ChatReceivedViewHolder(receivedmessageView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatReceivedViewHolder holder, int position) {
        holder.tv_receivedMessage.setText(receivedMessages.get(position));
    }

    @Override
    public int getItemCount() {
        return receivedMessages.size();
    }

    class ChatReceivedViewHolder extends RecyclerView.ViewHolder{
        TextView tv_receivedMessage;

        public ChatReceivedViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_receivedMessage = itemView.findViewById(R.id.textMessage);
        }
    }
}
