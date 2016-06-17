package com.mycompany.app.weibo.dao;

import com.mycompany.app.weibo.model.Weibo;
import org.junit.Test;

import java.util.List;

/**
 * Created by lzz on 6/17/16.
 */
public class WeiboDAOTest {
    private static WeiboDAO weiboDAO = new WeiboDAO();

    @Test
    public void testAddWeibo(){

        Weibo weibo2  = new Weibo( "kklzz", "hello today is satuday2 ");
        weiboDAO.addWeibo( weibo2 );
        Weibo weibo3  = new Weibo( "kklzz", "hello today is satuday3 ");
        weiboDAO.addWeibo( weibo3 );
    }

    @Test
    public void testGetWeiboList(){
        List<String> list =  weiboDAO.getWeiboList( "lzz" );
        for (String l : list ) {
            System.out.printf( l );
        }
    }
}
