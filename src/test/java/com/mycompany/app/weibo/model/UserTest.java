package com.mycompany.app.weibo.model;

import org.junit.Test;

/**
 * Created by lzz on 6/16/16.
 */
public class UserTest {
    @Test
    public void testUser(){
        User user = new User( "linzhouzhi","林周治","490664646@qq.com","7654" );
        System.out.printf( user.toString() );

    }
}
