package model;


public class UserModel extends BaseModel {

    private String username;
    private boolean loggedIn = false;

    private static UserModel userModel = null;

    private UserModel() {}

    public static UserModel getUserModel() {
        if(userModel == null) {
            userModel = new UserModel();
        }
        return userModel;
    }

    public void setUsername(String username) {
        this.username = username;
        this.loggedIn = true;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
