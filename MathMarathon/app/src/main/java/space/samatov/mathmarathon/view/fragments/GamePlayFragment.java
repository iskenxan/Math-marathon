package space.samatov.mathmarathon.view.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.samatov.mathmarathon.R;
import space.samatov.mathmarathon.model.QuestionGenerator;

/**
 * Created by iskenxan on 11/8/17.
 */

public class GamePlayFragment extends Fragment {


    @BindView(R.id.TimerTextView)TextView mTimerTextView;
    @BindView(R.id.YourScoreTextView)TextView mYourScoreTextView;
    @BindView(R.id.OpponentScoreTextView)TextView mOpponentScoreTextView;
    @BindView(R.id.QuestionTextView)TextView mQuestionTextView;
    @BindView(R.id.firstAnswerButton)Button mFirstAnswer;
    @BindView(R.id.secondAnswerButton)Button mSecondAnswer;
    @BindView(R.id.thirdAnswerButton)Button mThirdAnswer;
    @BindView(R.id.fourthAnswerButton)Button mFourthAnswer;

    CountDownTimer mTimer;
    QuestionGenerator mQuestionGenerator;
    boolean mIsPlaying =false;
    int mTimerValue=60;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_gameplay,container,false);
        ButterKnife.bind(this,view);

        mQuestionGenerator=new QuestionGenerator(15,100);

        startTimer();


        return view;
    }



    private void startTimer(){
        mTimer=new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long l) {
                mTimerValue--;
                mTimerTextView.setText(mTimerValue+"");
            }

            @Override
            public void onFinish() {
                Toast.makeText(getContext(),"Game over!",Toast.LENGTH_SHORT).show();
            }
        };
        mTimer.start();
        mIsPlaying =true;
    }


    @OnClick(R.id.firstAnswerButton)
    public void onFirstButtonClicked(){

    }

    @OnClick(R.id.secondAnswerButton)
    public void onSecondButtonClicked(){

    }

    @OnClick(R.id.thirdAnswerButton)
    public void onThirdButtonClicked(){

    }

    @OnClick(R.id.fourthAnswerButton)
    public void onFourthButtonClicked(){

    }


}
