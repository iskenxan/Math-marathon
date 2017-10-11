package space.samatov.mathmarathon.view.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import space.samatov.mathmarathon.R;
import space.samatov.mathmarathon.model.utils.AnimationFactory;

/**
 * Created by iskenxan on 10/9/17.
 */

public class LoadingDialog extends DialogFragment {

    ImageView mImageView;


    public static LoadingDialog displayDialog(FragmentManager fragmentManager){
        LoadingDialog loadingDialog=newInstance();
        loadingDialog.show(fragmentManager,"loading_dialog");

        return loadingDialog;
    }




    public static LoadingDialog newInstance() {
        LoadingDialog loadingDialog=new LoadingDialog();

        return loadingDialog;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.loading_dialog, container);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCancelable(false);
        mImageView=view.findViewById(R.id.loadingDialogImageView);
        AnimationFactory.startRotatingAnimation(mImageView);
    }



}
