package com.mycompany.app;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.*;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.util.Bytes;
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
        try {
            table = new HTable(config, "user");
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
    public void get(String rowKey, String family, String qualifier ) {
        Get g = new Get( Bytes.toBytes( rowKey ) );
        Result r = null;
        try {
            r = table.get( g );
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte [] value = r.getValue(Bytes.toBytes( family ), Bytes.toBytes( qualifier ));
        String valueStr = Bytes.toString(value);
        System.out.println("GET: " + valueStr);
    }

    /**
     * 多条查询
     * family 是列族
     * qualifier 就是列限制符column:name
     */
    public void scan( String family, String qualifier, Long limit ) {
        Scan s = new Scan();
        s.setFilter( new PageFilter( limit ) );
        s.addColumn(Bytes.toBytes( family ), Bytes.toBytes( qualifier ));
        ResultScanner scanner = null;
        try {
            scanner = table.getScanner(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // Scanners return Result instances.
            // Now, for the actual iteration. One way is to use a while loop like so:
            for (Result rr = scanner.next(); rr != null; rr = scanner.next()) {
                byte[] row = rr.getRow();
                byte[] value = rr.value();
                String s_value = new String( value );
                String s_row = new String( row );
                System.out.println( s_row + " : " + s_value );
            }

            // The other approach is to use a foreach loop. Scanners are iterable!
            // for (Result rr : scanner) {
            //   System.out.println("Found row: " + rr);
            // }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Make sure you close your scanners when you are done!
            // Thats why we have it inside a try/finally clause
            scanner.close();
        }
    }
}
