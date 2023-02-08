package com.cst2335.xie00076;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    //private MainActivityBinding variableBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     //variableBinding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(R.layout.lab3activity_main);

        Button btn = (Button) findViewById(R.id.button2);
        EditText send_email = findViewById(R.id.editText2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = send_email.getText().toString();
                //an intent object specifies the page where you are and then what page you want to go
                Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                //the information to be attached to the intent object to send to the next page
                i.putExtra("email", str);
                //MainActivity.this.
                startActivity(i); //go to the profile page
            }
        });
    }


}