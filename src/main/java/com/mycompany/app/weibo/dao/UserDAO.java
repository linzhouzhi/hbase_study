package com.mycompany.app.weibo.dao;

import com.mycompany.app.service.HbaseHelper;
import com.mycompany.app.service.HbaseTool;
import com.mycompany.app.weibo.model.User;
import org.apache.hadoop.hbase.client.Result;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lzz on 6/16/16.
 */
public class UserDAO {
    private static final String TABLE_NAME = "user";
    private static final String COLUMN_FAMILY = "f";
    private static final String NICK_NAME_COL = "nick_name";
    private static final String USER_NAME_COL = "user_name";
    private static final String EMAIL_COL = "email";
    private static final String PASSWD_COL = "passwd";
    private static final HbaseHelper HBase = new HbaseHelper( TABLE_NAME );
    /**
     * 增加用户
     * @param u
     */
    public void addUser( User u ){
        Map map = new HashMap();
        map.put( NICK_NAME_COL, u.getNickName() );
        map.put( USER_NAME_COL, u.getUserName() );
        map.put( EMAIL_COL, u.getEmail() );
        map.put( PASSWD_COL, u.getPassword() );
        HBase.put( u.getNickName(), COLUMN_FAMILY, map );
    }

    /**
     * 获取用户
     * @param nickName
     * @return User
     */
    public User getUser(String nickName ){
        Result r =  HBase.get( nickName );
        User user = new User();
        user.setUserName( HbaseTool.getValue( r, COLUMN_FAMILY, USER_NAME_COL ) );
        user.setPassword( HbaseTool.getValue( r, COLUMN_FAMILY, PASSWD_COL ) );
        user.setEmail( HbaseTool.getValue( r, COLUMN_FAMILY, EMAIL_COL ) );
        user.setNickName( nickName );
        return user;
    }

    public void deleteUser( String nickName ){
        HBase.delete( nickName );
    }

}
