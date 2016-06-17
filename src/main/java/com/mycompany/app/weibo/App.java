package com.mycompany.app.weibo;

import com.mycompany.app.weibo.dao.FollowsDAO;

import java.util.List;

/**
 * Created by lzz on 6/17/16.
 */
public class App {
    public static void main( String[] args ){
        FollowsDAO followsDAO = new FollowsDAO();
        //1 lzz 关注 luu 了吗？
        String res = followsDAO.getFollow( "lzz", "luu" );
        System.out.println( res != null ? "lzz 关注了 luu" : "lzz 没有关注 luu");
        //2 lzz 关注了谁
        List<String> list = followsDAO.getFollowList( "lzz" );
        System.out.println("lzz关注了：");
        for ( String l : list ) {
            System.out.println( l );
        }
        //3 luu 关注了 lzz 了吗？
        String res2 = followsDAO.getFollow( "luu", "lzz" );
        System.out.println( res2 != null ? "luu 关注了 lzz" : "luu 没关注 lzz" );
        //4 谁关注了lzz
        List<String> list2 = followsDAO.getFollowedList( "lzz" );
        System.out.println("有谁关注了lzz：");
        for ( String l : list2 ) {
            System.out.println( l );
        }
    }
}
