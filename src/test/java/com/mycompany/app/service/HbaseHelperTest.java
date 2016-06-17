package com.mycompany.app.service;

import org.junit.*;

/**
 * Created by lzz on 6/15/16.
 */
public class HbaseHelperTest {

    static com.mycompany.app.service.HbaseHelper helper;

    @BeforeClass
    public static void setUp(){
        helper = new com.mycompany.app.service.HbaseHelper();
    }

    @Before
    public void before(){
        System.out.printf("before");
    }
    @After
    public void after(){
        System.out.printf("after");
    }

    @Test(timeout = 3000)
    public void testGet(){
        helper.get( "rk004", "", "" );
        //System.out.printf("hello wolrd" + helper.toString() );
    }

    @Test
    public void testHello(){
        System.out.printf("helo");
    }

    @Ignore
    @Test
    public  void testIgnore(){
        System.out.printf("test ignore");
    }

    @AfterClass
    public static void tearDown(){
        System.out.printf("tear down");
    }
}
