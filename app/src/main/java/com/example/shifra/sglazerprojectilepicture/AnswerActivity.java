package com.example.shifra.sglazerprojectilepicture;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


/**
 * Created by student1 on 10/15/2015.
 */
public class AnswerActivity extends AppCompatActivity{

    private TextView answer;
    private double angle;
    private double velocity;
    private double time;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        answer= (TextView) findViewById(R.id.answered);
        Intent intent= getIntent();
        angle = getIntent().getDoubleExtra("ANGLE",0.0);
        velocity = getIntent().getDoubleExtra("VELOCITY",0.0);
        time = getIntent().getDoubleExtra("TIME",0.0);
        answer.setText(getX()+" , "+ getY());
    }

    public double getY(){
        double radians= Math.toRadians(angle);
        return (Math.cos(radians)* time*
                velocity)-
                (.5 * 9.8 * Math.pow(time,2));
    }
    public double getX(){
        double radians= Math.toRadians(angle);
        return Math.sin(radians)* time* velocity;
    }
}
