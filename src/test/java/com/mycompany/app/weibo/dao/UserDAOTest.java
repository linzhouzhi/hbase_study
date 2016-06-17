package com.mycompany.app.weibo.dao;

import com.mycompany.app.weibo.model.User;
import org.junit.Test;

/**
 * Created by lzz on 6/16/16.
 */
public class UserDAOTest {
    private static UserDAO userDAO = new UserDAO();

    @Test
    public void testAddUser(){
        User user = new User("linzhouzhi","林周治","490664646@qq.com","127654");
        userDAO.addUser( user );

    }

    @Test
    public void testGetUser(){
        User usere = userDAO.getUser( "linzhouzhi" );
        System.out.printf("user:" + usere );
    }

    @Test
    public void testDeleteUser(){
        userDAO.deleteUser( "linzhouzhi" );
    }
}
