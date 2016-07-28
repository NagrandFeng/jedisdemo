package shardedJedisDemo;

import demo1.Helloworld;
import redis.clients.jedis.*;
import redis.clients.util.ShardInfo;
import sun.security.krb5.Config;

import java.util.*;

/**
 * Created by Administrator on 2016/7/19.
 */
public class ShardedJedisTest {
    public static void main(String[] args) {
        //                directUseShard();
        //        testLinkHashMap();
//        byPoolUseShard();
//        Helloworld.print3port();
//        Helloworld.clear6port();
//        Helloworld.clearAll("127.0.0.1",6379);
//        Helloworld.setKey();
//        Helloworld.printAll();
    }

    public static void byPoolUseShard() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(2);
        poolConfig.setMaxIdle(1);
        poolConfig.setMaxWaitMillis(5000);
        poolConfig.setTestOnBorrow(false);
        poolConfig.setTestOnReturn(false);

        //设置Redis信息
        String host = "127.0.0.1";
        //        String hostB="172.17.21.26";
        JedisShardInfo shardInfo1 = new JedisShardInfo(host, 6379, 500);
        JedisShardInfo shardInfo2 = new JedisShardInfo(host, 6380, 500);
        JedisShardInfo shardInfo3 = new JedisShardInfo(host, 6381, 500);

        //        JedisShardInfo shardInfo4 = new JedisShardInfo(hostB, 6379, 500);

        //        JedisShardInfo shardInfo5 = new JedisShardInfo(host, 6379, 500);


        //初始化ShardedJedisPool
        List<JedisShardInfo> infoList = Arrays.asList(shardInfo1, shardInfo2, shardInfo3);
        //        List<JedisShardInfo> infoList = Arrays.asList(shardInfo1, shardInfo2,shardInfo4);
        //        List<JedisShardInfo> infoList = Arrays.asList(shardInfo1, shardInfo2);
        ShardedJedisPool jedisPool = new ShardedJedisPool(poolConfig, infoList);
        ShardedJedis jedis = jedisPool.getResource();

      /*  String key = "key";
        String value = "value";
        Client client = null;
        int count_6379 = 0, count_6380 = 0, count_6381 = 0;
        for (int i = 0; i <= 999; i++) {
            jedis.set(key + String.valueOf(i), value + String.valueOf(i));
            client = jedis.getShard(key + String.valueOf(i)).getClient();
            if (client.getPort() == 6379) {
                count_6379++;
            } else if (client.getPort() == 6380) {
                count_6380++;
            } else {
                count_6381++;
            }
            System.out.println(key + String.valueOf(i) + " in server:" + client.getHost() + " and port is:" + client.getPort());

        }
        System.out.println("port 6379 key count:" + count_6379);
        System.out.println("port 6380 key count:" + count_6380);
        System.out.println("port 6381 key count:" + count_6381);*/
        testgetShard(jedis);

        jedis.close();
    }

    public static void testgetShard(ShardedJedis jedis){

//      Jedis redis=jedis.getShard("key200");
        System.out.println(jedis.get("key200"));
//      System.out.println(redis.get("*"));
    }

    public static void directUseShard() {
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        JedisShardInfo si = new JedisShardInfo("127.0.0.1", 6379);
        shards.add(si);
        //        si = new JedisShardInfo("172.20.20.2", 6379);
        si = new JedisShardInfo("localhost", 6380);
        shards.add(si);
        //        ShardedJedis jedis = new ShardedJedis(shards);
        ShardedJedis jedis = new ShardedJedis(shards, ShardedJedis.DEFAULT_KEY_TAG_PATTERN);
        jedis.set("Foo{key1}", "asdasd1");
        jedis.set("test{tsd1}", "test2");
        //        jedis.set("object", "foo-object");
        System.out.println(jedis.get("Foo{key1}"));
        ShardInfo ss = jedis.getShardInfo("test");
        System.out.println(ss);
        jedis.disconnect();
    }

    public static void testLinkHashMap() {
        int c = 1;
        c <<= 1;
        System.out.println(c);
    }
}
