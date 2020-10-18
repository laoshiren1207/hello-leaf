## Leaf 

> There are no two identical leaves in the world.
>
> 世界上没有两片完全相同的树叶。
>
> — 莱布尼茨

### 类snowflake方案

这种方案大致来说是一种以划分命名空间（`UUID`也算，由于比较常见，所以单独分析）来生成ID的一种算法，这种方案把64-bit分别划分成多段，分开来标示机器、时间等，比如在`snowflake`中的64-bit分别表示如下图（图片来自网络）所示：

![](https://static.oschina.net/uploads/space/2018/0526/141205_FovT_12.png)

41-bit的时间可以表示（1L<<41）/(1000L*3600*24*365)=69年的时间，10-bit机器可以分别表示1024台机器。如果我们对IDC划分有需求，还可以将10-bit分5-bit给IDC，分5-bit给工作机器。这样就可以表示32个IDC，每个IDC下可以有32台机器，可以根据自身需求定义。12个自增序列号可以表示2^12个ID，理论上`snowflake`方案的QPS约为409.6w/s，这种分配方式可以保证在任何一个`IDC`的任何一台机器在任意毫秒内生成的ID都是不同的。

这种方式的优缺点是：

优点：

- 毫秒数在高位，自增序列在低位，整个`ID`都是趋势递增的。
- 不依赖数据库等第三方系统，以服务的方式部署，稳定性更高，生成ID的性能也是非常高的。
- 可以根据自身业务特性分配`bit`位，非常灵活。

缺点：

- 强依赖机器时钟，如果机器上时钟回拨，会导致发号重复或者服务会处于不可用状态。

### Leaf-snowflake方案

`Leaf-snowflake`方案完全沿用 `snowflake` 方案的 `bit` 位设计，即是 “1+41+10+12” 的方式组装 `ID` 号。对于 `workerID` 的分配，当服务集群数量较小的情况下，完全可以手动配置。`Leaf` 服务规模较大，动手配置成本太高。所以使用 `Zookeeper` 持久顺序节点的特性自动对 `snowflake` 节点配置 `wokerID`。

本人在`github`上找到了一个简单易用的`docker`容器。地址[](https://github.com/laoShiRen1207/leaf-compose)

#### 使用
~~~shell 
# 编译
git clone https://github.com/laoShiRen1207/leaf-compose.git
cd Leaf
mvn clean install -DskipTests
# 构建镜像
cd leaf-docker
chmod +x build.sh
./build.sh
# 启动服务
docker-compose up -d

# 测试
curl http://localhost:12580/api/snowflake/get/test
# 输出
1278888624527507458
~~~

### 此项目的作用
本项目是为了解决内部更具方便的去请求`leaf`服务，相当于`xxxxTemplate`项目，是一个简单开箱即用的一个依赖。
首先，需要配置公司的`nexus`环境，引入如下依赖。

~~~xml 
<dependency>
    <groupId>com.laoshiren.hello.leaf</groupId>
    <artifactId>leaf-id-generator</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
~~~
配置文件
~~~yaml 
spring:
  laoshiren:
    leaf:
      host: http:120.79.0.210
      port: 12580
      url: api/snowflake/get/test
~~~
~~~java 
@Autowired
private LeafGenerator leafGenerator;

@Test
public void leaf() throws Exception {
    System.out.println(leafGenerator.leafSnowFlakeIdGenerate());
}
~~~
~~~shell 
# 输出如下
2020-07-03 10:45:25.225  INFO 15128 --- [           main] c.l.h.l.api.impl.DefaultLeafGenerator    :  get request --> http:120.79.0.210:12580/api/snowflake/get/test
2020-07-03 10:45:25.659  INFO 15128 --- [           main] c.l.h.l.api.impl.DefaultLeafGenerator    :  get response --> 1278882506606641172
~~~

解决了分布式ID，就可以着手解决分库分表 [项目地址](http://git.pharmakeyring.com:9091/xiangdehua/hello-apache-shardingsphere)