package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    //We could use boolean and it would work fine
    // but integer has the advantage that if you want extend the
    // game at some  to more than two players , so it's better to use integer


    // By creating this array we will keep track ,
    // which are empty which are yellow exc
    // 2 represents empty grid
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2,};

    // Array of winning positions
    //  First winning position    2 winning position     3 winning position
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},

            //    4 winning position     5 winning position                6 winning position
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},

            //  7 winning position       8 winning position
            {0, 4, 8}, {2, 4, 6}};


    // 0 is yellow , and 1 is red 2 represents empty grid
    int activePlayer = 0;
    boolean isGameActive = true;

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        Log.i("Tag", counter.getTag().toString());


        // When the player drops in a new counter we just need to update the game state
        // appropriately so we can keep track of each space
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        // This statement is checking that empty imageViews left && is game active
        if (gameState[tappedCounter] == 2 && isGameActive) {


            //Updating value og each player. So this array will track
            // our game state
            gameState[tappedCounter] = activePlayer;
            ;
            counter.setTranslationY(-1500);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);

                activePlayer = 1;
            } else {

                counter.setImageResource(R.drawable.red);

                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(1000);
            Log.i("Activity was clicked", "Yellow circle should drop down");


            // For loop for finding winning position
            for (int[] winningPositions : winningPositions) {

                //Checking winning position , so this logic is checking that imageView  is full
                if (gameState[winningPositions[0]] == gameState[winningPositions[1]]

                        && gameState[winningPositions[1]] == gameState[winningPositions[2]]

                        //            This part of logic is checking that is line is not empty
                        && gameState[winningPositions[0]] != 2) {

                    //If someone won this stops the game
                    isGameActive = false;

                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Yellow team wins";

                    } else {
                        winner = "Red team wins";
                    }


                    Log.i("Activity was clicked", "Yellow circle should drop down");

                   Button playAgainButton =(Button) findViewById(R.id.playAgainButton);

                    TextView winnerTextView =(TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner);
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    //Logic in this method resetting game if game is over
        public void playAgain (View view){



                //Reset button and
                Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                playAgainButton.setVisibility(View.INVISIBLE);
                winnerTextView.setVisibility(View.INVISIBLE);
          // Finding Grid layout
            GridLayout parentGridLayOut = (GridLayout) findViewById(R.id.gridLayout);

            // Iterate through all objects in gridview
            for(int i=0; i<parentGridLayOut.getChildCount(); i++) {
                ImageView counter = (ImageView) parentGridLayOut.getChildAt(i);

//                Remove the image from a imageview Android [duplicate]
                counter.setImageDrawable(null);
            }
for(int i = 0; i< gameState.length; i++){
    gameState[i] =2;
}
             activePlayer = 0;
          isGameActive = true;



  }
        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }
    }

