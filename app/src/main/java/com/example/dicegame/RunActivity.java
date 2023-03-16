package com.example.dicegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Locale;
import java.util.Random;

public class RunActivity extends AppCompatActivity {

    private Button roll_btn;
    private ImageView dice1, dice2;
    private TextView total;
    private LinearLayout layout;
    TextToSpeech textToSpeech;

    int[] img1 = {R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6};
    int i1 = 0;

    int[] img2 = {R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6};
    int i2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
        binding();
        setImg1(i1);
        setImg2(i2);

        roll_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Random random = new Random();
                int a = random.nextInt(img1.length);

                Random random1 = new Random();
                int b = random1.nextInt(img2.length);

                dice1.setImageResource(img1[a]);
                dice2.setImageResource(img2[b]);

                Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                v.vibrate(300);

                int x = Integer.parseInt(String.valueOf(a));
                int y = Integer.parseInt(String.valueOf(b));
                int tot = (x + 1) + (y + 1);
                total.setText("Your toss is " + tot);

                textToSpeech.speak(total.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);

            }
        });

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                // if No error is found then only it will run
                if (i != TextToSpeech.ERROR) {
                    // To Choose language of speech
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });
    }
    private void binding() {
        roll_btn = findViewById(R.id.roll_btn);
        dice1 = findViewById(R.id.dice1);
        dice2 = findViewById(R.id.dice2);
        total = findViewById(R.id.total);
        layout = findViewById(R.id.layout);
    }
    void setImg1(int p) {
        Glide.with(RunActivity.this)
                .load(img1[p])
                .placeholder(R.drawable.ic_launcher_background)
                .into(dice1);
    }
    void setImg2(int p) {
        Glide.with(RunActivity.this)
                .load(img2[p])
                .placeholder(R.drawable.ic_launcher_background)
                .into(dice2);
    }
}