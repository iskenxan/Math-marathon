package space.samatov.mathmarathon.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.samatov.mathmarathon.R;
import space.samatov.mathmarathon.model.utils.FragmentFactory;

/**
 * Created by iskenxan on 10/16/17.
 */

public class FriendRequestFragment extends Fragment {


    @BindView(R.id.FriendRequestsProfileImageView)ImageView mProfileImageView;
    @BindView(R.id.FriendRequestsNoRequestsTextView)TextView mNoRequestTextView;
    @BindView(R.id.FriendRequestsRecyclerView)RecyclerView mRecyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_friend_requests,container,false);
        ButterKnife.bind(this,view);

        return view;
    }


    @OnClick(R.id.FriendRequestsSendButton)
    public void onSendButtonClicked(){

    }


    @OnClick(R.id.FriendRequestsBackButton)
    public void onBackButtonClicked(){
        FragmentFactory.startMenuFragment((AppCompatActivity) getActivity());
    }
}
