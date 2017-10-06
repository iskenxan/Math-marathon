package space.samatov.mathmarathon.model;

import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;


/**
 * Created by iskenxan on 10/6/17.
 */

public class AnimationManager {



    public static void startAnimationOnImageView(ImageView imageView,int animationResource){
        AnimationDrawable animation;

        imageView.setBackgroundResource(animationResource);
        animation = (AnimationDrawable) imageView.getBackground();
        animation.start();
    }
}
