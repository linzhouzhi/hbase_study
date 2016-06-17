package com.mycompany.app.weibo.model;

/**
 * Created by lzz on 6/16/16.
 */
public class Follows {
    //关注者（nick_name）
    private String follower;
    //被关注者
    private String followed;
    //被关注者用户名
    private String followed_name;

    public Follows(){}

    public Follows(String follower, String followed, String followed_name) {
        this.follower = follower;
        this.followed = followed;
        this.followed_name = followed_name;
    }

    public  String toString(){
        return String.format( "<Follows: follower-> %s,followed-> %s,followed_name-> %s>", follower, followed, followed_name );
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getFollowed() {
        return followed;
    }

    public void setFollowed(String followed) {
        this.followed = followed;
    }

    public String getFollowed_name() {
        return followed_name;
    }

    public void setFollowed_name(String followed_name) {
        this.followed_name = followed_name;
    }
}
