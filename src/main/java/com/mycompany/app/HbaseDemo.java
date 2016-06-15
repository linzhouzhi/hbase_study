package com.mycompany.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;

/**
 * Created by lzz on 6/14/16.
 */
public class HbaseDemo {
    public static Configuration configuration;
    static {
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("hbase.zookeeper.quorum", "192.168.1.221,192.168.1.222,192.168.1.223");
        configuration.set("hbase.master", "192.168.1.226:60010");
    }

    public static void main(String[] args) {
        //createTable("emp2");
        //insertData("user");
        // QueryAll("wujintao");
        // QueryByCondition1("wujintao");
        // QueryByCondition2("wujintao");
        //QueryByCondition3("wujintao");
        deleteRow("user","rk002");
        scanTable( "user" );
    }

    public static void scanTable( String tableName ){
        HTable table= null;
        try {
            table = new HTable(configuration,tableName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scan s =new Scan();
        try {
            ResultScanner rs = table.getScanner(s);
            for (Result r : rs) {
                KeyValue[] kv = r.raw();
                for (int i =0; i < kv.length; i++) {
                    System.out.print(new String(kv[i].getRow()) +"");
                    System.out.print(new String(kv[i].getFamily()) +":");
                    System.out.print(new String(kv[i].getQualifier()) +"");
                    System.out.print(kv[i].getTimestamp() +"");
                    System.out.println(new String(kv[i].getValue()));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createTable(String tableName) {
        System.out.println("start create table ......");
        try {
            HBaseAdmin hBaseAdmin = new HBaseAdmin(configuration);
            /*
            if (hBaseAdmin.tableExists(tableName)) {// 如果存在要创建的表，那么先删除，再创建
                hBaseAdmin.disableTable(tableName);
                hBaseAdmin.deleteTable(tableName);
                System.out.println(tableName + " is exist,detele....");
            }
            */
            HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf( tableName ));
            tableDescriptor.addFamily(new HColumnDescriptor("column1"));
            tableDescriptor.addFamily(new HColumnDescriptor("column2"));
            tableDescriptor.addFamily(new HColumnDescriptor("column3"));
            hBaseAdmin.createTable(tableDescriptor);
            System.out.println("end create table ......2");

        } catch (MasterNotRunningException e) {
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertData(String tableName) {
        System.out.println("start insert data ......");
        //HTablePool pool = new HTablePool(configuration, 1000);
        //HTable table = (HTable) pool.getTable(tableName);
        HTable table= null;
        try {
            table = new HTable(configuration,tableName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Put put = new Put("rk003".getBytes());// 一个PUT代表一行数据，再NEW一个PUT表示第二行数据,每行一个唯一的ROWKEY，此处rowkey为put构造方法中传入的值
        put.add("c1".getBytes(), null, "aaa".getBytes());// 本行数据的第一列
        put.add("c2".getBytes(), null, "bbb".getBytes());// 本行数据的第三列

        try {
            table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("end insert data ......");
    }

    public static void deleteRow(String tablename, String rowkey)  {
        try {
            HTable table = new HTable(configuration, tablename);
            List list = new ArrayList();
            Delete d1 = new Delete(rowkey.getBytes());
            list.add(d1);

            table.delete( list );
            System.out.println("删除行成功!");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
