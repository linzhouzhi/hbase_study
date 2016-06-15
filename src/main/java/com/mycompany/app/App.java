package com.mycompany.app;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        HbaseHelper helper = new HbaseHelper();
        //helper.insert( "rk008", "c1", "hello insert3", "f5" );
        //helper.get( "rk008", "c1", "" );
        helper.getAll( "c1", "", (long) 100);
    }
}
