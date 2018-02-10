package com.example.willy.activitygenerator;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Generator extends AppCompatActivity {

    // Buttons
    Button btnGenerate;

    //Toolbar
    Toolbar mToolbar;

    // Text Views
    TextView txtWord;


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

        final ArrayList<String> dict = loadDictionary();

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = getRandomWord(dict);
                txtWord.setText(word);
            }
        });
    }

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
