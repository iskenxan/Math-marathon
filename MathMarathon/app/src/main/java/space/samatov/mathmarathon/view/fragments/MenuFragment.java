package space.samatov.mathmarathon.view.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.samatov.mathmarathon.R;
import space.samatov.mathmarathon.model.utils.FragmentFactory;

/**
 * Created by iskenxan on 10/9/17.
 */

public class MenuFragment extends Fragment {


    Handler mHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_menu,container,false);
        ButterKnife.bind(this,view);
        mHandler=new Handler();

        return view;
    }


    @OnClick(R.id.MenuFragmentStartButton)
    public void onStartButtonClicked(){

    }


    @OnClick(R.id.MenuFragmentFriendButton)
    public void onFriendRequestsButtonClicked(){
        mHandler.postDelayed(friendRequestRunnable,300);
    }



    @OnClick(R.id.MenuFragmentMyProfile)
    public void onMyProfileButtonClicked(){
        mHandler.postDelayed(myProfileRunnable,300);
    }


    Runnable  myProfileRunnable=new Runnable() {
        @Override
        public void run() {
            FragmentFactory.startMyProfileFragment((AppCompatActivity) getActivity());
        }
    };


    Runnable friendRequestRunnable= new Runnable() {
        @Override
        public void run() {
            FragmentFactory.startFriendRequestFragment((AppCompatActivity) getActivity());
        }
    };


}
