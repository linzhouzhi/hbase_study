package com.mycompany.app.service;

import com.mycompany.app.config.Config;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.*;

/**
 * Created by lzz on 6/15/16
 * Hbase 操作类
 */
public class HbaseHelper {
    private static Configuration config;
    private static HTable table;
    static {
        Config HConfig = new Config();
        config = HConfig.getHConfig();
    }

    public HbaseHelper( String table ){
        try {
            this.table = new HTable(config, table);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加和修改
     * rowKey 就是行键
     * family 是列族
     * value 就是值
     * qualifier就是列限制符column:name
     */
    public void put( String rowKey, String family, Map map ) {
        Put p = new Put( Bytes.toBytes( rowKey ) );
        //获取所有的key
        Set keys = map.keySet();
        Iterator iter = keys.iterator();

        do {
            String key = (String)iter.next();
            String value = (String) map.get(key);
            p.add(Bytes.toBytes( family ), Bytes.toBytes( key ), Bytes.toBytes( value ));
        }while ( iter.hasNext() );

        try {
            table.put(p);
        } catch (InterruptedIOException e) {
            e.printStackTrace();
        } catch (RetriesExhaustedWithDetailsException e) {
            e.printStackTrace();
        }
    }

    /**
     * 单个增加和修改
     * rowKey 就是行键
     * family 是列族
     * value 就是值
     * qualifier就是列限制符column:name
     */
    public void put( String rowKey, String family, String value, String qualifier ) {
        Put p = new Put( Bytes.toBytes( rowKey ) );
        p.add(Bytes.toBytes( family ), Bytes.toBytes( qualifier ), Bytes.toBytes( value ));
        try {
            table.put(p);
        } catch (InterruptedIOException e) {
            e.printStackTrace();
        } catch (RetriesExhaustedWithDetailsException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除
     * rowKey 行键
     */
    public void delete( String rowKey ){
        List list = new ArrayList();
        Delete d1 = new Delete(rowKey.getBytes());
        list.add(d1);
        try {
            table.delete( list );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 单条查询
     * rowKey 就是行键
     * family 是列族
     * qualifier就是列限制符column:name
     */
    public  Result get( String rowKey ) {
        Get g = new Get( Bytes.toBytes( rowKey ) );
        Result r = null;
        try {
            r = table.get( g );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return r;
    }

    /**
     * 多条查询
     * family 是列族
     * qualifier 就是列限制符column:name
     */
    public List<String> getList( String family, Filter filter ) {
        Scan s = new Scan();
        s.setFilter(filter);
        s.addFamily( Bytes.toBytes( family ) );
        ResultScanner scanner = null;
        List<String> resultList = new ArrayList<String>();
        try {
            scanner = table.getScanner(s);
            for( Result rr : scanner ){
                //byte[] row = rr.getRow();
                byte[] value = rr.value();
                String s_value = new String( value );
                resultList.add( s_value );
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
        return resultList;
    }
}
