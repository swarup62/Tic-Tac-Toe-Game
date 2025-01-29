package com.example.tictactoe;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.tictactoe.controller.Common;

public class AboutUs extends AppCompatActivity {

    ImageView swfb, swinsta, swlkdn, swgit, dvmail;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        context = this;

        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(context, R.color.bg));

        swfb = findViewById(R.id.sfb);
        swinsta = findViewById(R.id.sinsta);
        swlkdn = findViewById(R.id.slkdn);
        swgit = findViewById(R.id.sgit);

        dvmail = findViewById(R.id.mail_us);

        // Swarup Social
        swfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.facebook.com/share/15fEVhfdV4/");
            }
        });

        swinsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.instagram.com/rup_62?igsh=MTJxdWk0aWY2YjAwZA==");
            }
        });
        swlkdn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.linkedin.com/in/swarup-paul-70b807316?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app");
            }
        });
        swgit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://github.com/swarup62");
            }
        });

        // DevByHeart mail
        dvmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail("swarup62paul@gmail.com");
            }
        });

    }

    private void sendEmail(String mail) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:")); // Only email apps should handle this
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{mail});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contact from TicTacToe App");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hi Swarup,\n\n");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        } catch (ActivityNotFoundException ex) {
            // Handle the case where no email app is installed
            // You can show a Toast or an AlertDialog to inform the user
            // For example:
            // Toast.makeText(this, "No email clients installed.", Toast.LENGTH_SHORT).show();
            Common.showToast("No email clients installed.", context);
        }
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}