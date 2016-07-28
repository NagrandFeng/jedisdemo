package TestLogin;

import java.sql.ResultSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Created by Administrator on 2016/7/15.
 */
public class Main {

    public static void main(String[] args) throws Exception{
        testLogin();
//        findAllKeys();
//            getValueByKey("user_25");

    }
    public static void testLogin() throws Exception{
        Mysql mysql=new Mysql();
        Redis redis=new Redis();
        ResultSet rs=null;
//正常业务的ID是通过UI的request.getParamenter()获取
        String id="28";
        String sql="select * from user where id="+id;
        String username;
        if(redis.hexists("user_"+id, "username_")){
            username=redis.hget("user_"+id, "username_");
            System.out.println("Welcome Redis! User "+username+" login success from redis");
        }else{
            rs=mysql.conn.createStatement().executeQuery(sql);
            if(rs.next()==false){
                System.out.println("Mysql no register, Please register first");
            }else{
                username=rs.getString("username");
                System.out.println("Welcome Mysql ! User "+username+" login success from mysql");
                redis.hset("user_"+id, "username_", username);
//30分钟未操作就过期
                redis.expire("user_"+id, 1*60);
            }
        }

    }
    public static void findAllKeys(){
        Redis redis=new Redis();
        Set<String> keyList=redis.keys("*");
        for (String s:keyList) {
            System.out.println(s);
        }
    }
    public static void getValueByKey(String key){
        Redis redis=new Redis();
        String res= redis.hget(key,"username_");
//        String res=redis.get(key);
        System.out.println(res);
    }
}
