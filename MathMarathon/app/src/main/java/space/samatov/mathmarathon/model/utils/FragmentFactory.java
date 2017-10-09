package space.samatov.mathmarathon.model.utils;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import space.samatov.mathmarathon.R;
import space.samatov.mathmarathon.view.fragments.WelcomeFragment;

/**
 * Created by iskenxan on 10/8/17.
 */

public class FragmentFactory {


    public static  final void startWelcomePageFragment(AppCompatActivity activity){
        FragmentManager fragmentManager=activity.getSupportFragmentManager();
        WelcomeFragment welcomeFragment=new WelcomeFragment();

        fragmentManager.beginTransaction().replace(R.id.mainPlaceholder,welcomeFragment).commit();
    }
}
