package com.mycompany.app.weibo.dao;

import com.mycompany.app.service.HbaseHelper;
import com.mycompany.app.weibo.model.Follows;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lzz on 6/17/16.
 */
public class FollowsDAO {
    private static final String TABLE_NAME = "follows";
    private static final String COLUMN_FAMILY = "f";
    private static final HbaseHelper HBase = new HbaseHelper( TABLE_NAME );

    public void addFollow( Follows f ){
        Map map = new HashMap();
        map.put( f.getFollowed(),f.getFollowed_name() );
        String key = keyRow( f.getFollower() , f.getFollowed() );
        HBase.put( key, COLUMN_FAMILY, map );
    }

    public String getFollow(String follower, String followed ){
        String key = keyRow( follower, followed );
        Result r =  HBase.get( key );
        byte[] value = r.value();
        String s_value = new String( value );
        return s_value;
    }

    public List<String> getFollowList( String follower ){
        Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL,new RegexStringComparator( "^"+follower ) );
        List<String> list = HBase.getList( COLUMN_FAMILY ,  filter );
        return list;
    }

    private String keyRow( String follower, String followed ){
        return follower + "_" + followed;
    }
}
