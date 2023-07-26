package com.example.quizapplicationnew;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView questionTextView,totalQuestionTextView;
    Button AnsA,AnsB,AnsC,AnsD,submit;
    int score=0;
    int totalQuestion=Questions.Questions.length;
    int currentQuestionIndex=0;
    String selectedAns="";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalQuestionTextView=(TextView)findViewById(R.id.tv1);
        questionTextView=(TextView) findViewById(R.id.tvQuestion);
        AnsA=(Button) findViewById(R.id.Ans1);
        AnsB=(Button) findViewById(R.id.Ans2);
        AnsC=(Button) findViewById(R.id.Ans3);
        AnsD=(Button) findViewById(R.id.Ans4);
        submit=(Button) findViewById(R.id.submit_btn);

        AnsA.setOnClickListener(this);
        AnsB.setOnClickListener(this);
        AnsC.setOnClickListener(this);
        AnsD.setOnClickListener(this);
        submit.setOnClickListener(this);

        totalQuestionTextView.setText("Total Questions:"+totalQuestion);
        loadnewQuestion();

    }

    @Override
    public void onClick(View view) {

        AnsA.setBackgroundColor(Color.BLUE);
        AnsB.setBackgroundColor(Color.BLUE);
        AnsC.setBackgroundColor(Color.BLUE);
        AnsD.setBackgroundColor(Color.BLUE);


        Button clickedbtn=(Button) view;
        if(clickedbtn.getId()==R.id.submit_btn){
            if(selectedAns.equals(Questions.correctAnswers[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadnewQuestion();


    } else{
        selectedAns=clickedbtn.getText().toString();
        clickedbtn.setBackgroundColor(Color.MAGENTA);
        }
    }

    void loadnewQuestion(){

        if(currentQuestionIndex==totalQuestion){
            finishQuiz();
            return;
        }
        questionTextView.setText(Questions.Questions[currentQuestionIndex]);
        AnsA.setText(Questions.Answers[currentQuestionIndex][0]);
        AnsB.setText(Questions.Answers[currentQuestionIndex][1]);
        AnsC.setText(Questions.Answers[currentQuestionIndex][2]);
        AnsD.setText(Questions.Answers[currentQuestionIndex][3]);

    }
   void finishQuiz() {
       String passStats = "";
       if (score > totalQuestion * 0.60) {
           passStats="Passed";
       } else{
           passStats="Failed";

       }
       new AlertDialog.Builder(this)
               .setTitle(passStats)
               .setMessage("Score is :"+score+" out of " +totalQuestion)
               .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz())
               .setCancelable(false)
               .show();
   }
   void restartQuiz(){
        score=0;
        currentQuestionIndex=0;
        loadnewQuestion();

   }
}