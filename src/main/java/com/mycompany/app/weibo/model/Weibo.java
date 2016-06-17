package com.mycompany.app.weibo.model;

/**
 * Created by lzz on 6/16/16.
 */
public class Weibo {
    private String author;
    private String content;

    public Weibo(){}

    public Weibo(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String toString(){
        return String.format( "<weibo: %s: %s>", author, content );
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

