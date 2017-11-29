package space.samatov.mathmarathon.view.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.text.Format;
import java.util.ArrayList;

import space.samatov.mathmarathon.R;
import space.samatov.mathmarathon.model.FirebaseManager;
import space.samatov.mathmarathon.model.User;
import space.samatov.mathmarathon.model.UserReference;
import space.samatov.mathmarathon.model.interfaces.OnExtracUserListener;
import space.samatov.mathmarathon.model.utils.AnimationFactory;
import space.samatov.mathmarathon.model.utils.Formatter;

/**
 * Created by iskenxan on 10/18/17.
 */

public class SendFriendRequestDialog extends DialogFragment implements View.OnClickListener, OnExtracUserListener {

    EditText mUsernameEditText;
    ImageView mLoadingImageView;
    Button mSendButton;
    Button mCancelButton;



    public static SendFriendRequestDialog displayDialog(FragmentManager fragmentManager){
        SendFriendRequestDialog dialog=newInstance();
        dialog.show(fragmentManager,"friend_request");
        return dialog;
    }



    public static SendFriendRequestDialog newInstance() {
        SendFriendRequestDialog loadingDialog=new SendFriendRequestDialog();

        return loadingDialog;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.friend_request_dialog, container);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews(view);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setListeners();
    }


    private void bindViews(View view){
        mUsernameEditText = view.findViewById(R.id.FriendDialogUsernameEdiText);
        mLoadingImageView = view.findViewById(R.id.FriendDialogLoadingImageView);
        mSendButton = view.findViewById(R.id.FriendDialogSendButton);
        mCancelButton = view.findViewById(R.id.FriendDialogCancellButton);
    }


    private void setListeners(){
        mSendButton.setOnClickListener(this);
        mCancelButton.setOnClickListener(this);
    }



    @Override
    public void onResume() {
        super.onResume();
        //setSizeToMatchParent();
    }

    private void setSizeToMatchParent(){
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;

        getDialog().getWindow().setLayout(width,height);
    }



    @Override
    public void onClick(View view) {
        if(view.getId()==mSendButton.getId()){
            onSendButtonClicked();
        }
        else
            this.dismiss();
    }


    private void onSendButtonClicked(){
        displayLoadingCursor();
        String username=Formatter.formatStringForFirebase(mUsernameEditText.getText()+"");
        if(!username.equals(""))
            FirebaseManager.extractUserData(this,username);
        else
            displayErrorMessage("Username can't be empty!");
    }


    private void displayLoadingCursor(){
        mLoadingImageView.setVisibility(View.VISIBLE);
        AnimationFactory.startRotatingAnimation(mLoadingImageView);
    }



    @Override
    public void onUserDataExtracted(User user) {
        if(user!=null){
            checkUserAndSendRequest(user);
        }
        else{
            displayErrorMessage("User doesn't exist");
        }
    }
    

    private void checkUserAndSendRequest(User user){
        String currentEmail=FirebaseAuth.getInstance().getCurrentUser().getEmail();
        if(!currentEmail.equals(user.getEmail()))
            sendFriendRequestToUser(user);
        else
            displayErrorMessage("You can't send request to yourself!");
    }


    private void sendFriendRequestToUser(User user){
        String currentUsername= Formatter.getCurrentUsername();
        if(!friendRequestWasSent(user,currentUsername)){
            addRequestAndUpdateUser(currentUsername,user);
        }
        else{
            displayErrorMessage("Friend request has already been sent!");
        }
    }



    private void addRequestAndUpdateUser(String currentUsername,User user){
        user.getFriendRequests().add(currentUsername);
        FirebaseManager.setUserRecord(user,Formatter.formatStringForFirebase(user.getEmail()));
        onFriendRequestSent();
    }


    private boolean friendRequestWasSent(User user,String username){
        return user.getFriendRequests().contains(username)||user.getFriendList().contains(username);
    }



    private void onFriendRequestSent(){
        displayErrorMessage("Friend request has been sent!");
        dismiss();
    }

    private void displayErrorMessage(String message){
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
        mLoadingImageView.setVisibility(View.INVISIBLE);
    }
}
