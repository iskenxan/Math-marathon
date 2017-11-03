package space.samatov.mathmarathon.model.utils;

import android.content.Context;
import android.graphics.Bitmap;

import com.google.firebase.auth.FirebaseAuth;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import space.samatov.mathmarathon.model.MySharedPreferenceManager;
import space.samatov.mathmarathon.model.UserReference;

/**
 * Created by iskenxan on 10/11/17.
 */

public class Formatter {



    public static UserReference getCurrentUserRequest(Context context){
        UserReference currentFriendRequest =new UserReference();
        currentFriendRequest.setUsername(Formatter.getCurrentUsername());
        currentFriendRequest.setPhotoUrl(MySharedPreferenceManager.getString(MySharedPreferenceManager.PROFILE_PHOTO_URL,context));

        return currentFriendRequest;
    }



    public static String getCurrentUsername(){
        String currentEmail= FirebaseAuth.getInstance().getCurrentUser().getEmail();
        String currentUsername= formatStringForFirebase(currentEmail);

        return currentUsername;
    }


    public static boolean listContainsUserRequest(ArrayList<UserReference> userList, UserReference user){
        for(UserReference listItem:userList){
            if(listItem.getUsername().equals(user.getUsername()))
                return true;
        }
        return false;
    }


    public static String formatStringForFirebase(String str){
        String formatted=str.split("@")[0];
        formatted=formatted.replaceAll("\\.","");

        return formatted;
    }


    public static byte[] convertBitmapToBytes(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        return data;
    }


}
