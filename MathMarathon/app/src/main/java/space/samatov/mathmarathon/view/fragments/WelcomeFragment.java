package space.samatov.mathmarathon.view.fragments;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.samatov.mathmarathon.R;
import space.samatov.mathmarathon.model.GoogleSignInManager;
import space.samatov.mathmarathon.model.interfaces.OnSignInListener;
import space.samatov.mathmarathon.model.utils.AnimationFactory;
import space.samatov.mathmarathon.model.utils.FragmentFactory;
import space.samatov.mathmarathon.view.dialogs.LoadingDialog;

/**
 * Created by iskenxan on 10/6/17.
 */

public class WelcomeFragment extends Fragment implements OnSignInListener {

    public static final int GOOGLE_SIGN_IN_INTENT=1;


    private FirebaseAuth mAuth;
    private GoogleSignInManager mGoogleSignInManager;
    private LoadingDialog mLoadingDialog;
    @BindView(R.id.WelcomePageTitleImageView)ImageView mTitleImageView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_welcome,container,false);
        ButterKnife.bind(this,view);
        mAuth=FirebaseAuth.getInstance();
        mGoogleSignInManager=new GoogleSignInManager((AppCompatActivity) getActivity(),mAuth,this);
        AnimationFactory.startPulsatinAnimation(mTitleImageView);
        return view;
    }



    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onStop() {
        super.onStop();
    }



    @OnClick(R.id.googleSignInButton)
    public void onSignInButtonClicked(){
        Auth.GoogleSignInApi.signOut(mGoogleSignInManager.getmGoogleApiClient());
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleSignInManager.getmGoogleApiClient());
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN_INTENT);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GOOGLE_SIGN_IN_INTENT){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            mGoogleSignInManager.handleSignInResult(result);
            mLoadingDialog=LoadingDialog.displayDialog(getFragmentManager());
        }
    }


    @Override
    public void onGoogleFirebaseSignInResult(boolean result) {
        mLoadingDialog.dismiss();
        if(result)
            FragmentFactory.startMenuFragment((AppCompatActivity) getActivity());
        else
            Toast.makeText(getContext(),"Signing in your google account failed! Try again later",Toast.LENGTH_LONG).show();
    }


}
