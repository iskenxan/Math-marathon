package space.samatov.mathmarathon.model;

import android.graphics.Bitmap;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import space.samatov.mathmarathon.model.interfaces.OnExtracUserListener;
import space.samatov.mathmarathon.model.interfaces.OnImageUploadListener;
import space.samatov.mathmarathon.model.utils.Formatter;

/**
 * Created by iskenxan on 10/11/17.
 */

public class FirebaseManager {
    public static final String USERS="users";
    public static final String PROFILE_IMAGES="profile_images";


    public static void extracUserData(OnExtracUserListener listener){
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        String formattedEmail= Formatter.formatEmailForFirebase(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        databaseReference.child(USERS).child(formattedEmail).
                addListenerForSingleValueEvent(new FirebaseCallbacks.ExtractUserCallback(listener));
    }



    public static void createNewUserRecord(){
        User user=getNewUserBasicInfo();
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        String formattedEmail= Formatter.formatEmailForFirebase(user.getEmail());

        databaseReference.child(USERS).child(formattedEmail).setValue(user);
    }


    private static User getNewUserBasicInfo(){
        User user=new User();
        FirebaseUser currentUser=FirebaseAuth.getInstance().getCurrentUser();
        user.setEmail(currentUser.getEmail());
        user.setFullName(currentUser.getDisplayName());
        user.setUID(currentUser.getUid());

        return user;
    }



    public static void updateProfileImageUrl(User user){
        String username=Formatter.formatEmailForFirebase(user.getEmail());

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child(USERS).child(username).child("photoUrl").setValue(user.getPhotoUrl());
    }


    public static void saveProfileImage(Bitmap bitmap, OnImageUploadListener listener,User user){
        StorageReference storageReference=getProfileImageStorageRef(user);
        byte[] bitmapBytArray=Formatter.convertBitmapToBytes(bitmap);
        FirebaseCallbacks.ImageUploadCallback callback=new FirebaseCallbacks.ImageUploadCallback(listener);

        UploadTask uploadTask = storageReference.putBytes(bitmapBytArray);
        uploadTask.addOnFailureListener(callback).addOnSuccessListener(callback);
    }



    private static StorageReference getProfileImageStorageRef(User user){
        StorageReference mountainImagesRef = FirebaseStorage.getInstance().getReference();
        String userName=Formatter.formatEmailForFirebase(user.getEmail());
        mountainImagesRef= mountainImagesRef.child(PROFILE_IMAGES+"/"+userName+".jpg");

        return mountainImagesRef;
    }


}
