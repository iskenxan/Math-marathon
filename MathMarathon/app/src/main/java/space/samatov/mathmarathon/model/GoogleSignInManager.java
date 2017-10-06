package space.samatov.mathmarathon.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by iskenxan on 10/6/17.
 */

public class GoogleSignInManager implements FirebaseAuth.AuthStateListener {

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user=firebaseAuth.getCurrentUser();
        if(user!=null){
            Log.d("LOGIN","Success!");
        }
        else
            Log.d("LOGIN","Failed");
    }
}
