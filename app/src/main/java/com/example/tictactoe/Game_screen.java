package com.example.tictactoe;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.tictactoe.controller.Common;
import com.google.android.material.card.MaterialCardView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;

public class Game_screen extends AppCompatActivity {

    private Context context;
    private MaterialCardView box1Card, box2Card, box3Card, box4Card, box5Card, box6Card, box7Card, box8Card, box9Card, resetCard;
    private ImageView turnImage;
    private ImageView box1Image, box2Image, box3Image, box4Image, box5Image, box6Image, box7Image, box8Image, box9Image;
    private TextView xScoreTv, oScoreTv, tiesTv;

    private boolean box1bl, box2bl, box3bl, box4bl, box5bl, box6bl, box7bl, box8bl, box9bl;
    private boolean isX_turn = true;
    private String singleOrDuo;
    private String choosedMark;
    private int markedCount;
    private boolean xIsYou;
    private Map<Integer, String> boardState = new HashMap<>();
    private boolean cpuTurn = false;

    private void init() {
        context = Game_screen.this;
        box1Card = findViewById(R.id.box1);
        box2Card = findViewById(R.id.box2);
        box3Card = findViewById(R.id.box3);
        box4Card = findViewById(R.id.box4);
        box5Card = findViewById(R.id.box5);
        box6Card = findViewById(R.id.box6);
        box7Card = findViewById(R.id.box7);
        box8Card = findViewById(R.id.box8);
        box9Card = findViewById(R.id.box9);

        box1Image = findViewById(R.id.box1_img);
        box2Image = findViewById(R.id.box2_img);
        box3Image = findViewById(R.id.box3_img);
        box4Image = findViewById(R.id.box4_img);
        box5Image = findViewById(R.id.box5_img);
        box6Image = findViewById(R.id.box6_img);
        box7Image = findViewById(R.id.box7_img);
        box8Image = findViewById(R.id.box8_img);
        box9Image = findViewById(R.id.box9_img);

        xScoreTv = findViewById(R.id.x_score);
        oScoreTv = findViewById(R.id.o_score);
        tiesTv = findViewById(R.id.tie_score);

        turnImage = findViewById(R.id.turn_img);

        resetCard = findViewById(R.id.reset_game);
        box1bl = box2bl = box3bl = box4bl = box5bl = box6bl = box7bl = box8bl = box9bl = false;
        isX_turn = true;

        Intent intent = getIntent();
        singleOrDuo = intent.getStringExtra("SingleOrDuo");
        choosedMark = intent.getStringExtra("playerChoose");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        init();

        // Initializing the turn based on the player's choice
        if (choosedMark.equals("x")) {
            isX_turn = true;
        } else {
            isX_turn = false;
            turnImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.o));
        }

        // Handling About Us
        LinearLayout abusLinearLayout = findViewById(R.id.abus);
        abusLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game_screen.this, AboutUs.class);
                startActivity(intent);
            }
        });

        // Handling reset button click
        resetCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (singleOrDuo.equals("single") && choosedMark.equals("o")) {
                    resetGame(true);
                } else {
                    resetGame(false);
                }
            }
        });

        startGame();
    }

    private void startGame() {
        // Setting click listeners for all the boxes
        setBoxListeners();

        if (singleOrDuo.equals("single") && choosedMark.equals("o")) {
            xIsYou = false;
            cpuTurn = true; // Let CPU make the first move
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    randomCpu();
                }
            }, 2000);
        } else {
            xIsYou = true;
        }
    }

    private void setBoxListeners() {
        box1Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBoxClick(1);
            }
        });
        box2Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBoxClick(2);
            }
        });
        box3Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBoxClick(3);
            }
        });
        box4Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBoxClick(4);
            }
        });
        box5Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBoxClick(5);
            }
        });
        box6Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBoxClick(6);
            }
        });
        box7Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBoxClick(7);
            }
        });
        box8Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBoxClick(8);
            }
        });
        box9Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBoxClick(9);
            }
        });
    }

    private void onBoxClick(int boxId) {
        // Preventing a click on already marked boxes
        if (boardState.containsKey(boxId)) {
            Common.showToast("Already Marked", context);  // Show the message if the box is already marked
            return;  // Return early if the box is already marked
        }

        // Determine which mark (X or O) to display
        String playerMark = isX_turn ? "x" : "o";
        ImageView boxImage = getBoxImage(boxId);

        // Make the image view visible and set the correct image for X or O
        boxImage.setVisibility(View.VISIBLE);  // Ensure it's visible
        if (playerMark.equals("x")) {
            boxImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.x));  // X mark
        } else {
            boxImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.o));  // O mark
        }

        // Add this move to the board state
        boardState.put(boxId, playerMark);
        markedCount++;  // Increment the marked count

        // Check for a winner after marking the box
        if (markedCount >= 5) {
            String result = checkWhoIsWin();
            if (!result.equals("No winner yet")) {
                gameEnd(result);  // End the game if there’s a winner
                return;  // Stop further execution after a winner is found
            }
        }

        // Switch turns between players and CPU
        isX_turn = !isX_turn;
        cpuTurn = !cpuTurn;  // Toggle CPU turn
        updateTurnImage();  // Update turn image

        // If it's CPU's turn, make the CPU move after a delay
        if (cpuTurn && singleOrDuo.equals("single") && !isX_turn) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    randomCpu();  // Let the CPU make a random move
                }
            }, 1000);
        }
    }


    private ImageView getBoxImage(int boxId) {
        switch (boxId) {
            case 1: return box1Image;
            case 2: return box2Image;
            case 3: return box3Image;
            case 4: return box4Image;
            case 5: return box5Image;
            case 6: return box6Image;
            case 7: return box7Image;
            case 8: return box8Image;
            case 9: return box9Image;
            default: return null;
        }
    }

    private void randomCpu() {
        if (cpuTurn) {
            List<Integer> availableMoves = new ArrayList<>();
            for (int i = 1; i <= 9; i++) {
                if (!boardState.containsKey(i)) {
                    availableMoves.add(i);
                }
            }

            if (!availableMoves.isEmpty()) {
                int randomMove = availableMoves.get(new Random().nextInt(availableMoves.size()));
                onBoxClick(randomMove);
            }
        }
    }

    private void updateTurnImage() {
        turnImage.setImageDrawable(ContextCompat.getDrawable(context, isX_turn ? R.drawable.x : R.drawable.o));
    }

    public String checkWhoIsWin() {
        int[][] winPatterns = {
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9},
                {1, 4, 7}, {2, 5, 8}, {3, 6, 9},
                {1, 5, 9}, {3, 5, 7}
        };

        for (int[] pattern : winPatterns) {
            String first = boardState.get(pattern[0]);
            String second = boardState.get(pattern[1]);
            String third = boardState.get(pattern[2]);

            if (first != null && first.equals(second) && second.equals(third)) {
                return first;
            }
        }

        if (boardState.size() == 9) {
            return "tie";
        }

        return "No winner yet";
    }

    @SuppressLint("SetTextI18n")
    private void gameEnd(String winningMark) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Show the result (win/lose/tie)
                Common.alertBox(winningMark.equals("tie") ? "tie" : "single", winningMark, context);

                // Reset the game after a short delay
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resetGame(false); // false because we don't want CPU's random start here
                    }
                }, 2000); // Add a small delay before resetting
            }
        }, 500);
    }


    private void resetGame(boolean isFromRandom) {
        // Hide all marks (X and O) from the board
        box1Image.setVisibility(View.GONE);
        box2Image.setVisibility(View.GONE);
        box3Image.setVisibility(View.GONE);
        box4Image.setVisibility(View.GONE);
        box5Image.setVisibility(View.GONE);
        box6Image.setVisibility(View.GONE);
        box7Image.setVisibility(View.GONE);
        box8Image.setVisibility(View.GONE);
        box9Image.setVisibility(View.GONE);

        // Reset the turn image to "X"
        turnImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.x));

        // Clear the board state
        boardState.clear();
        markedCount = 0;
        isX_turn = true;  // Reset the turn to X
        cpuTurn = false;  // Disable CPU's turn initially

        // If it's a single-player game and we are resetting from a random CPU move
        if (singleOrDuo.equals("single") && isFromRandom) {
            randomCpu();  // Let the CPU make the first random move
        }
    }

}