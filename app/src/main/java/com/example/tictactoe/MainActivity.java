package com.example.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    //Setting Timer
    public static final int SPLASH_SCREEN_TIMEOUT = 2000;

    RelativeLayout btn_Splash;
    TextView btn_Splash_Txt;
    LottieAnimationView btn_Splash_Animation;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(context, R.color.bg));

        //Hook
        btn_Splash = findViewById(R.id.splash_btn);
        btn_Splash_Txt = findViewById(R.id.splash_btn_txt);
        btn_Splash_Animation = findViewById(R.id.splash_btn_animation);

        //Button Click Listener
        btn_Splash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Lottie Animation Visibility
                btn_Splash_Animation.setVisibility(View.VISIBLE);
                btn_Splash_Animation.playAnimation();

                //Button Text Visibility
                btn_Splash_Txt.setVisibility(View.GONE);

                //Handler
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resetButton();
                    }
                }, SPLASH_SCREEN_TIMEOUT);
            }

            private void resetButton() {
                //Lottie Animation Visibility gone
                btn_Splash_Animation.pauseAnimation();
                btn_Splash_Animation.setVisibility(View.GONE);

                //Make Text Visible
                btn_Splash_Txt.setVisibility(View.VISIBLE);

                //Start New Activity
                startActivity(new Intent(MainActivity.this, Home_screen.class));
            }
        });
    }
}