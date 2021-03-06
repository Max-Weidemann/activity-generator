package com.example.willy.activitygenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    // Buttons
    private Button btnPlay;

    //Toolbar
    Toolbar mToolbar;

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
                Intent setIntent = SettingsActivity.makeSettingsIntent(StartActivity.this);
                startActivity(setIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //Activate Toolbar
        mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);

        //Linking
        btnPlay = findViewById(R.id.button_play);
        btnPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId())
        {
            case R.id.button_play:
                Intent genIntent = Generator.makeGeneratorIntent(StartActivity.this);
                startActivity(genIntent);
        }
    }
}
