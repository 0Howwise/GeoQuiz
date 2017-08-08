package com.example.howwi.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE =
            "com.example.howwi.answer_true_true";

    private boolean mAnswerIsTrue;
    private Button mShowAnswerButton;
    private TextView mAnswerTextView;

    //adding m a extra to an intent
    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

    //retrieving the data from the extra
    mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);

    //text view for showing the answer
    mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);

    // showing answer in text view when you get the data or answer
    mShowAnswerButton = (Button) findViewById(R.id.show_answer_button);
    mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mAnswerIsTrue) {
               mAnswerTextView.setText(R.string.true_button);
            } else {
                mAnswerTextView.setText(R.string.false_button);
            }
        }}
    );

    }
}
