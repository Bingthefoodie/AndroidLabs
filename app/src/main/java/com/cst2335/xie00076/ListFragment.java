package com.cst2335.xie00076;

import android.app.Notification;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.ConversationActions;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cst2335.xie00076.databinding.ActivityChatRoomBinding;
import com.cst2335.xie00076.databinding.FragmentListBinding;
import com.cst2335.xie00076.databinding.ReceiveMessageBinding;
import com.cst2335.xie00076.databinding.SentMessageBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import Data.ChatMessage;
import Data.ChatRoomViewModel;

public class ListFragment extends Fragment {

    private OnItemSelectedListener mItemListener;

    //variables from chatRoom
    FragmentListBinding binding;
    ChatRoomViewModel chatModel;
    private RecyclerView.Adapter myAdapter;
    ArrayList<ChatMessage> messages;
    private ChatMessageDAO mDao;
    SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
    String currentTime = sdf.format(new Date());

    private String TAG;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
        // inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        //to refer to the activity context from the fragment
        Context context = getActivity().getApplicationContext();

        //to create an instance of the database
        MessageDatabase db = MessageDatabase.getInstance(context);

        //to create an instance of the dao class.
        ChatMessageDAO mdao = db.chatMessageDAO();
        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        messages = chatModel.messages.getValue();

        if (messages == null) {
            chatModel.messages.postValue(messages = new ArrayList<ChatMessage>());

            //to get all the messages from the database and add them to the arraylist.
            new Thread(new Runnable() {
                @Override
                public void run() {
                    messages.addAll(mDao.getAllMessages());
                }
            }).start();
        }

        class MyRowHolder extends RecyclerView.ViewHolder {
            TextView messageText;
            TextView timeText;

            public MyRowHolder(@NonNull View itemView) {
                super(itemView);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = getAdapterPosition();
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", messages.get(position).getID());
                        bundle.putString("message", messages.get(position).getMessage());
                        bundle.putString("timeSent", messages.get(position).getTimeSent());
                        bundle.putBoolean("sendOrReceive", messages.get(position).getIsSentButton());

                        mItemListener.onItemSelected(position,bundle);
                    }
                });

                messageText = itemView.findViewById(R.id.message);
                timeText = itemView.findViewById(R.id.time);

            }
        }

        RecyclerView recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            //this creates the view holder, it represents a single row in the list
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                if (viewType == 0) {
                    SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater());
                    return new MyRowHolder(binding.getRoot());
                } else {
                    ReceiveMessageBinding binding = ReceiveMessageBinding.inflate(getLayoutInflater());
                    return new MyRowHolder(binding.getRoot());
                }
            }

            //this initialize a viewholder to go at the row specified by the position parameter
            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                ChatMessage chatMessage = messages.get(position);
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
                ChatMessage chatMessage = messages.get(position);
                if (chatMessage.getIsSentButton()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });

        Button sendButton=view.findViewById(R.id.sendButton);
        EditText textInput=view.findViewById(R.id.textInput);

      sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("ChatRoom", "send button clicked");
                String message = textInput.getText().toString();
                // boolean sentButton = true;
                ChatMessage chat = new ChatMessage(message, currentTime, true);


                if (!message.isEmpty()){
                    messages.add(chat);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            mDao.insertMessage(chat);
                        }
                    }).start();
                }
                myAdapter.notifyItemInserted(messages.size() - 1);
                //clear the previous text
                textInput.setText("");

            }
        });


       Button receiveButton=view.findViewById(R.id.receiveButton);

       receiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("ChatRoom", "receive button clicked");
                String message = textInput.getText().toString();
                // boolean sentButton = false;
                ChatMessage chat = new ChatMessage(message, currentTime, false);

                if(!message.isEmpty()){
                    messages.add(chat);


                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            mDao.insertMessage(chat);
                        }
                    }).start();
                }
                myAdapter.notifyItemInserted(messages.size() - 1);
                //clear the previous text
                textInput.setText("");

            }
       });
        return view;
    }

    public interface OnItemSelectedListener{
        public void onItemSelected(int position, Bundle bundle);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            mItemListener =(OnItemSelectedListener) context;
        }catch (ClassCastException castException){
            Log.e(TAG, "error in onAttach(). Missing a Listener in the actitivy");
        }
    }
}


