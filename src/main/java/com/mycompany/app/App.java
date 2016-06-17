package com.mycompany.app;

import com.mycompany.app.service.HbaseHelper;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        HbaseHelper helper = new HbaseHelper();
        helper.put( "rk008", "c1", "林周知", "f5" );
        helper.get( "rk008", "c1", "f5" );
        //helper.delete( "rk004" );
        helper.scan( "c1", "", (long) 100);
    }
}

