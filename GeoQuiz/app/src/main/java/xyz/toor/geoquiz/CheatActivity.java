package xyz.toor.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String TAG = "CheatActivity";
    private static final String EXTRA_ANSWER_IS_TRUE="xyz.toor.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN="xyz.toor.geoquiz.answer_shown";

    private static final String KEY_ISCHEATED="isCheat";
    private static final String KEY_ANSWER_TEXT ="answer_text";

    private boolean mAnswerIsTrue;
    private Button mShowAnswerButton;
    private TextView mAnswerTextView;

    private boolean mIsCheater;

    public static Intent newIntent(Context packageContext,boolean answerIsTrue){
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE,answerIsTrue);
        return intent;
    }

    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN,false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
        if (savedInstanceState !=null){
            mIsCheater = savedInstanceState.getBoolean(KEY_ISCHEATED,false);
            if(mIsCheater){
                setAnswerShownResult(true);
                mAnswerTextView.setText(savedInstanceState.getString(KEY_ANSWER_TEXT));
            }
        }

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);

        mShowAnswerButton = (Button) findViewById(R.id.show_answer_button);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAnswerIsTrue){
                    mAnswerTextView.setText(R.string.true_button);
                }else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                mIsCheater = true;
                setAnswerShownResult(true);
            }


        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(KEY_ISCHEATED,mIsCheater);
        savedInstanceState.putString(KEY_ANSWER_TEXT,mAnswerTextView.getText().toString());
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN,isAnswerShown);
        setResult(RESULT_OK,data);
    }
}
