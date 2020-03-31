package cn.keats.rediscli.laoqian;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Author: keats_coder
 * @Date: 2020/03/31
 * @Version 1.0
 */
public interface CallWithJedis {
    // 接口提供方法，由实现类来实现具体的业务逻辑
    void call(Jedis jedis);

    class RedisPool {
        private JedisPool pool;

        // 加载类的时候初始化连接池
        public RedisPool() {
            // 这里采用默认配置，也可以自定义配置
            this.pool = new JedisPool();
        }

        public void execute(CallWithJedis caller) {
            // 业务调用此方法，已经将获取的连接使用try with resource 写法包装。
            try (Jedis jedis = pool.getResource()) {
                caller.call(jedis);
            }
        }
    }
}
