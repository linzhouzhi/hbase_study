package com.mycompany.app.weibo.dao;

import com.mycompany.app.weibo.model.Follows;
import org.junit.Test;

import java.util.List;

/**
 * Created by lzz on 6/17/16.
 */
public class FollowsDAOTest {
    private static FollowsDAO fDao = new FollowsDAO();
    @Test
    public void testAddFollowe(){
        Follows follows = new Follows( "lzz", "ldd3", "helloldd3");
        fDao.addFollow( follows );

        Follows follows1 = new Follows( "lzz", "luu", "helloaaa");
        fDao.addFollow( follows1 );

        Follows follows2 = new Follows( "lzz", "luu", "hellobbb");
        fDao.addFollow( follows2 );

        Follows follows3 = new Follows( "luu", "lzz", "hellccc");
        fDao.addFollow( follows3 );

        Follows follows4 = new Follows( "lxy", "lzz", "hellddddd");
        fDao.addFollow( follows4 );
    }

    @Test
    public void testGetFollowe(){
        String res = fDao.getFollow( "lzz", "ldd" );
        System.out.printf( res );
    }

    @Test
    public void testGetList(){
        List< String > list = fDao.getFollowList( "lzz" );
        for ( String l : list ) {
            System.out.println( l );
        }
    }
}
