package com.cst2335.xie00076;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;

public class ProfileActivity extends AppCompatActivity {

    //declare a tag constant
    private static final String TAG="ProfileActivity";

    EditText nameKey,addressKey, emailKey;
    Button saveButton;
    Button clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.e(TAG,"In function: " + "onCreate");
        TextView receiver_msg;
        /*get the value of email from the main activity page*/
        receiver_msg=findViewById(R.id.editText4);
        //create the get intent object
        Intent intent=getIntent();
        //receive the value by getStringExtra() method and
        //the key must be same which is send by the main activity
        String str=intent.getStringExtra("email");
        //display the string into textview
        receiver_msg.setText(str);

        nameKey=(EditText)findViewById(R.id.editText2);
        addressKey=(EditText)findViewById(R.id.editText3);
        emailKey=(EditText)findViewById(R.id.editText4);
        saveButton=(Button)findViewById(R.id.button2);
        clearButton=(Button)findViewById(R.id.button);


        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                saveData(nameKey,addressKey,emailKey);
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameKey.getText().clear();
                addressKey.getText().clear();
                saveData(nameKey,addressKey,emailKey);
            }
        });


    }


    protected void onStart() {
        super.onStart();
        retrieveData();
        Log.e(TAG, "In function:" + " onStart");
    }

    protected void onResume() {
        super.onResume();
        retrieveData();
        Log.e(TAG, "In function:" + " onResume");
    }

    protected void onPause() {
        super.onPause();
        saveData(this.nameKey,this.addressKey,this.emailKey);
        Log.e(TAG, "In function:" + " onPause");
    }

    protected void onStop() {
        super.onStop();
        Log.e(TAG, "In function:" + " onStop");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "In function:" + " onDestroy");
    }

    public void retrieveData(){
        SharedPreferences sharedPreferences =getSharedPreferences("MyPrefrences", Context.MODE_PRIVATE);
        String s1 =sharedPreferences.getString("name","");
        String s2 =sharedPreferences.getString("address","");
        nameKey.setText(s1);
        addressKey.setText(s2);
    }

    public void saveData(EditText nameKey, EditText addressKey, EditText emailKey){
        SharedPreferences sharedPreferences =getSharedPreferences("MyPrefrences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("name",nameKey.getText().toString());
        editor.putString("address",addressKey.getText().toString());
        editor.apply();
    }
}

