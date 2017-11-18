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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.samatov.mathmarathon.R;
import space.samatov.mathmarathon.model.FirebaseManager;
import space.samatov.mathmarathon.model.Question;
import space.samatov.mathmarathon.model.QuestionGenerator;
import space.samatov.mathmarathon.model.utils.AnimationFactory;
import space.samatov.mathmarathon.model.utils.Formatter;
import space.samatov.mathmarathon.model.utils.FragmentFactory;

/**
 * Created by iskenxan on 11/8/17.
 */

public class GamePlayFragment extends Fragment implements ChildEventListener {


    @BindView(R.id.TimerTextView)TextView mTimerTextView;
    @BindView(R.id.YourScoreTextView)TextView mYourScoreTextView;
    @BindView(R.id.OpponentScoreTextView)TextView mOpponentScoreTextView;
    @BindView(R.id.QuestionTextView)TextView mQuestionTextView;
    @BindView(R.id.firstAnswerButton)Button mFirstAnswerButton;
    @BindView(R.id.secondAnswerButton)Button mSecondAnswerButton;
    @BindView(R.id.thirdAnswerButton)Button mThirdAnswerButton;
    @BindView(R.id.fourthAnswerButton)Button mFourthAnswerButton;

    CountDownTimer mTimer;
    QuestionGenerator mQuestionGenerator;
    Question mCurrentQuestion;
    String mOpponentUsername;
    boolean mIsPlaying =false;
    int mTimerValue=60;

    int mUserScore=0;
    int mOpponentScore=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_gameplay,container,false);
        ButterKnife.bind(this,view);
        mQuestionGenerator=new QuestionGenerator(15,100);

        readArgs();
        FirebaseManager.addUserStatusChangedListener(this,mOpponentUsername);
        startTimer();
        generateNewQuestion();


        return view;
    }



    private void readArgs(){
        mOpponentUsername=getArguments().getString(FragmentFactory.OPPONENT_NAME);
    }



    private void generateNewQuestion(){
        Question question=mQuestionGenerator.generateRandomQuestion();
        mCurrentQuestion =question;
        mQuestionTextView.setText(mCurrentQuestion.getQuestion());
        mFirstAnswerButton.setText(mCurrentQuestion.getAnswers().get(0));
        mSecondAnswerButton.setText(mCurrentQuestion.getAnswers().get(1));
        mThirdAnswerButton.setText(mCurrentQuestion.getAnswers().get(2));
        mFourthAnswerButton.setText(mCurrentQuestion.getAnswers().get(3));
    }



    private void startTimer(){
        mTimer=new CountDownTimer(120000,1000) {
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
        FirebaseManager.updateUserField(Formatter.getCurrentUsername(),FirebaseManager.IN_GAME,true);
    }



    @OnClick(R.id.firstAnswerButton)
    public void onFirstButtonClicked(){
        checkAnswerAndAnimateButton(mFirstAnswerButton,0);
    }

    @OnClick(R.id.secondAnswerButton)
    public void onSecondButtonClicked(){
        checkAnswerAndAnimateButton(mSecondAnswerButton,1);
    }

    @OnClick(R.id.thirdAnswerButton)
    public void onThirdButtonClicked(){
        checkAnswerAndAnimateButton(mThirdAnswerButton,2);
    }

    @OnClick(R.id.fourthAnswerButton)
    public void onFourthButtonClicked(){
        checkAnswerAndAnimateButton(mFourthAnswerButton,3);
    }


    private void checkAnswerAndAnimateButton(Button button,int buttonIndex){
        if(mCurrentQuestion.getRightAnswerIndex()==buttonIndex){
            addScoreToUser();
            AnimationFactory.startRightAnswerButtonAnimation(button);
        }
        else
            AnimationFactory.startWrongAnswerButtonAnimation(button);

        generateNewQuestion();
    }


    private void addScoreToUser(){
        mUserScore+=1;
        mYourScoreTextView.setText("Your score: "+mUserScore);
        FirebaseManager.updateUserField(Formatter.getCurrentUsername(),FirebaseManager.IN_GAME_SCORE,mUserScore);
    }



    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        updateOpponentScore(dataSnapshot);
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        updateOpponentScore(dataSnapshot);
    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }


    private void updateOpponentScore(DataSnapshot dataSnapshot){
        if(dataSnapshot.getKey().equals(FirebaseManager.IN_GAME_SCORE)){
            int opponentScore=dataSnapshot.getValue(Integer.class);
            mOpponentScore=opponentScore;
            mOpponentScoreTextView.setText(mOpponentUsername+"'s score: "+opponentScore);
        }
    }
}
