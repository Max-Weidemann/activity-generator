package com.example.willy.activitygenerator;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.CountDownTimer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Locale;

//Unused:
/*import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.Menu;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.TextureView;
import android.widget.TextClock;
import android.widget.Toast;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;*/

public class Generator extends AppCompatActivity {

    public static Intent makeGeneratorIntent (Context context) {
        return new Intent(context, Generator.class);
    }

    // Buttons
    Button btnGenerate;

    // Text Views
    TextView txtWord;
    TextView countdown_timer;

    //Job ImageView
    ImageView jobView;
    CountDownTimer cdt;

    public Generator() {
        cdt = null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);

        // Linking
        btnGenerate = findViewById(R.id.button_check);
        txtWord = findViewById(R.id.textView_newWord);
        jobView = findViewById(R.id.imageView_todo);
        countdown_timer = findViewById(R.id.textView_clock);

        final ArrayList<String> dict = loadDictionary();

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Generate random job:
                int min = 1;
                int max = 4;
                int jobId = (int) (Math.random() * (max-min) + min);

                String word = getRandomWord(dict);

                //For printing jobId into word for reference do:
                // String jobword = String.valueOf(jobId);

                txtWord.setText(word);

                if (jobId==1)
                {
                    jobView.setImageResource(R.drawable.ic_draw);
                }

                if (jobId==2)
                {
                    jobView.setImageResource(R.drawable.ic_explain);
                }

                if (jobId==3)
                {
                    jobView.setImageResource(R.drawable.ic_meme);
                }

                if (cdt != null) {
                    cdt.cancel();
                }
                cdt = new CountDownTimer(300000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        String text = String.format(Locale.getDefault(), "%02d:%02d",
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60);
                        countdown_timer.setText(text);
                        // countdown_timer.setText("Timer: " + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        countdown_timer.setText(R.string.time_over);
                    }
                };

                cdt.start();

            }
        });
    }

    private ArrayList<String> loadDictionary(){
        ArrayList<String> dictionary = new ArrayList<>();

        BufferedReader dict = null;
        AssetManager am = this.getAssets();

        try {
            //dictionary.txt should be in the assets folder.
            dict = new BufferedReader(new InputStreamReader(am.open("german_words.dict"), "windows-1252"));

            String word;
            while((word = dict.readLine()) != null){
//                System.out.println(word);
                dictionary.add(word);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            assert dict != null;
            dict.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return dictionary;
    }

    private String getRandomWord(ArrayList<String> dict){
        return dict.get((int)(Math.random() * dict.size()));
    }
}
