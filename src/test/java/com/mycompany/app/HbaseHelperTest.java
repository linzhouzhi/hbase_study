package com.mycompany.app;

import org.junit.*;

/**
 * Created by lzz on 6/15/16.
 */
public class HbaseHelperTest {

    static HbaseHelper helper;

    @BeforeClass
    public static void setUp(){
        helper = new HbaseHelper();
    }

    @Before
    public void before(){
        System.out.printf("before");
    }
    @After
    public void after(){
        System.out.printf("after");
    }

    @Test
    public void testGet(){
        helper.get( "rk008", "c1", "" );
        //System.out.printf("hello wolrd" + helper.toString() );
    }

    @Test
    public void testHello(){
        System.out.printf("helo");
    }

    @AfterClass
    public static void tearDown(){
        System.out.printf("tear down");
    }
}
