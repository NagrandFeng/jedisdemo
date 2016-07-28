package javaCache;

/**
 * Created by Administrator on 2016/7/26.
 */
public class JavaCache {
    public static void main(String[] args) {
        Integer a = 1000, b = 1000;
        System.out.println(a == b);//1
        Integer c = 100, d = 100;
        System.out.println(c == d);//2
    }
}
