package com.example.howwi.geoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//this is a mess
public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;


    //Array Containing all of the Questions
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;

    ///instance update question code
    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    //determining if the answer is true or false base on if which button has been pressed
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId;

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();

    }


    /// pushing data to activity quiz
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
            mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

            // False button code
            mFalseButton = (Button) findViewById(R.id.false_button);
            mFalseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            // True button code
            mTrueButton = (Button) findViewById(R.id.true_button);
            mTrueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });

            // Next Button listener
            mNextButton = (Button) findViewById(R.id.next_Button);
            mNextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                    updateQuestion();
                }
            });
            //previous button code
            mPrevButton = (Button) findViewById(R.id.prev_button);
            mPrevButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCurrentIndex--;
                    if (mCurrentIndex < 0)
                        mCurrentIndex = mQuestionBank.length - 1;
                    updateQuestion();
                }
            });
            //cheat button code. it starts the activity when the button is pressed, and also uses an intent and a extra to pass data between the two activities

            mCheatButton = (Button) findViewById(R.id.cheat_button);
            mCheatButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //starting CheatActivity
                    boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                    Intent intent = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                    startActivity(intent);
                }
            });

            updateQuestion();
        }

     @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
     }

}

