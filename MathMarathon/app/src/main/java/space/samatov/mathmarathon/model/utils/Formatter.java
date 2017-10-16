package space.samatov.mathmarathon.model.utils;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

/**
 * Created by iskenxan on 10/11/17.
 */

public class Formatter {


    public static String formatEmailForFirebase(String email){
        String formatted=email.split("@")[0];
        formatted=formatted.replaceAll("\\.","");

        return formatted;
    }


    public static byte[] convertBitmapToBytes(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        return data;
    }


}
