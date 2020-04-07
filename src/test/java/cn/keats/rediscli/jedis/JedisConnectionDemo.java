package cn.keats.rediscli.jedis;

import cn.keats.rediscli.RedisCliApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: keats_coder
 * @Date: 2020/03/31
 * @Version 1.0
 */
@SpringBootTest(classes = RedisCliApplication.class)
@RunWith(SpringRunner.class)
public class JedisConnectionDemo {
    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private int port;


    @Test
    public void testConnection() {
        // 建立连接
        Jedis jedis = new Jedis(host, port);
        // 添加 key-value。添加成功则返回OK
        String setResult = jedis.set("name", "keats");
        Assert.assertEquals("OK", setResult);
        // 通过 key 获取 value
        String value = jedis.get("name");
        Assert.assertEquals("keats", value);
        // 关闭连接
        jedis.close();
    }

    @Test
    public void testConnectionWithPool() {
        // 创建连接池
        JedisPool jedisPool = new JedisPool(host, port);

        Jedis jedis = jedisPool.getResource();

        // doSomething

        // 归还连接
        jedis.close();
    }

    @Test
    public void testConnectionNotClose() {
        // 创建连接池
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxWaitMillis(5000L); // 等待Jedis连接超时时间
        JedisPool jedisPool = new JedisPool(poolConfig, host, port);

        try {
            for (int i = 1; i <= 10; i++) {
                Jedis jedis = jedisPool.getResource();
                System.out.println(i);
                // doSomething
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnectionWithException() {
        // 创建连接池
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxWaitMillis(5000L); // 等待Jedis连接超时时间
        JedisPool jedisPool = new JedisPool(poolConfig, host, port);

        for (int i = 1; i <= 8; i++) {
            System.out.println(i);
            try (Jedis jedis = jedisPool.getResource()) {
                new Thread(() -> {
                    // doSomething
                    // 模拟一个错误
                    int j = 1 / 0;

                    jedis.close();
                }).run();
            } catch (Exception e) {
                // 服务器运行过程中出现了8次异常，没有执行到close方法
            }
        }
        // 第9次无法获取连接
        Jedis jedis = jedisPool.getResource();
    }

    @Test
    public void testPipeline() {
        // 创建连接池
        JedisPool jedisPool = new JedisPool(host, port);
        try (Jedis jedis = jedisPool.getResource()){
            Pipeline pipelined = jedis.pipelined();
            // doSomething 获取 keys
            List<String> keys = new ArrayList<>();

            // pipelined 添加命令
            for (String key : keys) {
                pipelined.del(key);
            }
            // 执行命令
            pipelined.sync();
        }
    }
}
