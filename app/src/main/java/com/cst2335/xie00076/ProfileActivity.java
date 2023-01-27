package com.cst2335.xie00076;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView receiver_msg;
    //declare a tag constant
    private static final String TAG="ProfileActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
//        Log.e(TAG, "onCreate()");
//        super.onStart();
//        Log.e(TAG, "onStart()");
//        super.onResume();
//        Log.e(TAG, "onResume()");
//        super.onPause();
//        Log.e(TAG, "onPause()");
//        super.onStop();
//        Log.e(TAG, "onStop()");
//        super.onDestroy();
//        Log.e(TAG, "onDestroy()");

        /*get the value of email from the main activity page*/
        receiver_msg=findViewById(R.id.editText2);
        //create the get intent object
        Intent intent=getIntent();
        //receive the value by getStringExtra() method and
        //the key must be same which is send by the main activity
        String str=intent.getStringExtra("email");
        //display the string into textview
        receiver_msg.setText(str);

            ActivityResultLauncher<Intent> myPictureTakerLauncher = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult()
                    , new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            int requestCode = 0;
                            int resultCode = 0;
                            Intent data = null;
                            ImageView imgView = null;
                            ProfileActivity.super.onActivityResult(requestCode, resultCode, data);
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                data = result.getData();
                                Bitmap imgbitmap = (Bitmap) data.getExtras().get("data");
                                imgView.setImageBitmap(imgbitmap);
                            } else if (result.getResultCode() == Activity.RESULT_CANCELED)
                                Log.i(TAG, "User refused to capture a picture.");
                        }
                    });

    }
}