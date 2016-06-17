package com.mycompany.app.weibo.model;


/**
 * Created by lzz on 6/16/16.
 */
public class User {
    private String nickName;
    private String userName;
    private String email;
    private String password;

    public User(){}

    public User(String nickName, String userName, String email, String password) {
        this.nickName = nickName;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public String toString(){
        return String.format( "<User: %s, %s, %s, %s >", nickName, userName, email, password );
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
