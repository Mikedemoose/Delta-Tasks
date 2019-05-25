package com.example.killright;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.content.Intent;

public class LauncherActivity extends AppCompatActivity {

    private Button Play_Button ;
    private Button Stats_Button ;
    private Intent launcher_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        Play_Button = (Button)findViewById(R.id.Play_Button);
        Stats_Button = (Button)findViewById(R.id.Stats_Button);

        Play_Button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        launcher_intent = new Intent(LauncherActivity.this,FirstActivity.class);
                        startActivity(launcher_intent);
                    }
                }
        );

        Stats_Button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        launcher_intent = new Intent(LauncherActivity.this,StatsActivity.class);
                        startActivity(launcher_intent);
                    }
                }
        );

    }
}
