package space.samatov.mathmarathon.model.utils;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.graphics.drawable.TransitionDrawable;
import android.view.View;
import android.widget.Button;

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


    public static void startRightAnswerButtonAnimation(Button button){
        button.setBackground(button.getResources().getDrawable(R.drawable.right_answer_button_transition));
        TransitionDrawable transition = (TransitionDrawable) button.getBackground();
        transition.startTransition(150);
        transition.reverseTransition(150);
    }

    public static void startWrongAnswerButtonAnimation(Button button){
        button.setBackground(button.getResources().getDrawable(R.drawable.wront_asnwer_button_transition));
        TransitionDrawable transition = (TransitionDrawable) button.getBackground();
        transition.startTransition(150);
        transition.reverseTransition(150);
    }



    private static void startPropertyAnimation(AnimatorSet animatorSet,View view){
        animatorSet.setTarget(view);
        animatorSet.start();
    }




}
