package com.mycompany.app.weibo.dao;

import com.mycompany.app.weibo.model.Follows;
import org.junit.Test;

/**
 * Created by lzz on 6/17/16.
 */
public class FollowsDAOTest {
    private static FollowsDAO fDao = new FollowsDAO();
    @Test
    public void testAddFollowe(){
        Follows follows = new Follows( "lzz", "ldd", "helloldd");
        fDao.addFollow( follows );
    }

    @Test
    public void testGetFollowe(){
        fDao.getFollow( "lzz", "ldd" );
    }
}
