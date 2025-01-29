package com.example.tictactoe.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.tictactoe.R;

import java.util.Objects;

public class Common {

    public static void showToast(String msg, Context context) {
        // Optional: Add logic to turn off toast display in some situations if needed
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void alertBox(String singleOrDuo, String winningPlayer, Context context) {
        AlertDialog alertBox;
        View alertBoxView = LayoutInflater.from(context).inflate(R.layout.dialog_box_ui, null);
        AlertDialog.Builder alertDialogue = new AlertDialog.Builder(context);
        alertDialogue.setView(alertBoxView);

        TextView winningTv = alertBoxView.findViewById(R.id.winorloose);
        ImageView icon = alertBoxView.findViewById(R.id.cup);
        ImageView star = alertBoxView.findViewById(R.id.star);

        alertBox = alertDialogue.create();
        alertBox.setCanceledOnTouchOutside(true);

        // Tie case
        if (singleOrDuo.equals("tie")) {
            winningTv.setText("It's a Tie!");
            icon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.x)); // You can customize with your tie icon
            star.setVisibility(View.GONE); // No star for tie
        }
        // Single-player game
        else if (singleOrDuo.equals("single")) {
            if (winningPlayer.equals("x")) {
                winningTv.setText("You Win");
                icon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.award));
                star.setVisibility(View.VISIBLE); // Star showing up on win
            } else {
                winningTv.setText("You Lose");
                winningTv.setTextColor(context.getColor(R.color.btnc_midred)); // Custom color for loss
                icon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.broke));
                star.setVisibility(View.GONE); // No star for loss
            }
        }
        // Multiplayer game
        else {
            if (winningPlayer.equals("x")) {
                winningTv.setText("X wins");
            } else {
                winningTv.setText("O wins");
            }
            icon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.award));
            star.setVisibility(View.VISIBLE); // Star showing up for multiplayer winner
        }

        // Make background transparent for the alert box
        Objects.requireNonNull(alertBox.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertBox.show();
    }
}
