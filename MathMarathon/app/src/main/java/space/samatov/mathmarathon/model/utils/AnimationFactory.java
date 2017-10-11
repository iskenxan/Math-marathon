package space.samatov.mathmarathon.model.utils;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.view.View;

import space.samatov.mathmarathon.R;

/**
 * Created by iskenxan on 10/9/17.
 */

public class AnimationFactory {



    public static void startPulsatinAnimation(View view){
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(view.getContext(), R.animator.pulsating_anim);
        startPropertyAnimation(set,view);
    }



    public static void startRotatingAnimation(View view){
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(view.getContext(), R.animator.rotating_anim);
        startPropertyAnimation(set,view);
    }



    private static void startPropertyAnimation(AnimatorSet animatorSet,View view){
        animatorSet.setTarget(view);
        animatorSet.start();
    }


}
