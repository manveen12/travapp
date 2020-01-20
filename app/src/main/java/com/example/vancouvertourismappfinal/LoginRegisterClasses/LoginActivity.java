package com.example.vancouvertourismappfinal.LoginRegisterClasses;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.vancouvertourismappfinal.MainActivity_1;
import com.example.vancouvertourismappfinal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText username, password, reg_username, reg_password,
            reg_firstName, reg_lastName, reg_email, reg_confirmemail;
    Button login, signUp, reg_register;
    Spinner spinnerRole;
    Spinner spinnerRole2;
    TextInputLayout txtInLayoutUsername, txtInLayoutPassword, txtInLayoutRegPassword;
    String pass,role;
    public static final String MyPREFERENCES = "com.example.myrentalapp.MyPrefs";

    private DBConnectivity receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signUp = findViewById(R.id.signUp);
        txtInLayoutUsername = findViewById(R.id.txtInLayoutUsername);
        txtInLayoutPassword = findViewById(R.id.txtInLayoutPassword);
        spinnerRole=findViewById(R.id.spinner2);

        receiver = new DBConnectivity(pass,role,this);

        spinnerRole.setSelection(1);


        ClickLogin();


        //SignUp's Button for showing registration page
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickSignUp();
            }
        });


        FirebaseAuth auth = FirebaseAuth.getInstance();

        // forgot password code

      /*   auth.sendPasswordResetEmail("email").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        }); */


        if(auth.getCurrentUser() != null)
        {
            Intent i = new Intent(LoginActivity.this, MainActivity_1.class);
            i.putExtra("UserName", auth.getCurrentUser().getEmail() );
            i.putExtra("loginOrRegister", "login");

            startActivity(i);
        }

    }

    //This is method for doing operation of check login
    private void ClickLogin() {

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {


                if (username.getText().toString().trim().isEmpty()) {

                    Snackbar snackbar = Snackbar.make(view, "Please fill out these fields",
                            Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.main));
                    snackbar.show();
                    txtInLayoutUsername.setError("Username should not be empty");
                }
                if (password.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(view, "Please fill out these fields",
                            Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.main));
                    snackbar.show();
                    txtInLayoutPassword.setError("Password should not be empty");
                }

              else{
                SharedPreferences editor = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                String UserName= editor.getString("UserName", username.getText().toString());
                String Password=editor.getString("Password", password.getText().toString());


                FirebaseAuth auth = FirebaseAuth.getInstance();

                auth.signInWithEmailAndPassword( username.getText().toString() , password.getText().toString() ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete( Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            Intent i = new Intent(LoginActivity.this, MainActivity_1.class);
                            i.putExtra("UserName", username.getText().toString());
                            i.putExtra("loginOrRegister", "login");

                            startActivity(i);
                        }
                        else {

                            Snackbar snackbar = Snackbar.make(view, "Username or Password is Incorrect",
                                    Snackbar.LENGTH_LONG);
                            View snackbarView = snackbar.getView();
                            snackbarView.setBackgroundColor(getResources().getColor(R.color.main));
                            snackbar.show();
                            txtInLayoutPassword.setError("Username or Password is Incorrect");
                        }
                    }
                });


            }

            }
        });

    }

    //The method for opening the registration page and another processes or checks for registering
    private void ClickSignUp() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.register, null);
        dialog.setView(dialogView);

        reg_username = dialogView.findViewById(R.id.reg_username);
        reg_password = dialogView.findViewById(R.id.reg_password);
        reg_firstName = dialogView.findViewById(R.id.reg_firstName);
        reg_lastName = dialogView.findViewById(R.id.reg_lastName);
        reg_register = dialogView.findViewById(R.id.reg_register);
        txtInLayoutRegPassword = dialogView.findViewById(R.id.txtInLayoutRegPassword);
        spinnerRole2=dialogView.findViewById(R.id.spinner3);
        spinnerRole2.setSelection(0);

        reg_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reg_username.getText().toString().trim().isEmpty()) {

                    reg_username.setError("Please fill out this field");

                    return;
                }
                if (reg_password.getText().toString().trim().isEmpty()) {
                    txtInLayoutRegPassword.setPasswordVisibilityToggleEnabled(false);
                    reg_password.setError("Please fill out this field");

                    return;
                } else {
                    txtInLayoutRegPassword.setPasswordVisibilityToggleEnabled(true);
                    //Here you can write the codes for checking password
                }
                if (reg_firstName.getText().toString().trim().isEmpty()) {

                    reg_firstName.setError("Please fill out this field");

                    return;
                } else {
                    //Here you can write the codes for checking firstname

                }
                if (reg_lastName.getText().toString().trim().isEmpty()) {

                    reg_lastName.setError("Please fill out this field");

                    return;
                }
                    //Here you can write the codes for checking lastname


                FirebaseAuth auth  = FirebaseAuth.getInstance();

                auth.createUserWithEmailAndPassword(reg_username.getText().toString() , reg_password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete( Task<AuthResult> task) {

                        if(task.isSuccessful()) {
                            SharedPreferences.Editor editor = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE).edit();
                            editor.putString("UserName", reg_username.getText().toString());
                            editor.putString("Password", reg_password.getText().toString());
                            editor.putString("FirstName", reg_firstName.getText().toString());
                            editor.putString("LastName", reg_lastName.getText().toString());
                            editor.putString("Role", spinnerRole2.getSelectedItem().toString());
                            editor.putString("loginOrRegister", "register");

                            editor.apply();
                            Intent i = new Intent(LoginActivity.this, MainActivity_1.class);
                            i.putExtra("UserName", reg_username.getText().toString());
                            i.putExtra("Password", reg_password.getText().toString());
                            i.putExtra("FirstName", reg_firstName.getText().toString());
                            i.putExtra("LastName", reg_lastName.getText().toString());
                            i.putExtra("Role", spinnerRole2.getSelectedItem().toString());
                            i.putExtra("loginOrRegister", "register");
                            startActivity(i);
                        }

                        else {

                            Toast.makeText(LoginActivity.this , "please try again" , Toast.LENGTH_SHORT).show();

                        }

                    }
                });



            }
        });


        dialog.show();


    }

    public void forget(View view) {
        Intent i = new Intent(LoginActivity.this, forgetpass.class);
        startActivity(i);
    }

   /* @Override
    protected void onPause() {
        // Unregister since the activity is paused.
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // An IntentFilter can match against actions, categories, and data
        IntentFilter filter = new IntentFilter(DBConnectivity.STATUS_DONE);
          *//*
        Intent registerReceiver (BroadcastReceiver receiver, IntentFilter filter)
        Register a BroadcastReceiver to be run in the main activity thread.
        The receiver will be called with any broadcast Intent that matches filter,
        in the main application thread.
         *//*

        registerReceiver(receiver,filter);
    }*/
}
