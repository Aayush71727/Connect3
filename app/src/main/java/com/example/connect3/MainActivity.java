package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //0 means yellow and 1 means red
    int activePlayer=0;

    boolean gameIsActive=true;

    //2 means unplayed
    int[] gameState={2,2,2,2,2,2,2,2,2};

    int[][] winningPosition={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropin(View view){

        ImageView counter=(ImageView) view;

        int tappedcounter =Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedcounter]==2 && gameIsActive) {

            gameState[tappedcounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);

                activePlayer = 1;

            } else {
                counter.setImageResource(R.drawable.red);

                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).setDuration(300);

            for(int[] winningPosition:winningPosition){

                if(gameState[winningPosition[0]]==gameState[winningPosition[1]]
                   && gameState[winningPosition[1]]==gameState[winningPosition[2]] &&
                   gameState[winningPosition[2]]!=2){
                    //someone has won!

                    gameIsActive=false;

                    String winner="red";

                    if(gameState[winningPosition[0]]==0){
                        winner="yellow";
                    }
                    TextView winnerMessage=findViewById(R.id.winnerMessage);

                    winnerMessage.setText(winner+"has won!");

                    LinearLayout layout=findViewById(R.id.playAgainLayout);

                    layout.setVisibility(View.VISIBLE);

                } else{
                    boolean gameOver=true;

                    for(int counterState:gameState){

                        if(counterState==2) gameOver=false;
                    }
                    if(gameOver){

                        TextView winnerMessage=findViewById(R.id.winnerMessage);

                        winnerMessage.setText("It's A Draw!");

                        LinearLayout layout=findViewById(R.id.playAgainLayout);

                        layout.setVisibility(View.VISIBLE);

                    }
                }
            }
        }
    }
    public void playAgain(){
        gameIsActive=true;

        LinearLayout layout=findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        activePlayer=0;

        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        GridLayout gridLayout=findViewById(R.id.grid1);

        for(int i=0;i<gridLayout.getChildCount();i++){

            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
