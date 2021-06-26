package server.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {

    private String userName;
    private int password;
    private String email;
    private Date created;
    private String firstName;
    private String lastName;
    private int followersNumber;
    private int followingNumber;
    private String bio;

    public User(String userName , String password , String email) {
        this.userName = userName;
        this.password = password.hashCode();
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }


}
