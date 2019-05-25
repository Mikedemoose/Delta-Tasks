package com.example.killright;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.content.Intent;

public class StatsActivity extends AppCompatActivity {

    private TextView Win_Number;
    private TextView Loss_Number;
    private SharedPreferences sharedPref1 ;
    private SharedPreferences.Editor editor ;
    private Button Back_Button ;
    private Intent backIntent;
    private Button Reset_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        Win_Number = (TextView) findViewById(R.id.Win_Number);
        Loss_Number = (TextView) findViewById(R.id.Loss_Number);
        Reset_Button = (Button) findViewById(R.id.Reset_Button);
        Back_Button = (Button) findViewById(R.id.Back_Button);
        sharedPref1 = getSharedPreferences("wins and losses", Context.MODE_PRIVATE);
        editor = sharedPref1.edit();

        Win_Number.setText(sharedPref1.getString("wins","0"));
        Loss_Number.setText(sharedPref1.getString("losses","0"));

        Back_Button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        backIntent = new Intent(StatsActivity.this,LauncherActivity.class);
                        startActivity(backIntent);
                    }
                }
        );

        Reset_Button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editor.putString("wins","0");
                        editor.putString("losses","0");
                        editor.apply();
                        Win_Number.setText("0");
                        Loss_Number.setText("0");
                    }
                }
        );

    }
}
