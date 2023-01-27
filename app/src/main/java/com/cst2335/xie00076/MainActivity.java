package com.cst2335.xie00076;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab3activity_main);

        Button btn = (Button) findViewById(R.id.button2);
        EditText send_email = findViewById(R.id.editText2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = send_email.getText().toString();
                Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                i.putExtra("email", str);
                MainActivity.this.startActivity(i);
            }
        });
    }
//     private void dispatchTakePictureIntent () {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            ActivityResultLauncher<Intent> myPictureTakerLauncher=null;
//            myPictureTakerLauncher.launch(takePictureIntent);
//        }
//    }
}