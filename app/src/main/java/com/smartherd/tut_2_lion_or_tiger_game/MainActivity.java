package com.smartherd.tut_2_lion_or_tiger_game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum Player {
        ONE, TWO
    }

    Player currentPlayer = Player.ONE;

    Player[] playerChoices = new Player[9];

    int[][] winnerRowColumns = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    private boolean gameOver = false;

    private Button btnReset;

    private GridLayout gridLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReset = findViewById(R.id.btnReset);
        gridLayout= findViewById(R.id.gridLayout);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTheGame();
                btnReset.setVisibility(View.GONE);
            }
        });

    }


    public void imageViewIsTapped(View imageView) {
        ImageView tappedImageView = (ImageView) imageView;
        int tiTag = Integer.parseInt(tappedImageView.getTag().toString());


        if (playerChoices[tiTag] == null && gameOver == false) {

            tappedImageView.setTranslationX(-2000);

            if (currentPlayer == Player.ONE) {

                tappedImageView.setImageResource(R.drawable.tiger);
                currentPlayer = Player.TWO;
                playerChoices[tiTag] = Player.ONE;
            } else if (currentPlayer == Player.TWO) {
                tappedImageView.setImageResource(R.drawable.lion);
                currentPlayer = Player.ONE;
                playerChoices[tiTag] = Player.TWO;
            }

            tappedImageView.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(1000);
            Toast.makeText(this, tappedImageView.getTag().toString(), Toast.LENGTH_SHORT).show();


            for (int[] winnerColumns : winnerRowColumns) {
                if (playerChoices[winnerColumns[0]] == playerChoices[winnerColumns[1]] && playerChoices[winnerColumns[1]] == playerChoices[winnerColumns[2]] && playerChoices[winnerColumns[0]] != null) {
                    //Toast.makeText(this, "We have a winner", Toast.LENGTH_LONG).show();
                    gameOver=true;
                    btnReset.setVisibility(View.VISIBLE);


                    if (currentPlayer == Player.ONE) {
                        Toast.makeText(this, "Player TWO is the winner", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(this, "Player ONE is the winner", Toast.LENGTH_LONG).show();


                }
            }

        }
    }

    // RESET game function

    private void resetTheGame(){
        for (int index =0; index<gridLayout.getChildCount(); index++){

            ImageView imageView = (ImageView) gridLayout.getChildAt(index);
            imageView.setImageDrawable(null);
            playerChoices[index]=null;
            imageView.setAlpha(0f);
        }
        currentPlayer = Player.ONE;
        gameOver=false;


    }

}
