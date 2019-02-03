package com.architbhadauria.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView resultTextView;
    TextView pointsTextView;
    int numberOfQuestions=0;
    int score=0;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgainButton;
    RelativeLayout gameRelativeLayout;
    public void playAgain(View view){

        score =0;
        numberOfQuestions=0;
        timerTextView.setText("30s");
        resultTextView.setText("");
        pointsTextView.setText("0/0");
        playAgainButton.setVisibility(View.INVISIBLE);
        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your Score : " + Integer.toString(score)+ "/" + Integer.toString(numberOfQuestions));

            }
        }.start();
        generateQuestions();
    }
    public void generateQuestions(){
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();
        int incorrectAnswer;
        for(int i=0;i<4;i++){
            if(i== locationOfCorrectAnswer)
            {
                answers.add(a + b);
            }
            else{
                incorrectAnswer =rand.nextInt(41);
                while (incorrectAnswer == a+b){
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        button2.setText(Integer.toString(answers.get(0)));
        button3.setText(Integer.toString(answers.get(1)));
        button4.setText(Integer.toString(answers.get(2)));
        button5.setText(Integer.toString(answers.get(3)));

    }
    public void chooseAnswer(View view){
            if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
               score++;
               resultTextView.setText("Correct!");
            }else{
                resultTextView.setText("Incorrect");
            }
            numberOfQuestions++;
            pointsTextView.setText(Integer.toString(score)+ "/" + Integer.toString(numberOfQuestions));
            generateQuestions();
    }
    public  void start(View view){

        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        startButton.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton= (Button) findViewById(R.id.startButton);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        timerTextView =(TextView) findViewById(R.id.timerTextView);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        gameRelativeLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);


    }
}
