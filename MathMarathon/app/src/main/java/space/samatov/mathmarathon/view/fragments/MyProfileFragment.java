package space.samatov.mathmarathon.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.samatov.mathmarathon.R;
import space.samatov.mathmarathon.model.utils.FragmentFactory;

/**
 * Created by iskenxan on 10/11/17.
 */

public class MyProfileFragment extends Fragment {

    @BindView(R.id.MyProfileImageView)ImageView mProfileImageView;
    @BindView(R.id.MyProfileName)TextView mNameTextView;
    @BindView(R.id.MyProfileOverallScore)TextView mOverallScoreTextView;
    @BindView(R.id.MyProfileWins)TextView mWinsTextView;
    @BindView(R.id.MyProfileLoses)TextView mLosesTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my_profile,container,false);
        ButterKnife.bind(this,view);
        populateViews();

        return view;
    }

    //TODO:Use formatted user email as a key instead of UID(to make sending friend requests easier)
    //TODO:Add ability to save photos on the database storage after compressing them

    private void populateViews(){
        mNameTextView.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
    }


    @OnClick(R.id.MyProfileUploadButton)
    public void onUploadButtonClicked(){

    }

    @OnClick(R.id.MyProfileBackButton)
    public void onBackButtonClicked(){
        FragmentFactory.startMenuFragment((AppCompatActivity) getActivity());
    }


}
