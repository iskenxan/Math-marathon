package space.samatov.mathmarathon.model.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import space.samatov.mathmarathon.R;
import space.samatov.mathmarathon.view.fragments.FriendRequestFragment;
import space.samatov.mathmarathon.view.fragments.MenuFragment;
import space.samatov.mathmarathon.view.fragments.MyProfileFragment;
import space.samatov.mathmarathon.view.fragments.WelcomeFragment;

/**
 * Created by iskenxan on 10/8/17.
 */

public class FragmentFactory {


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


    private static final void startMainActivityFragment(AppCompatActivity activity, Fragment fragment){
        FragmentManager fragmentManager=activity.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.mainPlaceholder,fragment).commit();
    }



}
