package space.samatov.mathmarathon.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.samatov.mathmarathon.R;
import space.samatov.mathmarathon.model.FirebaseManager;
import space.samatov.mathmarathon.model.User;
import space.samatov.mathmarathon.model.interfaces.OnExtracUserListener;
import space.samatov.mathmarathon.model.utils.AnimationFactory;

/**
 * Created by iskenxan on 11/6/17.
 */

public class LoadingGameFragment extends Fragment implements OnExtracUserListener {
    //TODO: AddListener to listen to firebase events and set up loading game workflow
    @BindView(R.id.LoadingFragmentImageView)ImageView mLoadingImageView;
    User mCurrentUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_loading_game,container,false);
        ButterKnife.bind(this,view);
        AnimationFactory.startPulsatinAnimation(mLoadingImageView);
        FirebaseManager.extracCurrentUserData(this);
        return view;
    }


    @Override
    public void onUserDataExtracted(User user) {

    }


    @OnClick(R.id.LoadingFragmentCancelButton)
    public void onCancelButtonClicked(){

    }


}
