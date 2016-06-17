package com.mycompany.app.weibo.model;

import org.junit.Test;

/**
 * Created by lzz on 6/16/16.
 */
public class WeiboTest {
    @Test
    public void testWeibo(){
        Weibo weibo = new Weibo("linzhouzhi", "hello weibo");
        System.out.printf( weibo.toString() );
    }
}
