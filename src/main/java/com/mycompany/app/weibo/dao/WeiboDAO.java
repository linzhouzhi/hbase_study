package com.mycompany.app.weibo.dao;

import com.mycompany.app.service.HbaseHelper;
import com.mycompany.app.service.HbaseTool;
import com.mycompany.app.weibo.model.Weibo;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by lzz on 6/17/16.
 */
public class WeiboDAO {
    private static final String TABLE_NAME = "weibo";
    private static final String COLUMN_FAMILY = "f";
    private static final HbaseHelper HBase = new HbaseHelper( TABLE_NAME );

    /**
     * 添加微薄
     * @param weibo
     */
    public void addWeibo(Weibo weibo ){
        Map map = new HashMap();
        map.put( weibo.getAuthor(), weibo.getContent() );
        String key = weibo.getAuthor() + "_" + String.valueOf(( Long.MAX_VALUE - System.currentTimeMillis() ));
        HBase.put( key, COLUMN_FAMILY, map );
    }


    public List<String> getWeiboList( String author ){
        Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL,new RegexStringComparator( "^"+author ) );
        List<String> list = HBase.getList( COLUMN_FAMILY ,  filter );
        return list;
    }

}
