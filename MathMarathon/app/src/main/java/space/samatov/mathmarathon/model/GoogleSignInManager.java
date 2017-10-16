package space.samatov.mathmarathon.model;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import space.samatov.mathmarathon.model.interfaces.OnSignInListener;
import space.samatov.mathmarathon.view.dialogs.LoadingDialog;


/**
 * Created by iskenxan on 10/6/17.
 */

public class GoogleSignInManager implements  GoogleApiClient.OnConnectionFailedListener,OnCompleteListener<AuthResult> {

    GoogleApiClient mGoogleApiClient;
    FirebaseAuth mAuth;
    AppCompatActivity mActivity;
    OnSignInListener mListener;


    public GoogleSignInManager(AppCompatActivity activity,FirebaseAuth auth,OnSignInListener listener){
        mAuth=auth;
        mActivity=activity;
        mListener=listener;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("794681036748-9n06mt73upanhdjtlc76cqpro6d3c7oo.apps.googleusercontent.com")
                .requestEmail().build();

        mGoogleApiClient = new GoogleApiClient.Builder(mActivity)
                .enableAutoManage(mActivity, this).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
    }



    public void handleSignInResult(GoogleSignInResult result){
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
            mAuth.signInWithCredential(credential).addOnCompleteListener(mActivity, this);

        }
        else
            mListener.onGoogleFirebaseSignInResult(false);
    }



    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful())
            mListener.onGoogleFirebaseSignInResult(true);
        else
            mListener.onGoogleFirebaseSignInResult(false);
    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public GoogleApiClient getmGoogleApiClient(){
        return mGoogleApiClient;
    }


}
