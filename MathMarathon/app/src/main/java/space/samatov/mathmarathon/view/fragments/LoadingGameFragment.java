package space.samatov.mathmarathon.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.samatov.mathmarathon.R;
import space.samatov.mathmarathon.model.FirebaseManager;
import space.samatov.mathmarathon.model.User;
import space.samatov.mathmarathon.model.interfaces.OnExtracUserListener;
import space.samatov.mathmarathon.model.utils.AnimationFactory;
import space.samatov.mathmarathon.model.utils.Formatter;
import space.samatov.mathmarathon.model.utils.FragmentFactory;

/**
 * Created by iskenxan on 11/6/17.
 */

public class LoadingGameFragment extends Fragment implements  ChildEventListener {



    @BindView(R.id.LoadingFragmentImageView)ImageView mLoadingImageView;
    String mOpponentUsername;
    String mCurrentUsername;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_loading_game,container,false);
        ButterKnife.bind(this,view);

        AnimationFactory.startRotatingAnimation(mLoadingImageView);
        readArgs();
        FirebaseManager.updateUserField(mCurrentUsername,FirebaseManager.IS_LOADING,true);
        FirebaseManager.addUserStatusChangedListener(this,mOpponentUsername);


        return view;
    }


    private void readArgs(){
        String opponentUsername=getArguments().getString(FragmentFactory.OPPONENT_NAME,null);
        mOpponentUsername=opponentUsername;
        mCurrentUsername=Formatter.getCurrentUsername();
    }



    @OnClick(R.id.LoadingFragmentCancelButton)
    public void onCancelButtonClicked(){
           FirebaseManager.updateUserField(mCurrentUsername,FirebaseManager.IS_LOADING,false);
        FragmentFactory.startMenuFragment((AppCompatActivity) getActivity());
    }



    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        startGameIfOpponentLoading(dataSnapshot);
    }




    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        startGameIfOpponentLoading(dataSnapshot);
    }


    private void startGameIfOpponentLoading(DataSnapshot dataSnapshot){
        if(dataSnapshot.getKey().equals(FirebaseManager.IS_LOADING)){
            boolean isLoading=dataSnapshot.getValue(Boolean.class);
            if(isLoading)
                startGame();
        }
    }



    private void startGame(){
        markUserAsInGame(mCurrentUsername);
        markUserAsInGame(mOpponentUsername);
        FragmentFactory.startGamePlayFragment((AppCompatActivity) getActivity(),mOpponentUsername);
    }


    private  void markUserAsInGame(String username){
        FirebaseManager.updateUserField(username,FirebaseManager.IS_LOADING,false);
        FirebaseManager.updateUserField(username,FirebaseManager.IN_GAME,true);
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
}
