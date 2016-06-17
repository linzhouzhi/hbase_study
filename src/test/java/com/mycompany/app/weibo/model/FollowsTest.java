package com.mycompany.app.weibo.model;

import org.junit.Test;

/**
 * Created by lzz on 6/16/16.
 */
public class FollowsTest {
    @Test
    public void testFollows(){
        Follows follow = new Follows( "linzhouzhi","hello","wollllll");
        System.out.printf( follow.toString() );
    }
}
