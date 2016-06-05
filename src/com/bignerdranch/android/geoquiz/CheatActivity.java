package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {
	
	public static final String EXTRA_ANSWER_IS_TRUE = 
			"com.binerdranch.android.geoquiz.answer_is_true";
	public static final String EXTRA_ANSWER_SHOWN = 
			"com.bignerdranch.android.geoquiz.answer_shown";
	
	private static final String ANSWER_SHOWN = "AnswerHasShown";
	
	private boolean mAnswerIsTrue;
	private boolean mAnswerIsShown;
	
	private TextView mAnswerTextView;
	private Button mShowAnswer;
	private TextView mApiLvlTextView;
	
	private void setAnswerShownResult(boolean isAnswerShown) {
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
		setResult(RESULT_OK, data);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);
		
		if(savedInstanceState != null) {
			mAnswerIsShown = savedInstanceState.getBoolean(ANSWER_SHOWN, false);
		}
		setAnswerShownResult(mAnswerIsShown);
		
		mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
		mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
		
		mApiLvlTextView = (TextView)findViewById(R.id.apiLvlTextView);
		mApiLvlTextView.setText("API Level " + Build.VERSION.SDK_INT);
		
		mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
		mShowAnswer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mAnswerIsTrue) {
					mAnswerTextView.setText(R.string.true_button);
				} else {
					mAnswerTextView.setText(R.string.false_button);
				}
				setAnswerShownResult(true);
				mAnswerIsShown = true;
			}
		});
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putBoolean(ANSWER_SHOWN, mAnswerIsShown);
	}

}
