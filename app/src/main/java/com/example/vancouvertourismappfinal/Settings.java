package com.example.vancouvertourismappfinal;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.vancouvertourismappfinal.LoginRegisterClasses.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.vancouvertourismappfinal.LoginRegisterClasses.LoginActivity.MyPREFERENCES;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void share(View view) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Download the app via play store now...";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));

    }

    public void rate(View view) {
        final String appPackageName = "com.example.drawersuvidha";

        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    public void call(View view) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "9779778322", null)));

    }

    public void email(View view) {
        sendEmail();}
    @SuppressLint({"LongLogTag", "IntentReset"})
    protected void sendEmail () {
        Log.i("Send email", "");
        String[] TO = {"mnvnsandhu@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Settings.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }


    public void logout(View view) {
        SharedPreferences.Editor editor = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();

        FirebaseAuth.getInstance().signOut();

        Intent intent = new Intent(Settings.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
