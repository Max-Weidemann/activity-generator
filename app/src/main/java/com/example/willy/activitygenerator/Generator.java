package com.example.willy.activitygenerator;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
// import java.util.concurrent.ThreadLocalRandom;

public class Generator extends AppCompatActivity {

    // Buttons
    Button btnGenerate;

    //Toolbar
    Toolbar mToolbar;

    // Text Views
    TextView txtWord;

    //Job ImageView
    ImageView jobView;

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

        } catch (FileNotFoundException e){
            e.printStackTrace();
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
