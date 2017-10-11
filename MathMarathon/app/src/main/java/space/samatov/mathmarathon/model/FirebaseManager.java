package space.samatov.mathmarathon.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by iskenxan on 10/11/17.
 */

public class FirebaseManager {
    public static final String USERS="users";


    public static void createNewUserRecord(){
        User user=getNewUserBasicInfo();
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();

        databaseReference.child(USERS).child(user.getUID()).setValue(user);
    }


    private static User getNewUserBasicInfo(){
        User user=new User();
        FirebaseUser currentUser=FirebaseAuth.getInstance().getCurrentUser();
        user.setEmail(currentUser.getEmail());
        user.setFullName(currentUser.getDisplayName());
        user.setUID(currentUser.getUid());

        return user;
    }


}
