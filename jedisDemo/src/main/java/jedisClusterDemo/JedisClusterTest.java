package jedisClusterDemo;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/21.
 */
public class JedisClusterTest {
    public static void main(String[] args) throws IOException{
        JedisPoolConfig config=new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(2);

        String host="localhost";
//        HostAndPort hp0=new HostAndPort(host,6379);
        HostAndPort hp1=new HostAndPort(host,6380);
        HostAndPort hp2=new HostAndPort(host,6381);
        HostAndPort hp3=new HostAndPort(host,6382);
        HostAndPort hp4=new HostAndPort(host,6383);
        HostAndPort hp5=new HostAndPort(host,6384);

        Set<HostAndPort> hps=new HashSet<HostAndPort>();
//        hps.add(hp0);
        hps.add(hp1);
        hps.add(hp2);
        hps.add(hp3);
        hps.add(hp4);
        hps.add(hp5);

        JedisCluster jedisCluster = new JedisCluster(hps, 5000, 10, config);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            jedisCluster.set("sn" + i, "n" + i);
        }
        long end = System.currentTimeMillis();

        System.out.println("Simple  @ Sharding Set : " + (end - start));
        for (int i = 0; i < 1000; i++) {
            System.out.println(jedisCluster.get("sn" + i));
        }
        jedisCluster.close();
    }
}
