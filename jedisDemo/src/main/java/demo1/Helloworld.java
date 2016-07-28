package demo1;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/13.
 */
public class Helloworld {

    public static void print6port(){
        for (int i = 0; i < 6; i++) {
            System.out.println("port:"+(6379+i)+"...........");
            printAll("127.0.0.1",6379+i);
        }
    }
    public static void clear6port(){
        for (int i = 0; i < 6; i++) {
            System.out.println("port:"+(6379+i)+"...........");
//            clearAll("127.0.0.1",6379+i);
        }
    }
    public static void printAll(String ip,int port){
        Jedis jedis=new Jedis(ip,port);
        Set<String> keyList=jedis.keys("*");
        for (String s:keyList) {
            System.out.println(s);
            System.out.println(jedis.get(s));
        }
    }
    public static void printAll(){
        Jedis jedis=new Jedis("localhost");
        String s1=jedis.get("test2");
        System.out.println(s1);
        Set<String> keyList=jedis.keys("*");
        for (String s:keyList) {
            System.out.println(s);
        }
    }

    public static void printHset(String key){
        Jedis jedis=new Jedis("localhost");
        Map<String,String> result=jedis.hgetAll(key);
        System.out.println("username:"+result.get("username_"));
    }


    public static void yeshuf(){
        System.out.println("this is test github for windows");
    }

}
