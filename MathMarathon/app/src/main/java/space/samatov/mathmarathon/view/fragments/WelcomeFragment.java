package space.samatov.mathmarathon.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.ButterKnife;
import space.samatov.mathmarathon.R;
import space.samatov.mathmarathon.model.GoogleSignInManager;

/**
 * Created by iskenxan on 10/6/17.
 */

public class WelcomeFragment extends Fragment {

    //TODO: Integrate Google Sign in :   https://developers.google.com/identity/sign-in/android/sign-in
    //TODO: Authenticate using Google Sign in : https://firebase.google.com/docs/auth/android/google-signin?utm_source=studio
    private FirebaseAuth mAuth;
    private GoogleSignInManager mGoogleSignInManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_welcome,container,false);
        ButterKnife.bind(this,view);
        mAuth=FirebaseAuth.getInstance();
        mGoogleSignInManager=new GoogleSignInManager();

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mGoogleSignInManager);
    }


    @Override
    public void onStop() {
        super.onStop();
        if(mGoogleSignInManager!=null)
            mAuth.removeAuthStateListener(mGoogleSignInManager);
    }
}
