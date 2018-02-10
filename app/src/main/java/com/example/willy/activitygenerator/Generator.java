package com.example.willy.activitygenerator;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Generator extends AppCompatActivity {

    // Buttons
    Button btnGenerate;

    // Text Views
    TextView txtWord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);

        // Linking
        btnGenerate = findViewById(R.id.button_generate);
        txtWord = findViewById(R.id.textView_newWord);

        final ArrayList<String> dict = loadDictionary("german_words.dict");

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = getRandomWord(dict);
                txtWord.setText(word);
            }
        });
    }

    private ArrayList<String> loadDictionary(String pathToDict){
        ArrayList<String> dictionary = new ArrayList<>();

        BufferedReader dict = null;
        AssetManager am = this.getAssets();

        try {
            //dictionary.txt should be in the assets folder.
            dict = new BufferedReader(new InputStreamReader(am.open(pathToDict), "UTF-8"));

            String word;
            while((word = dict.readLine()) != null){
                // System.out.println(word);
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
