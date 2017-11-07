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

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.samatov.mathmarathon.R;
import space.samatov.mathmarathon.model.FirebaseManager;
import space.samatov.mathmarathon.model.User;
import space.samatov.mathmarathon.model.interfaces.OnExtracUserListener;
import space.samatov.mathmarathon.model.interfaces.OnStartGameItemClickedListener;
import space.samatov.mathmarathon.model.utils.FragmentFactory;
import space.samatov.mathmarathon.view.adapters.StartGameListAdapter;

/**
 * Created by iskenxan on 11/6/17.
 */

public class StartGameFragment extends Fragment implements OnExtracUserListener, OnStartGameItemClickedListener {

    @BindView(R.id.StartGameProfileImageView)ImageView mProfileImageView;
    @BindView(R.id.StartGameNoFriendsContainer)LinearLayout mNoFriendsContainer;
    @BindView(R.id.StartGameRecyclerContainer)LinearLayout mRecyclerContainer;
    @BindView(R.id.StartGameRecyclerView)RecyclerView mRecyclerView;
    User mCurrentUser;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_start_game,container,false);
        ButterKnife.bind(this,view);
        FirebaseManager.extracCurrentUserData(this);
        return view;
    }


    @Override
    public void onUserDataExtracted(User user) {
        Picasso.with(getContext()).load(user.getPhotoUrl()).placeholder(R.drawable.profile_placeholder).into(mProfileImageView);
        mCurrentUser=user;
        if(user.getFriendList().size()>0){
            mRecyclerContainer.setVisibility(View.VISIBLE);
            setupRecyclerView();
        }
        else
            mNoFriendsContainer.setVisibility(View.VISIBLE);
    }


    private void setupRecyclerView(){
        StartGameListAdapter adapter=new StartGameListAdapter(mCurrentUser.getFriendList(),this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
    }


    @Override
    public void onStartGameItemClicked(int position) {

    }


    @OnClick(R.id.StartGameBackButton)
    public void onBackButtonClicked(){
        FragmentFactory.startMenuFragment((AppCompatActivity) getActivity());
    }

}
