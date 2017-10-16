package space.samatov.mathmarathon.view.fragments;

import android.content.Intent;
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
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.samatov.mathmarathon.R;
import space.samatov.mathmarathon.model.FirebaseManager;
import space.samatov.mathmarathon.model.GoogleSignInManager;
import space.samatov.mathmarathon.model.User;
import space.samatov.mathmarathon.model.interfaces.OnExtracUserListener;
import space.samatov.mathmarathon.model.interfaces.OnSignInListener;
import space.samatov.mathmarathon.model.utils.AnimationFactory;
import space.samatov.mathmarathon.model.utils.FragmentFactory;
import space.samatov.mathmarathon.model.utils.IntentFactory;
import space.samatov.mathmarathon.view.dialogs.LoadingDialog;

/**
 * Created by iskenxan on 10/6/17.
 */

public class WelcomeFragment extends Fragment implements OnSignInListener, OnExtracUserListener {



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
        IntentFactory.startGoogleSignInIntent(mGoogleSignInManager,this);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IntentFactory.GOOGLE_SIGN_IN_INTENT){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            mGoogleSignInManager.handleSignInResult(result);
            if(result.isSuccess())
                mLoadingDialog=LoadingDialog.displayDialog(getFragmentManager());
        }
    }


    @Override
    public void onGoogleFirebaseSignInResult(boolean result) {
        if(result)
            FirebaseManager.extracUserData(this);
        else
           displayFailedToast();
    }



    @Override
    public void onUserDataExtracted(User user) {
        mLoadingDialog.dismiss();
        if(user==null)
            FirebaseManager.createNewUserRecord();
        FragmentFactory.startMenuFragment((AppCompatActivity) getActivity());
    }


    private void displayFailedToast(){
        if(mLoadingDialog!=null)
            mLoadingDialog.dismiss();
        Toast.makeText(getContext(),"Signing in your google account failed! Please try again.",Toast.LENGTH_SHORT).show();
    }

}
