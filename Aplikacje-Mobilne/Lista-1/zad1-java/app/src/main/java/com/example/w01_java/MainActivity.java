package com.example.w01_java;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int r1 = 0;
    private int r2 = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score = 0;
        roll();
    }

    public void roll() {
        ((TextView)findViewById(R.id.points)).setText("Punkty: " + score);
        Random random = new Random();
        r1 = random.nextInt(10);
        r2 = random.nextInt(10);
        ((TextView)findViewById(R.id.leftClick)).setText("" + r1);
        ((TextView)findViewById(R.id.rightClick)).setText("" + r2);
    }

    public void buttonLeft(View view) {
        if(r1>=r2){
            score++;
            Toast.makeText(this, "Dobrze", Toast.LENGTH_SHORT).show();
        }
        else {
            score--;
            Toast.makeText(this, "Zle", Toast.LENGTH_SHORT).show();
        }
        roll();
    }

    public void buttonRight(View view) {
        if(r1<=r2){
            score++;
            Toast.makeText(this, "Dobrze", Toast.LENGTH_SHORT).show();
        }
        else {
            score--;
            Toast.makeText(this, "Zle", Toast.LENGTH_SHORT).show();
        }
        roll();
    }
}
