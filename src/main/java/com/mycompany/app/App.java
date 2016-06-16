package com.mycompany.app;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        HbaseHelper helper = new HbaseHelper();
        helper.put( "rk008", "c1", "hello put put", "f5" );
        helper.get( "rk008", "c1", "" );
        helper.delete( "rk004" );
        helper.scan( "c1", "", (long) 100);
    }
}
