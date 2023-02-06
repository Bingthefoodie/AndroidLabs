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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {


    //declare a tag constant
    private static final String TAG="ProfileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ImageView img=  (ImageView)findViewById(R.id.imageView);
        img.setImageResource(R.drawable.profile);
        Log.e(TAG,"In function: " + "onCreate");
//       Button btn=findViewById(R.id.button2);
//
//        btn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                Intent takePictureIntent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                if(takePictureIntent.resolveActivity(getPackageManager())!=null){
//                    //myPictureTakerLauncher.launch(takePictureIntent);
//                }
//           }
//        });

        TextView receiver_msg;
        /*get the value of email from the main activity page*/
        receiver_msg=findViewById(R.id.editText4);
        //create the get intent object
        Intent intent=getIntent();
        //receive the value by getStringExtra() method and
        //the key must be same name which is send by the main activity
        //depends on the type from the other page's intent putExtra() normally, the type could be int, long, String
        //use gerIntExtra(), getStringExtra(), getBooleanExtra() etc
        String str=intent.getStringExtra("email");
        //display the string into textview
        receiver_msg.setText(str);
    }

//    ActivityResultLauncher<Intent> myPictureTakerLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult()
//            , new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    Log.e(TAG, "In function:" + " onActivityResult");
//                    if (result.getResultCode() == Activity.RESULT_OK) {
//                        Intent data = result.getData();
//                        Bitmap imgBitmap = (Bitmap) data.getExtras().get("data");
//                        ImageView imgView =findViewById(R.id.imageView);
//                        imgView.setImageBitmap(imgBitmap);
//                    } else if (result.getResultCode() == Activity.RESULT_CANCELED)
//                        Log.i(TAG, "User refused to capture a picture.");
//                }
//            });

    protected void onStart() {
        super.onStart();
        Log.e(TAG, "In function:" + " onStart");
    }

    protected void onResume() {
        super.onResume();
        Log.e(TAG, "In function:" + " onResume");
   }

    protected void onPause() {
        super.onPause();
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

}