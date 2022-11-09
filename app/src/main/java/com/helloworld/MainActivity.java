package com.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.helloworld.Data.MyDbHandler;
import com.helloworld.Model.Contact;

import java.sql.SQLOutput;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public int Counter = 0;
    Button loginBtn;
    EditText uName,password;
    String actualUname="Shibin";
    String actualPwd = "Shibin@123";
//    String fName = getIntent().getExtras().getString("fName");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginBtn = findViewById(R.id.loginBtn);
        uName = findViewById(R.id.userName);
        password =findViewById(R.id.password);

        //accessing data shared from anther activity using shared preferences.
//        SharedPreferences myPref = getSharedPreferences("mySharedPreferences",MODE_PRIVATE);
//        String name =myPref.getString("name","");
//        String pwd = myPref.getString("pwd","123");
//        Log.e("---",name+"    "+pwd);
        //commented this line to use db to access userData(after signup)

        //get userData(id,phone number)
        MyDbHandler myDbHandler = new MyDbHandler(MainActivity.this);
                List<Contact> allContacts= myDbHandler.getAllContacts();
        for (Contact contact:allContacts) {
            Log.d("userDetails","data read from table-id:"+contact.getId());
            Log.d("userDetails","data read from table-phn:"+contact.getPhoneNumber());
            Log.d("userDetails","data read from table:"+contact);


        }
//        uName.setText(name);
//        password.setText(pwd);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
//                uName = findViewById(R.id.userName);
//                password = findViewById(R.id.password);
//
//                String userName = uName.getText().toString();
//                String userPassword = password.getText().toString();
//
//                validateFn(userName,userPassword);
            }
        });
    }

    //currently not using this method for validation

//    private void validateFn(String userName, String userPassword) {
//
//        if (TextUtils.isEmpty(userName)||TextUtils.isEmpty(userPassword)){
//            Toast.makeText(this, "Empty data provided", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        else if(!userName.matches("[A-Za-z]+")) {
//            Toast.makeText(this, "Username contains other characters", Toast.LENGTH_SHORT).show();
//            return;
//
//        }
//        else if(userName.equals(actualUname)&&userPassword.equals(actualPwd)){
//            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        else{
//            Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
//        }
//    }
}