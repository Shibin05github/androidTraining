package com.helloworld;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.helloworld.Data.MyDbHandler;
import com.helloworld.Model.Contact;

public class SignUpActivity extends AppCompatActivity {

    EditText fName, LName, phNum, eMail, pwd;
    Button loginBtn;
    SharedPreferences myPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        MyDbHandler myDbHandler = new MyDbHandler(SignUpActivity.this);


        fName = findViewById(R.id.etFirstName);
        LName = findViewById(R.id.etLastName);
        phNum = findViewById(R.id.etPhoneNumber);
        eMail = findViewById(R.id.Id);
        pwd = findViewById(R.id.etPwd);
        loginBtn = findViewById(R.id.signUpBtn);

        myPref = getSharedPreferences("mySharedPreferences", MODE_PRIVATE);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = fName.getText().toString();
                String lastName = LName.getText().toString();
                String phoneNumber = phNum.getText().toString();
                int id = eMail.getId();
                String password = pwd.getText().toString();
                if (validate(firstName, lastName, phoneNumber, id, password)) {

                    //using shared preference
//                    SharedPreferences.Editor myEditor = myPref.edit();
//                    myEditor.putString("name", firstName + " " + lastName);
//                    myEditor.putString("pwd", password);
//                    myEditor.apply();          ----> commented this code for using another method.
//                                                     Storing data in db when do signup and accessing the data on login page from db.

                    Contact mydetails = new Contact();
                    mydetails.setId(id);
                    mydetails.setPhoneNumber(phoneNumber);
                    myDbHandler.addContact(mydetails);
                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));         //this line works as navigation to login screen.

                    openDialogBox(); //this will open diaglog box
                }

            }
        });
    }

    private void openDialogBox() {
        AlertDialog.Builder builder=new AlertDialog.Builder(SignUpActivity.this);
        builder.setTitle("Alert!");
        builder.setIcon(R.drawable.ic_baseline_campaign_24);
        builder.setMessage("Are you sure?");
        builder.setCancelable(false);
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(SignUpActivity.this,MainActivity.class));

            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(SignUpActivity.this, "You pressed no button", Toast.LENGTH_SHORT).show();
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private boolean validate(String firstName, String lastName, String phoneNumber, int id, String password) {
        if (firstName.isEmpty()) {
            fName.setError("Field cannot be empty");
            return false;
        } else if (lastName.isEmpty()) {
            LName.setError("Field cannot be empty");
            return false;
        } else if (phoneNumber.isEmpty()) {
            phNum.setError("Field cannot be empty");
            return false;
        } else if (password.isEmpty()) {
            pwd.setError("Field cannot be empty");
            return false;
        } else if (password.length() < 4) {
            pwd.setError("Password is too small");
            return false;
        }
        return true;
    }
}