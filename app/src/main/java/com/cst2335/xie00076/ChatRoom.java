package com.cst2335.xie00076;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cst2335.xie00076.databinding.ActivityChatRoomBinding;
import com.cst2335.xie00076.databinding.ReceiveMessageBinding;
import com.cst2335.xie00076.databinding.SentMessageBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Data.ChatMessage;
import Data.ChatRoomViewModel;

public class ChatRoom extends AppCompatActivity {
    ActivityChatRoomBinding binding;
    ChatRoomViewModel chatModel;
    private RecyclerView.Adapter myAdapter;
    ArrayList<ChatMessage> messages;

    //ChatMessage chat = new ChatMessage("","",false);

    SimpleDateFormat sdf= new SimpleDateFormat("EEEE, dd-mmm-yyyy hh-mm-ss a");
    String currentTime= sdf.format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));

        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        messages= chatModel.messages.getValue();
        if(messages==null){
            chatModel.messages.postValue(messages= new ArrayList<ChatMessage>());
        }

        binding.sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = binding.textInput.getText().toString();
               // boolean sentButton = true;
                ChatMessage chat = new ChatMessage(message, currentTime, true);
                messages.add(chat);
                myAdapter.notifyItemInserted(messages.size() - 1);
                //clear the previous text
                binding.textInput.setText("");
            }
        });

        binding.receiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = binding.textInput.getText().toString();
               // boolean sentButton = false;
               ChatMessage chat = new ChatMessage(message, currentTime, false);
                messages.add(chat);
                myAdapter.notifyItemInserted(messages.size() - 1);
                //clear the previous text
                binding.textInput.setText("");
            }
        });


        binding.recycleView.setAdapter(myAdapter=new RecyclerView.Adapter<MyRowHolder>() {

            @NonNull
            @Override
            //this creates the view holder, it represents a single row in the list
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                if(viewType==0) {
                    SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater());
                    return new MyRowHolder(binding.getRoot());
                }else{
                   ReceiveMessageBinding binding=ReceiveMessageBinding.inflate(getLayoutInflater());
                  return new MyRowHolder(binding.getRoot());
                }
            }

            //this initialize a viewholder to go at the row specified by the position parameter
            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                ChatMessage chatMessage=messages.get(position);
                holder.messageText.setText(chatMessage.getMessage());
                holder.timeText.setText(chatMessage.getTimeSent());
            }

            //this function returns an int specifying how many items to draw
            @Override
            public int getItemCount() {
                return messages.size();
            }

            @Override
            public int getItemViewType(int position) {
                ChatMessage chatMessage=messages.get(position);
                if(chatMessage.getIsSentButton()){
                    return 0;
                }else {
                    return 1;
                }
            }
        });


    }


     class MyRowHolder extends RecyclerView.ViewHolder {
        TextView messageText;
        TextView timeText;

        public MyRowHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.message);
            timeText = itemView.findViewById(R.id.time);
        }
    }
}