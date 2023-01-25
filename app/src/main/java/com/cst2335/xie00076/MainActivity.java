package com.cst2335.xie00076;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_constraint);

        Button button = (Button) findViewById(R.id.button);
        String toastMessage=MainActivity.this.getResources().getString(R.string.toast_message);
        String buttonText=MainActivity.this.getResources().getString(R.string.ButtonText);
        String checkBox=MainActivity.this.getResources().getString(R.string.check_box);
        String switchOnOff=MainActivity.this.getResources().getString(R.string.switch_onoff);
        String hintMessage=MainActivity.this.getResources().getString(R.string.hint_message);
        String snackBarMessage=MainActivity.this.getResources().getString(R.string.snack_bar);
        String unDo=MainActivity.this.getResources().getString(R.string.undo_label);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_LONG).show();
            }

        });
        Switch mySwitch = (Switch) findViewById(R.id.switch2);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton cb, boolean b) {

                String message="";

                if (b == true) {
                    message = " on";
                } else {
                    message = " off";
                }
                Snackbar mySnackBar=Snackbar.make(cb,snackBarMessage+message,Snackbar.LENGTH_LONG);
                mySnackBar.show();
                mySnackBar.setAction(unDo,click->cb.setChecked(!b));
            }
        });

    }
}