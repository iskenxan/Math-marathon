package space.samatov.mathmarathon.model.utils;


import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;

import com.google.android.gms.auth.api.Auth;

import space.samatov.mathmarathon.model.GoogleSignInManager;

/**
 * Created by iskenxan on 10/11/17.
 */

public class IntentFactory {

    public static final int GOOGLE_SIGN_IN_INTENT=0;
    public static final int REQUEST_IMAGE_CAPTURE = 1;


    public static void startGoogleSignInIntent(GoogleSignInManager googleSignInManager,Fragment fragment){
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleSignInManager.getmGoogleApiClient());
        fragment.startActivityForResult(signInIntent, GOOGLE_SIGN_IN_INTENT);
    }


    public  static void dispatchTakePictureIntent(Fragment fragment) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(fragment.getActivity().getPackageManager()) != null) {
            fragment.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

}
