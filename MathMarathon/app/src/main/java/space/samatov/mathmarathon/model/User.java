package space.samatov.mathmarathon.model;

import java.util.ArrayList;

/**
 * Created by iskenxan on 10/11/17.
 */

public class User {

    private String UID;
    private String email;
    private String fullName;
    private String photoUrl;
    private int overallScore=0;
    private int Wins=0;
    private int loses=0;
    private ArrayList<UserReference> friendRequests=new ArrayList<>();
    private ArrayList<UserReference> friendList=new ArrayList<>();


    public ArrayList<UserReference> getFriendList() {
        return friendList;
    }

    public void setFriendList(ArrayList<UserReference> friendList) {
        this.friendList = friendList;
    }

    public ArrayList<UserReference> getFriendRequests() {
        return friendRequests;
    }

    public void setFriendRequests(ArrayList<UserReference> friendRequests) {
        this.friendRequests = friendRequests;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(int overallScore) {
        this.overallScore = overallScore;
    }

    public int getWins() {
        return Wins;
    }

    public void setWins(int wins) {
        Wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }
}
