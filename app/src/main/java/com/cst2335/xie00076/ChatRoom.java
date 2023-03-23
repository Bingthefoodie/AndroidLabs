package com.cst2335.xie00076;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.cst2335.xie00076.databinding.ActivityChatRoomBinding;
import com.cst2335.xie00076.databinding.ReceiveMessageBinding;
import com.cst2335.xie00076.databinding.SentMessageBinding;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import Data.ChatMessage;
import Data.ChatRoomViewModel;

public class ChatRoom extends AppCompatActivity implements ListFragment.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        //verify if frame layout2 is loaded
        FrameLayout fl2 = findViewById(R.id.frameLayout2);
        FragmentManager fm = getSupportFragmentManager();

        //create fragments
        ListFragment lf = new ListFragment();
        DetailsFragment df = new DetailsFragment();


        //this is for a phone
        if (fl2 == null) {
            fm.beginTransaction().add(R.id.frameLayout1, lf).commit();
        }

        if (fl2 != null) {
            fm.beginTransaction().replace(R.id.frameLayout1, lf).add(R.id.frameLayout2, df).commit();
        }

    }

    @Override
    public void onItemSelected(int position, Bundle bundle) {
           FrameLayout fl2=findViewById(R.id.frameLayout2);
           FragmentManager fm=getSupportFragmentManager();
           DetailsFragment df= new DetailsFragment();
           df.setArguments(bundle);

           if(fl2==null ){
               fm.beginTransaction().replace(R.id.frameLayout1,df).addToBackStack(null).commit();
           }

           if(fl2!=null){
               fm.beginTransaction().replace(R.id.frameLayout2,df).commit();
           }
    }

}