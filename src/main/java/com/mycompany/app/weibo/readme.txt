# weibo系统表的设计

1 user 用户表
rowkey:nick_name 昵称（唯一）
column family: user_name, email,passwd
value: {1:user_name,1:email,1:passwd}
创建表：create 'user','f'

2 weibo 表
rowkey: md5(nick_name) + re_time(倒序时间Long.MAX_VALUE-当前时间)
cf: f
value: {user_name:content} (名字为key 微薄内容是value)
创建表：create 'weibo','f'

3 follows 用户关注表
* 宽表设计
rowkey: nick_name
cf: follows
values: {followed001:1,followed002:2,followed003:3.......}
缺点：要查询某个用户关注了哪个用户必须全部查询出来后过滤
优点：put操作有原子性

*高表设计
1）
rowkey: follower+follwed(关注者[nick_name]+被关注者)
cf:f(短列限制符减少io开销)
value: {name:1}

2）改进版
rowkey: md5(nick_name1)md5(nick_name2)
优点：容易操作计算起始和停止键，md5散列键会使数据更均匀分布到region中
cf:f
value: {nick_name:user_name}(nick_name 是被关注者，因为rowkey md5加密后scan 某个用户打所有关注者不能从rowkey中获取)
创建表：create 'follows','f'