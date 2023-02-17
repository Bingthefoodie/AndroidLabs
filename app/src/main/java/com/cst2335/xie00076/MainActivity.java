/**
 * Description: This program  has password validation methods used to validate the password
 * user entered, to see if it matches the required criteria with the right length, uppercase, lowercase
 * number and special character.
 * @author Bing Xie
 * @Version 1.0
 * @Date 2023-02-16
 */


package com.cst2335.xie00076;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cst2335.xie00076.R;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private EditText et;
    private Button btn;
    private final int MIN_LENGTH = 4;
    private final int MAX_LENGTH = 20;

    /**
     * This is to initiate and load the program and call the
     * checkPassword method to check user's password
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tv = findViewById(R.id.textview);
        et = findViewById(R.id.edittext);
        btn = findViewById(R.id.button);

        btn.setOnClickListener(clk -> {
            String password = et.getText().toString();

            if (checkPasswordComplexity(password)) {
                tv.setText("Your password is complex enough");
            } else {
                tv.setText("You shall not pass");
            }
        });
    }

    /**
     * checkPassword with for loop to iterate through the password string the user input and
     * validate if the password meets the required criteria
     * @param pw The string object that we are checking
     */
    public boolean checkPasswordComplexity(String pw) {
        boolean foundUpperCase = false;
        boolean foundLowerCase = false;
        boolean foundNumber = false;
        boolean foundSpecial = false;
        boolean lengthValid = false;

        if (pw.length() < MIN_LENGTH && pw.length() > MAX_LENGTH){
            lengthValid=false;
            Toast.makeText(this, "Your password length must be 4 to 20", Toast.LENGTH_SHORT).show();
        }else {
            lengthValid=true;
        }

        for (int i = 0; i < pw.length(); i++) {
            char ch = pw.charAt(i);
            if (Character.isUpperCase(ch)) {
                foundUpperCase = true;
            } else if (Character.isLowerCase(ch)) {
                foundLowerCase = true;
            } else if (Character.isDigit(ch)) {
                foundNumber = true;
            } else if (isSpecialCharacter(ch)) {
                foundSpecial = true;
            }
        }

        if (!foundUpperCase) {
            Toast.makeText(this, "Upper case is needed", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!foundLowerCase) {
            Toast.makeText(this, "Lower case is needed", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!foundNumber) {
            Toast.makeText(this, "Number is needed", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!foundSpecial) {
            Toast.makeText(this, "You need to have at least one special character", Toast.LENGTH_SHORT).show();
            return false;
        }

        return lengthValid&&foundLowerCase&&foundNumber&&foundUpperCase&&foundSpecial;
    }

    /**
     * This is a method to verify if the character matches the special character
     * @param ch match the special character
     * @return true or false if the special char matches
     */

    public boolean isSpecialCharacter(char ch) {
        switch (ch) {
            case '#':
            case '?':
            case '*':
            case '@':
            case '$':
            case '!':
            case '&':
            case '^':
            case '%':
                return true;
            default:
                return false;
        }
    }
}