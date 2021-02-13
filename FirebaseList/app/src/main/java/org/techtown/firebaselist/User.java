package org.techtown.firebaselist;

public class User {
    private String profile;
    private String ID;
    private int pw;
    private String user_name;

    public User(){}

    public String getProfile() {
        return profile;
    }

    public String getID() {
        return ID;
    }

    public int getPw() {
        return pw;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPw(int pw) {
        this.pw = pw;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
