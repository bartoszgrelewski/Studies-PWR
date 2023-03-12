package com.example.lab01_zad2_java;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView playerScore;
    private TextView phoneScore;
    private ImageButton rockImage;
    private ImageButton paperImage;
    private ImageButton scissorsImage;
    private Button resetButton;

    private int phoneMove = 0;
    private int playerMove = 0;
    private int playerResult = 0;
    private int phoneResult = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerScore = findViewById(R.id.playerScore);
        phoneScore = findViewById(R.id.phoneScore);
        rockImage = findViewById(R.id.rock);
        paperImage = findViewById(R.id.paper);
        scissorsImage = findViewById(R.id.scissors);
        resetButton = findViewById(R.id.resetButton);

        paperImage.setOnClickListener(this);
        rockImage.setOnClickListener(this);
        scissorsImage.setOnClickListener(this);
        resetButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        generateRandomPhoneMove();

        switch(v.getId()) {
            case R.id.rock:
                playerMove = 1;
                break;
            case R.id.paper:
                playerMove = 2;
                break;
            case R.id.scissors:
                playerMove = 3;
                break;
            case R.id.resetButton:
                playerMove = 0;
                break;
            default:
                playerMove = -1;
                break;
        }

        if (playerMove == 0) {
            resetGame();
            Toast.makeText(this, "Reset game", Toast.LENGTH_SHORT).show();
        } else {
            decideWhoWonRound();
        }
    }

    private void decideWhoWonRound() {

        if(playerMove == phoneMove){
            Toast.makeText(this, "Draw, nobody won", Toast.LENGTH_SHORT).show();
        }
        if (playerMove == 1 && phoneMove == 3 ||
                playerMove == 2 && phoneMove == 1 ||
                playerMove == 3 && phoneMove == 2) {

            Toast.makeText(this, "Player has won", Toast.LENGTH_SHORT).show();
            playerResult++;
            playerScore.setText("Player: " + playerResult);
        } else {
            Toast.makeText(this, "Phone has won", Toast.LENGTH_SHORT).show();
            phoneResult++;
            phoneScore.setText("Phone: " + phoneResult);
        }
    }

    private void resetGame() {
        playerResult = 0;
        phoneResult = 0;
        playerScore.setText("Player: " + playerResult);
        phoneScore.setText("Phone: " + phoneResult);
    }

    private void generateRandomPhoneMove() {
        phoneMove = new Random().nextInt(3) + 1;
    }
}
