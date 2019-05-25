package com.example.killright;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.TextView ;
import android.widget.EditText ;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Button Submit_Button ;
    private Button Give_Up ;
    private Button Retry_Button ;
    private TextView Higher_or_Lower ;
    private TextView Number_of_Guesses ;
    private EditText Your_Guess ;
    private String Your_Guess_String ;
    private int Your_Guess_Int ;
    private TextView Wrong_Answer_Text ;
    private TextView Answer_Text ;
    private int counter = 0 ;
    private String RequiredAgeString;
    private int RequiredAgeInt ;
    private Bundle bundle ;
    private Intent newintent ;
    private View view ;
    private View Inside_View ;
    private SharedPreferences sharedPref ;
    private SharedPreferences.Editor editor;
    private String wins ;
    private String losses ;
    private int winInt ;
    private int lossInt ;
    private int flag=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bundle = getIntent().getExtras() ;
        RequiredAgeString = bundle.getString("RequiredAgeString") ;

        sharedPref = getSharedPreferences("wins and losses", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        wins = sharedPref.getString("wins","0") ;
        losses = sharedPref.getString("losses","0");
        winInt = Integer.parseInt(wins);
        lossInt = Integer.parseInt(losses);

        view = this.getWindow().getDecorView() ;
        view.setBackgroundResource(R.color.lightcyan);
        Inside_View = (View)findViewById(R.id.Inside_View) ;
        Inside_View.setBackgroundResource(R.color.lightcyan);

        Submit_Button = (Button)findViewById(R.id.Submit_Button) ;
        Give_Up = (Button)findViewById(R.id.Give_Up_Button) ;
        Higher_or_Lower = (TextView) findViewById(R.id.Higher_or_Lower) ;
        Number_of_Guesses = (TextView) findViewById(R.id.Number_of_Guesses) ;
        Retry_Button = (Button)findViewById(R.id.Retry_Button) ;
        Your_Guess = (EditText) findViewById(R.id.Your_Guess) ;
        Wrong_Answer_Text = (TextView) findViewById(R.id.Wrong_Answer_Text) ;
        Answer_Text = (TextView)findViewById(R.id.Answer_Text) ;
        Give_Up.setVisibility(View.GONE);
        Retry_Button.setVisibility(View.GONE);

        Submit_Button.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        if(isInteger(Your_Guess.getText().toString())){
                            if(Integer.parseInt(Your_Guess.getText().toString())==flag){
                               Wrong_Answer_Text.setText("Try again with a different integer.");
                               Your_Guess.getText().clear();
                            }else{
                                Your_Guess_String = Your_Guess.getText().toString();
                                Your_Guess_Int = Integer.parseInt(Your_Guess_String) ;
                                RequiredAgeInt = Integer.parseInt(RequiredAgeString) ;
                                flag = Your_Guess_Int;
                                Your_Guess.getText().clear();
                                CompareTheGuess(Your_Guess_Int,RequiredAgeInt);
                            }
                        }else{
                            Your_Guess.getText().clear();
                            Wrong_Answer_Text.setText("You should enter an integer between 0 and 100.");
                        }
                    }
                }
        );

        Give_Up.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Submit_Button.setVisibility(View.GONE);
                        Give_Up.setVisibility(View.GONE);
                        lossInt++ ;
                        editor.putString("losses",String.valueOf(lossInt));
                        editor.apply();
                        Answer_Text.setText("The Answer was : "+ RequiredAgeString);
                        Higher_or_Lower.setText(" ") ;
                        Wrong_Answer_Text.setText(" ");
                        Retry_Button.setVisibility(View.VISIBLE);
                    }
                }
        );

        Retry_Button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        newintent = new Intent(MainActivity.this,LauncherActivity.class) ;
                        startActivity(newintent) ;
                    }
                }
        );

    }

    public void CompareTheGuess(int Guess , int Answer) {
        if(Guess == Answer){
            flag = -1;
            view.setBackgroundResource(R.color.GreenRight);
            Wrong_Answer_Text.setText("Yay! That's right.");
            winInt++;
            editor.putString("wins",String.valueOf(winInt));
            editor.apply();
            Higher_or_Lower.setText(" ");
            Submit_Button.setVisibility(View.GONE);
            Give_Up.setVisibility(View.GONE);
            Higher_or_Lower.setText(" ");
            Answer_Text.setText("Congratulations");
            Retry_Button.setVisibility(View.VISIBLE);
        }else{
            if((Guess-Answer >= 50) || (Answer-Guess >=50)){
                view.setBackgroundResource(R.color.Red);
            }else if((Guess-Answer >= 30) || (Answer-Guess >=30)){
                view.setBackgroundResource(R.color.Brown);
            }else if((Guess-Answer >= 20) || (Answer-Guess >=20)){
                view.setBackgroundResource(R.color.GreenishThingy);
            }else if((Guess-Answer >= 10) || (Answer-Guess >=10)){
                view.setBackgroundResource(R.color.AlmostGreen);
            }else{
                view.setBackgroundResource(R.color.Green);
            }
            counter++ ;
            if(counter == 1){
                Give_Up.setVisibility(View.VISIBLE);
            }
            if(counter == 6){
                Submit_Button.setVisibility(View.GONE) ;
                Give_Up.setVisibility(View.GONE);
                lossInt++ ;
                editor.putString("losses",String.valueOf(lossInt));
                editor.apply();
                Retry_Button.setVisibility(View.VISIBLE);
                Answer_Text.setText("The Answer was : "+ RequiredAgeString);
                Higher_or_Lower.setText("You have reached the maximum number of guesses.");
            }
            Wrong_Answer_Text.setText("Oops! Wrong answer. Try Again.");
            Number_of_Guesses.setText("Number of Incorrect Guesses : " + String.valueOf(counter));
            if(counter!=6){
                if(Guess > Answer){
                    Higher_or_Lower.setText("The answer is less than that.");
                }else{
                    Higher_or_Lower.setText("The answer is greater than that.");
                }
            }
        }
    }

    public static boolean isInteger(String s){
        try{
            Integer.parseInt(s);
        }catch (NumberFormatException e){
            return false;
        }catch (NullPointerException e){
            return false;
        }
        return true;
    }
}
