package space.samatov.mathmarathon.model.utils;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.TransitionDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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


    public static void startMoveRunnerAnimation(ImageView view){
        float distance=calculateMoveRunnerAnimationDistance(view.getContext());

        ObjectAnimator animation = ObjectAnimator.ofFloat(view, "translationX", view.getTranslationX()+distance);
        animation.setDuration(800);
        animation.start();
    }


    private static float calculateMoveRunnerAnimationDistance(Context context){
        int screenWidth=Formatter.getScreenSize(context)[0];

        float distance=screenWidth/25f;

        return distance;
    }



    private static void startPropertyAnimation(AnimatorSet animatorSet,View view){
        animatorSet.setTarget(view);
        animatorSet.start();
    }

}
