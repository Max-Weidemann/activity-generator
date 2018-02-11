package com.example.willy.activitygenerator;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.os.CountDownTimer;
import android.content.Intent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Locale;

//Unused:
/*import android.annotation.SuppressLint;
import android.content.Context;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.TextureView;
import android.widget.TextClock;
import android.widget.Toast;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;*/

public class Generator extends AppCompatActivity {

    // Buttons
    Button btnGenerate;

    //Toolbar
    Toolbar mToolbar;

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

        //Activate Toolbar
        mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);

        // Linking
        btnGenerate = findViewById(R.id.button_generate);
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

                //Print jobId into word for reference do:
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


    //OptionsMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
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
