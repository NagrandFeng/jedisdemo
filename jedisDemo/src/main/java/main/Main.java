package main;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

/**
 * Created by Administrator on 2016/7/21.
 */
public class Main {
    public static JedisPool jedisPool;
    public static Jedis jedis = null;

    static {
        //读取相关的配置
        String ip = "127.0.0.1";
        int port = 7000;
        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大连接数
        config.setMaxTotal(100);
        //设置最大空闲数
        config.setMaxIdle(20);
        //设置超时时间
        config.setMaxWaitMillis(3000);
        //初始化连接池
        jedisPool = new JedisPool(config, ip, port);
    }

    public static void main(String[] args) {
        /*String ip = "127.0.0.1";
        int port = 7000;
        Jedis jedis=new Jedis(ip,port);*/
        jedis = jedisPool.getResource();
//        jedis.set("name", "this is name");
//        mSet(jedis);
//        System.out.println(get(jedis, "name"));
//        hset(jedis);
//        hget(jedis, "person", "age");
//        set10Hash(jedis);
//        printAllkey(jedis);
//        subStr(jedis);
        System.out.println(jedis.hkeys("student1"));
    }

    public static void mSet(Jedis jedis) {
        jedis.mset("name", "t1", "age", "15");
    }

    public static String get(Jedis jedis, String key) {
        String value = jedis.get(key);
        return value;
    }

    public static void printAllkey(Jedis jedis) {
        Set<String> keys = jedis.keys("*");
        printSet(keys);
    }

    public static void printHkey(Jedis jedis, String key) {
        Set<String> keys = jedis.hkeys(key);
        printSet(keys);
    }

    public static void printSet(Set<String> keys) {
        System.out.println("all key list");
        for (String key : keys) {
            System.out.println(key);
        }
        System.out.println("------------");
    }

    public static void subStr(Jedis jedis) {
        System.out.println(jedis.substr("name", 1, 2));

    }

    public static void hset(Jedis jedis) {
        String person = "person1";
        String feild = "name";
        String age = "ttsd1";
        jedis.hset(person, feild, age);
    }

    public static void hget(Jedis jedis, String key, String feild) {
        System.out.println("key : " + key + " feild : " + feild + "\n value : " + jedis.hget(key, feild));
    }

    public static void set10Hash(Jedis jedis) {
        for (int i = 0; i < 10; i++) {
            int age = 20 + i;
            jedis.hset("student" + i, "name" + i, String.valueOf(age));
        }
    }
}
