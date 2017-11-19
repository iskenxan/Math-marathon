package space.samatov.mathmarathon.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.samatov.mathmarathon.R;
import space.samatov.mathmarathon.model.FirebaseManager;
import space.samatov.mathmarathon.model.User;
import space.samatov.mathmarathon.model.interfaces.OnExtracUserListener;
import space.samatov.mathmarathon.model.utils.Formatter;
import space.samatov.mathmarathon.model.utils.FragmentFactory;

/**
 * Created by iskenxan on 11/17/17.
 */

public class GameResultFragment extends Fragment implements OnExtracUserListener {

    //TODO: Allow only 25 questions. There are some bugs when users start game. For example sometimes the user who starts game first gets exception

    @BindView(R.id.GameResultTextView)TextView mResultTextView;
    @BindView(R.id.GameResultYourScoreTextView)TextView mYourScoreTextView;
    @BindView(R.id.GameResultOpponentsScoreTextView)TextView mOpponentScoreTextView;
    @BindView(R.id.GameResultImageView)ImageView mResultImageView;

    private String mOpponentUsername;
    private User  mCurrentUser;
    private User mOpponentUser;
    boolean mIsWinner=false;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_game_result,container,false);
        ButterKnife.bind(this,view);
        readArgs();
        getUserData();

        return view;
    }



    private void readArgs(){
        mOpponentUsername=getArguments().getString(FragmentFactory.OPPONENT_NAME);
    }

    private void getUserData(){
        FirebaseManager.extracCurrentUserData(this);
        FirebaseManager.extractUserData(this,mOpponentUsername);
    }

    @OnClick(R.id.GameResultContinueButton)
    public void onContinueButtonClicked(){
        updateUserData();
        FragmentFactory.startMenuFragment((AppCompatActivity) getActivity());
    }



    @Override
    public void onUserDataExtracted(User user) {
        if(user.getEmail().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail()))
            mCurrentUser=user;
        else
            mOpponentUser=user;

        findAndDisplayWinner();
    }


    private void findAndDisplayWinner(){
        if(mCurrentUser!=null&&mOpponentUser!=null){
            int userScore=mCurrentUser.getInGameScore();
            int opponentScore=mOpponentUser.getInGameScore();

            if(userScore>opponentScore){
                onCurrentUserIsWinner();
                mIsWinner=true;
            }
            else if(userScore<opponentScore)
                onOpponentWinner();
            else
                onTie();

            displayUserScores();
        }
    }


    private void onCurrentUserIsWinner(){
        mResultTextView.setText("WINNER!");
        mResultImageView.setImageResource(R.drawable.cup);

    }

    private void onOpponentWinner(){
        mResultTextView.setText("YOU LOST!");
        mResultImageView.setImageResource(R.drawable.sad_face);
    }

    private void onTie(){
        mResultTextView.setText("ITS A TIE!");
        mResultImageView.setImageResource(R.drawable.friend_icon);
    }

    private void displayUserScores(){
        mYourScoreTextView.setText("Your score: "+mCurrentUser.getInGameScore());
        mOpponentScoreTextView.setText(mOpponentUsername+"'s score:"+mOpponentUser.getInGameScore());
    }


    private void updateUserData(){
        if(mIsWinner){
            int gameScore=mCurrentUser.getInGameScore();
            int newOverallScore=gameScore+mCurrentUser.getOverallScore();
            mCurrentUser.setOverallScore(newOverallScore);
            mCurrentUser.setWins(mCurrentUser.getWins()+1);
        }
        else
            mCurrentUser.setLoses(mCurrentUser.getLoses()+1);

        mCurrentUser.setInGame(false);
        FirebaseManager.setUserRecord(mCurrentUser,Formatter.getCurrentUsername());
    }


}
