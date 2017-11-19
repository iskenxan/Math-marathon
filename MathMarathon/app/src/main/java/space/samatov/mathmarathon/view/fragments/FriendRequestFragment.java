package space.samatov.mathmarathon.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.samatov.mathmarathon.R;
import space.samatov.mathmarathon.model.FirebaseManager;
import space.samatov.mathmarathon.model.MySharedPreferenceManager;
import space.samatov.mathmarathon.model.User;
import space.samatov.mathmarathon.model.UserReference;
import space.samatov.mathmarathon.model.interfaces.OnExtracUserListener;
import space.samatov.mathmarathon.model.interfaces.OnUserRequestItemClickedListener;
import space.samatov.mathmarathon.model.utils.Formatter;
import space.samatov.mathmarathon.model.utils.FragmentFactory;
import space.samatov.mathmarathon.view.adapters.FriendRequestListAdapter;
import space.samatov.mathmarathon.view.dialogs.SendFriendRequestDialog;

/**
 * Created by iskenxan on 10/16/17.
 */

public class FriendRequestFragment extends Fragment implements OnExtracUserListener, OnUserRequestItemClickedListener {


    @BindView(R.id.FriendRequestsProfileImageView)ImageView mProfileImageView;
    @BindView(R.id.FriendRequestsRecyclerView)RecyclerView mRecyclerView;
    @BindView(R.id.FriendRequestsRecyclerContainer)LinearLayout mRecyclerContainer;
    @BindView(R.id.FriendRequestsNoRequestsContainer)LinearLayout mNorequestsContainer;

    User mCurrentUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_friend_requests,container,false);
        ButterKnife.bind(this,view);
        FirebaseManager.extracCurrentUserData(this);
        setupViews();
        return view;
    }


    private void setupViews(){
        String profileUrl= MySharedPreferenceManager.getString(MySharedPreferenceManager.PROFILE_PHOTO_URL,getContext());
        Picasso.with(getContext()).load(profileUrl).placeholder(R.drawable.profile_placeholder).into(mProfileImageView);
    }


    @Override
    public void onUserDataExtracted(User user) {
        if(user.getEmail().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
            mCurrentUser=user;
            displayListOrEmptyListMessage();
        }
        else
            addCurrentUserToAcceptedUserFriendList(user);
    }


    private void addCurrentUserToAcceptedUserFriendList(User user){
        user.getFriendList().add(Formatter.getCurrentUsername());
        FirebaseManager.setUserRecord(user,Formatter.formatStringForFirebase(user.getEmail()));
    }


    private void displayListOrEmptyListMessage(){
        if(mCurrentUser.getFriendRequests().size()>0){
            mRecyclerContainer.setVisibility(View.VISIBLE);
            setupRecyclerView(mCurrentUser);
        }
        else{
            mNorequestsContainer.setVisibility(View.VISIBLE);
            mRecyclerContainer.setVisibility(View.INVISIBLE);
        }
    }


    private void setupRecyclerView(User user){
        FriendRequestListAdapter adapter=new FriendRequestListAdapter(user.getFriendRequests(),this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onRequestItemClicked(boolean accepted, int position) {
        acceptOrRejectFriendRequest(accepted,position);
        FirebaseManager.setUserRecord(mCurrentUser,Formatter.getCurrentUsername());
        mRecyclerView.getAdapter().notifyDataSetChanged();
        displayListOrEmptyListMessage();
    }



    private void acceptOrRejectFriendRequest(boolean accepted, int position){
        if(accepted){
            String friendRequest=mCurrentUser.getFriendRequests().get(position);
            mCurrentUser.getFriendList().add(friendRequest);
            FirebaseManager.extractUserData(this,friendRequest);
        }
        mCurrentUser.getFriendRequests().remove(position);
    }


    @OnClick(R.id.FriendRequestsSendButton)
    public void onSendButtonClicked(){
        SendFriendRequestDialog.displayDialog(getFragmentManager());
    }


    @OnClick(R.id.FriendRequestsBackButton)
    public void onBackButtonClicked(){
        FragmentFactory.startMenuFragment((AppCompatActivity) getActivity());
    }





}
