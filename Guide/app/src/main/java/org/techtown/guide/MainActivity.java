package org.techtown.guide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public SharedPreferences prefs; // 선언하기

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences("Pref", MODE_PRIVATE);
        checkFirstRun();
    }

    public void checkFirstRun() {
        boolean isFirstRun = prefs.getBoolean("isFirstRun", true);
        if (isFirstRun) {
            Intent intent = new Intent(getApplicationContext(), GuideActivity.class);
            startActivity(intent);
            prefs.edit().putBoolean("isFirstRun", false).apply();
        }

    }
}