package space.samatov.mathmarathon.model;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.UploadTask;

import space.samatov.mathmarathon.model.interfaces.OnExtracUserListener;
import space.samatov.mathmarathon.model.interfaces.OnImageUploadListener;

/**
 * Created by iskenxan on 10/11/17.
 */

public class FirebaseCallbacks {


    public static class ExtractUserCallback implements ValueEventListener{
        OnExtracUserListener mListener;


        public ExtractUserCallback(OnExtracUserListener listener){
            mListener=listener;
        }


        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if(dataSnapshot.getValue()!=null){
                User user=dataSnapshot.getValue(User.class);
                mListener.onUserDataExtracted(user);
            }
            else
                mListener.onUserDataExtracted(null);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            mListener.onUserDataExtracted(null);
        }
    }


    public static class ImageUploadCallback implements OnFailureListener, OnSuccessListener<UploadTask.TaskSnapshot>{
        OnImageUploadListener mListener;

        public ImageUploadCallback(OnImageUploadListener listener) {
            mListener=listener;
        }


        @Override
        public void onFailure(@NonNull Exception e) {
            mListener.onImageUploadResult(null);
        }


        @Override
        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            Uri downloadUrl = taskSnapshot.getDownloadUrl();
            mListener.onImageUploadResult(downloadUrl);
        }
    }
}
