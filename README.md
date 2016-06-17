# hbase_study

* hbase 常用命令

1）创建表

create 'test','cf'

2）修改

添加两个列族cf1和cf2

alter 'test', NAME => 'cf1'

alter 'test', NAME => 'cf2'

3）插入

put 'test','row0', 'cf', 'value0'

put 'test','row1','cf:a','value1'

put 'test','row1','cf1:b','value2'

put 'test','row1','cf2:c','value3'

3）修改

添加两个列族cf1和cf2

alter 'test', NAME => 'cf1'

alter 'test', NAME => 'cf2'

4）查找

当行查询

get 'test','row1'

get 'test','row1','cf'

get 'test','row1','cf:b'

get 'test','row1',{COLUMN => 'cf:b', TIMESTAMP => 1465975393290}

全表扫描

scan 'test'

scan 'test',{COLUMNS => 'cf:a'}

scan 'test',{COLUMNS => ['cf','cf1'], LIMIT => 3}

5）删除

删除行

deleteall 'test','row0'

删除列限制符

delete 'test','row1','cf2:c'

删除列

deleteall 'test','row1','cf1'

删除表

disable 'test'

drop 'test'

6）常用方法

查看有什么表

list

查看表的结构

describe 'test'

查看表有多少行

count 'test'

清空表中的数据

truncate 'test'

判断表是否存在

exists 'test'

查看hbase状态

status

版本version

帮助主题表

table_help

* hbase 例子可以在官网api中选择 Overview 下面有packages->org.apache.hadoop.hbase.client（点击就有啦）

# 目录结构说明

com.mycompany.app/

config : hbase配置文件信息

service: hbase操作类为dao成提供服务

weibo:   微薄项目

