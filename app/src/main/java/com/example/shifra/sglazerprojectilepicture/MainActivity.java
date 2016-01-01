package com.example.shifra.sglazerprojectilepicture;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private Button calculate;
    private EditText angle;
    private EditText time;
    private EditText velocity;
    private TextView calculated;
    private double angleValue;
    private double timeValue;
    private double velocityValue;
    private ImageView imageView;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences= this.getSharedPreferences("DEFAULT",MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        Picasso.with(this).load("http://images.tutorcircle.com/cms/images/83/projectile-motion111.PNG").into(imageView);
        angle= (EditText) findViewById(R.id.angle);
        time= (EditText) findViewById(R.id.time);
        velocity= (EditText) findViewById(R.id.velocity);
        calculate= (Button) findViewById(R.id.calculate);
        calculated= (TextView) findViewById(R.id.calculated);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculateAnswer();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        angle.setText(preferences.getString("ANGLE",""));
        velocity.setText(preferences.getString("VELOCITY",""));
        time.setText(preferences.getString("TIME",""));
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor= preferences.edit();
        editor.putString("ANGLE",angle.getText().toString());
        editor.putString("VELOCITY",velocity.getText().toString());
        editor.putString("TIME",time.getText().toString());

        editor.apply();
    }

    private void calculateAnswer() {
        Intent intent= new Intent(this,AnswerActivity.class);
        angleValue=Double.valueOf(angle.getText().toString());
        timeValue=Double.valueOf(time.getText().toString());
        velocityValue=Double.valueOf(velocity.getText().toString());

        intent.putExtra("ANGLE", angleValue);
        intent.putExtra("VELOCITY", velocityValue);
        intent.putExtra("TIME",timeValue);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
