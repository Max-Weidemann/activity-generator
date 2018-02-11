package com.example.willy.activitygenerator;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

public class SettingsActivity extends AppCompatPreferenceActivity {
    private static boolean isXLargeTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    @Override
    public boolean onIsMultiPane() {
        return isXLargeTablet(this);
    }


    @Override
    public void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

}
