package JedisDemo;

import common.Redis;

/**
 * Created by Administrator on 2016/7/19.
 */
public class JedisStart {
    static Redis redis=new Redis();

    public static void main(String[] args) {
        System.out.println(redis.get("*"));
    }
}
