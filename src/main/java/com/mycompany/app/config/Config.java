package com.mycompany.app.config;


import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.hadoop.hbase.HBaseConfiguration;

/**
 * Created by lzz on 6/15/16.
 */
public class Config {

    private org.apache.hadoop.conf.Configuration HConfig;

    public Config() {
        String resource = "/home/lzz/work/idea_work/hadoop_work/hbase_demo/src/main/java/com/mycompany/app/config/hbase_config.xml";
        Configuration config = null;
        try {
            config = new XMLConfiguration(resource);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

        this.HConfig = HBaseConfiguration.create();
        this.HConfig.set("hbase.zookeeper.property.clientPort", config.getString( "zookeeper.property.clientPort" ));
        this.HConfig.set("hbase.zookeeper.quorum", config.getString( "zookeeper.quorum" ) );
        this.HConfig.set("hbase.master", config.getString( "master" ) );
    }

    public org.apache.hadoop.conf.Configuration getHConfig() {
        return this.HConfig;
    }
}
