package space.samatov.mathmarathon.view.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.samatov.mathmarathon.R;
import space.samatov.mathmarathon.model.FirebaseManager;
import space.samatov.mathmarathon.model.MySharedPreferenceManager;
import space.samatov.mathmarathon.model.User;
import space.samatov.mathmarathon.model.interfaces.OnExtracUserListener;
import space.samatov.mathmarathon.model.interfaces.OnImageUploadListener;
import space.samatov.mathmarathon.model.utils.AnimationFactory;
import space.samatov.mathmarathon.model.utils.Formatter;
import space.samatov.mathmarathon.model.utils.FragmentFactory;
import space.samatov.mathmarathon.model.utils.IntentFactory;
import space.samatov.mathmarathon.view.dialogs.LoadingDialog;

import static android.app.Activity.RESULT_OK;
import static android.view.View.GONE;

/**
 * Created by iskenxan on 10/11/17.
 */

public class MyProfileFragment extends Fragment implements OnExtracUserListener, OnImageUploadListener, Callback {


    @BindView(R.id.MyProfileImageView)ImageView mProfileImageView;
    @BindView(R.id.MyProfileName)TextView mUsername;
    @BindView(R.id.MyProfileOverallScore)TextView mOverallScoreTextView;
    @BindView(R.id.MyProfileWins)TextView mWinsTextView;
    @BindView(R.id.MyProfileLoses)TextView mLosesTextView;
    @BindView(R.id.ProfileImagePlaceHolder)ImageView mProfilePlaceHolder;
    LoadingDialog mLoadingDialog;
    User mUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my_profile,container,false);
        ButterKnife.bind(this,view);
        FirebaseManager.extracCurrentUserData(this);
        mLoadingDialog=LoadingDialog.displayDialog(getFragmentManager());
        AnimationFactory.startRotatingAnimation(mProfilePlaceHolder);
        return view;
    }



    @Override
    public void onUserDataExtracted(User user) {
        mLoadingDialog.dismiss();
        if(user!=null){
            mUser=user;
            populateViews();
        }
    }



    private void populateViews(){
        loadPicture();
        String username= Formatter.formatStringForFirebase(mUser.getEmail());
        mUsername.setText(username);
        mOverallScoreTextView.setText(mUser.getOverallScore()+"");
        mWinsTextView.setText(mUser.getWins()+"");
        mLosesTextView.setText(mUser.getLoses()+"");
    }


    private void loadPicture(){
        mProfileImageView.setVisibility(View.VISIBLE);
        if(mUser.getPhotoUrl()!=null){
            Picasso.with(getContext()).load(mUser.getPhotoUrl()).placeholder(R.color.darkNavyColor).
                    memoryPolicy(MemoryPolicy.NO_CACHE).into(mProfileImageView,this);
            mProfilePlaceHolder.setVisibility(View.VISIBLE);
        }
        else
            mProfilePlaceHolder.setVisibility(GONE);
    }


    @Override
    public void onSuccess() {
        mProfilePlaceHolder.setVisibility(GONE);
    }

    @Override
    public void onError() {
        mProfilePlaceHolder.setVisibility(GONE);
    }


    @OnClick(R.id.MyProfileUploadButton)
    public void onUploadButtonClicked(){
        IntentFactory.dispatchTakePictureIntent(this);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IntentFactory.REQUEST_IMAGE_CAPTURE){
            handleCameraIntent(resultCode,data);
        }
    }



    private void handleCameraIntent(int resultCode,Intent data){
        if(resultCode==RESULT_OK){
           saveImageInFirebaseStorage(data);
        }
        else
            Toast.makeText(getContext(),"Error while using a camera, please try again",Toast.LENGTH_LONG).show();
    }


    private void saveImageInFirebaseStorage(Intent data){
        mLoadingDialog=LoadingDialog.displayDialog(getFragmentManager());
        Bundle extras = data.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        FirebaseManager.saveProfileImage(imageBitmap,this,mUser);
    }


    @OnClick(R.id.MyProfileBackButton)
    public void onBackButtonClicked(){
        FragmentFactory.startMenuFragment((AppCompatActivity) getActivity());
    }



    @Override
    public void onImageUploadResult(Uri uri) {
        mLoadingDialog.dismiss();
        if(uri!=null){
          saveImageUrlAndDisplay(uri);
            Toast.makeText(getContext(),"Profile image was successfully saved!",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(getContext(),"Error while saving the image, please try again",Toast.LENGTH_LONG).show();
    }


    private void saveImageUrlAndDisplay(Uri uri){
        MySharedPreferenceManager.saveString(MySharedPreferenceManager.PROFILE_PHOTO_URL,uri.toString(),getContext());
        FirebaseManager.updateProfileImageUrl(mUser);
        mProfilePlaceHolder.setVisibility(View.VISIBLE);
        Picasso.with(getContext()).load(uri).placeholder(R.color.darkNavyColor).into(mProfileImageView,this);
    }


}
