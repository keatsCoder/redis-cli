package cn.keats.rediscli.laoqian;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import redis.clients.jedis.Jedis;

/**
 * @Author: keats_coder
 * @Date: 2020/03/31
 * @Version 1.0
 */
public class JedisTest{
    // 原始写法
//    public static void main(String[] args) {
//        CallWithJedis.RedisPool redis = new CallWithJedis.RedisPool();
//        redis.execute(new CallWithJedis() {
//            @Override
//            public void call(Jedis jedis) {
//            // do something with jedis
//            }
//        });
//    }

    // λ写法
//    public static void main(String[] args) {
//        CallWithJedis.RedisPool redis = new CallWithJedis.RedisPool();
//        redis.execute(jedis -> {
//            // do something with jedis
//        });
//    }

    // 使用Holder类型将变量带出来
    public static void main(String[] args) {
        CallWithJedis.RedisPool redis = new CallWithJedis.RedisPool();
        Holder<Long> countHolder = new Holder<>();
        redis.execute(jedis -> {
            long count = jedis.zcard("codehole");
            countHolder.value(count);
        });
        System.out.println(countHolder.value());
    }

}
