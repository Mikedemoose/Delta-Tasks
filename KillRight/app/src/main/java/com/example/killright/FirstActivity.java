package com.example.killright;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button ;
import android.content.Intent ;
import android.widget.EditText ;
import android.widget.TextView;
import android.os.Bundle;

public class FirstActivity extends AppCompatActivity {

    private EditText Required_Age ;
    private Button Submit_Button_One ;
    private Intent newIntent ;
    private String RequiredAge ;
    private View thisView ;
    private TextView Intro_Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        thisView = this.getWindow().getDecorView();
        thisView.setBackgroundResource(R.color.lightcyan);

        Required_Age = (EditText)findViewById(R.id.Required_Age) ;
        Submit_Button_One = (Button)findViewById(R.id.Submit_Button_One) ;
        Intro_Text = (TextView)findViewById(R.id.Intro_Text);

        Submit_Button_One.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RequiredAge = Required_Age.getText().toString() ;
                        if(isInteger(RequiredAge)){
                            if((Integer.parseInt(RequiredAge)>100) || (Integer.parseInt(RequiredAge)<0)){
                                Intro_Text.setText("The age should lie in between 0 and 100.");
                            }else{
                                newIntent = new Intent(FirstActivity.this, MainActivity.class) ;
                                newIntent.putExtra("RequiredAgeString",RequiredAge) ;
                                startActivity(newIntent) ;
                            }
                        }else{
                            Intro_Text.setText("The entered age should be an integer.");
                        }
                    }
                }
        );

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
