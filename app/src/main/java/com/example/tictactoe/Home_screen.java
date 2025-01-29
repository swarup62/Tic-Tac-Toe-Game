package com.example.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tictactoe.controller.Common;
import com.google.android.material.card.MaterialCardView;

public class Home_screen extends AppCompatActivity {

    private CardView clickX, clickO;
    private RelativeLayout clickfd, clickbot;
    private String playerChoose = ""; // Initialize to empty string
    private Context context;

    private void init() {
        clickX = findViewById(R.id.click_x);
        clickO = findViewById(R.id.click_o);
        clickbot = findViewById(R.id.botcont);
        clickfd = findViewById(R.id.fdcont);
        context = Home_screen.this;
    } // Added closing brace

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        init();

        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(context, R.color.bg));

        clickX.setOnClickListener(view -> {
            playerChoose = "x";
            clickX.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.btnc_midblue_selectlight)));
            clickO.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.btnc_midblue)));
        });

        clickO.setOnClickListener(view -> {
            playerChoose = "o";
            clickO.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.btnc_midblue_selectlight)));
            clickX.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.btnc_midblue)));
        });

        clickbot.setOnClickListener(view -> {
            Log.d("single", "clicked");
            if (playerChoose.isEmpty()) {
                Common.showToast("Please choose 'X' OR 'O'", Home_screen.this);
            } else {
                Intent intent = new Intent(Home_screen.this, Game_screen.class);
                intent.putExtra("SingleOrDuo", "single");
                intent.putExtra("playerChoose", playerChoose);
                startActivity(intent);
            }
        });

        clickfd.setOnClickListener(view -> {
            Log.d("duo", "clicked");
            if (playerChoose.isEmpty()) {
                Common.showToast("Please choose 'X' OR 'O'", Home_screen.this);
            } else {
                Intent intent = new Intent(Home_screen.this, Game_screen.class);
                intent.putExtra("SingleOrDuo", "duo");
                intent.putExtra("playerChoose", playerChoose);
                startActivity(intent);
            }
        });
    }
}