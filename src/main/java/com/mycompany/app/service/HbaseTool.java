package com.mycompany.app.service;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by lzz on 6/17/16.
 */
public class HbaseTool {
    /**
     * 根据cf和qualifier获取result中的string类型的值
     */
    public static String getValue( Result result, String cf, String qualifier ){
        return new String( result.getValue( cf.getBytes(), qualifier.getBytes()) );
    }

    /**
     *
     */
    public static String getValues( Iterator<Result> interResult ){
        for (Result rr = interResult.next(); rr != null; rr = interResult.next()) {
            System.out.printf(rr.toString());
            byte[] row = rr.getRow();
            byte[] value = rr.value();
            String s_value = new String( value );
            String s_row = new String( row );
            System.out.println( s_row + " : " + s_value );
        }
        return "";
    }
}
