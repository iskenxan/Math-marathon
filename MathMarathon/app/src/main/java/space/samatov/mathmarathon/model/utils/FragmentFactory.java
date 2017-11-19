package space.samatov.mathmarathon.model.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import space.samatov.mathmarathon.R;
import space.samatov.mathmarathon.view.fragments.FriendRequestFragment;
import space.samatov.mathmarathon.view.fragments.GamePlayFragment;
import space.samatov.mathmarathon.view.fragments.GameResultFragment;
import space.samatov.mathmarathon.view.fragments.LoadingGameFragment;
import space.samatov.mathmarathon.view.fragments.MenuFragment;
import space.samatov.mathmarathon.view.fragments.MyProfileFragment;
import space.samatov.mathmarathon.view.fragments.StartGameFragment;
import space.samatov.mathmarathon.view.fragments.WelcomeFragment;

/**
 * Created by iskenxan on 10/8/17.
 */

public class FragmentFactory {

    public static final String OPPONENT_NAME="opponent_name";


    public static  final void startWelcomePageFragment(AppCompatActivity activity){
        WelcomeFragment welcomeFragment=new WelcomeFragment();
        startMainActivityFragment(activity,welcomeFragment);
    }


    public static final void startMenuFragment(AppCompatActivity activity){
        MenuFragment menuFragment=new MenuFragment();
        startMainActivityFragment(activity,menuFragment);
    }


    public static final void startMyProfileFragment(AppCompatActivity activity){
        MyProfileFragment myProfileFragment=new MyProfileFragment();
        startMainActivityFragment(activity,myProfileFragment);
    }


    public static final void startFriendRequestFragment(AppCompatActivity activity){
        FriendRequestFragment fragment=new FriendRequestFragment();
        startMainActivityFragment(activity,fragment);
    }

    public static final void startStartGameFragment(AppCompatActivity activity){
        StartGameFragment fragment=new StartGameFragment();
        startMainActivityFragment(activity,fragment);
    }


    public static final void startLoadingGameFragment(AppCompatActivity activity,String opponentUsername){
        LoadingGameFragment fragment=new LoadingGameFragment();
        fragment.setArguments(addOpponentsNameToArgs(opponentUsername));

        startMainActivityFragment(activity,fragment);
    }


    public static final void startGamePlayFragment(AppCompatActivity activity,String opponentUsername){
        GamePlayFragment fragment=new GamePlayFragment();
        fragment.setArguments(addOpponentsNameToArgs(opponentUsername));
        startMainActivityFragment(activity,fragment);
    }


    public static final void startGameResultFragment(AppCompatActivity activity,String opponentUsername){
        GameResultFragment fragment=new GameResultFragment();
        fragment.setArguments(addOpponentsNameToArgs(opponentUsername));
        startMainActivityFragment(activity,fragment);
    }


    private static Bundle addOpponentsNameToArgs(String opponentName){
        Bundle args=new Bundle();
        args.putString(OPPONENT_NAME,opponentName);

        return args;
    }


    private static final void startMainActivityFragment(AppCompatActivity activity, Fragment fragment){
        FragmentManager fragmentManager=activity.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.mainPlaceholder,fragment).commit();
    }

}
