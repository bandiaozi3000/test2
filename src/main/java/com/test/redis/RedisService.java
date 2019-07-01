package com.test.redis;

import com.alibaba.fastjson.JSON;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description: Redis工具类
 *
 */

@Component
public class RedisService{

    /**
     * 日志记录
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisService.class);

    /**
     * Try not to use it
     */
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Redisson redisson;
    /**
     * 缓存前缀
     */
    public static final String KEY_PREFIX = "JB:";


    /**
     * 保存数据
     * @param key 键
     * @param value 值
     * @return
     */
    public boolean set(String key ,String value) {
        try {
            stringRedisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    /**
     * 保存数据 对象转json后存储
     * @param key 键
     * @param value 值
     * @return
     */
    public boolean set(String key ,Object value) {
        try {
            String s = JSON.toJSONString(value);
            stringRedisTemplate.opsForValue().set(key, s);
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    /**
     * 保存数据
     * @param key 键
     * @param value 值
     * @param second 时间（秒）
     * @return
     */
    public boolean set(String key, String value, long second) {
        try {
            stringRedisTemplate.opsForValue().set(key, value, second, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    /**
     * 保存数据  对象转json后存储
     * @param key 键
     * @param value 值
     * @param second 时间（秒）
     * @return
     */
    public boolean set(String key, Object value, long second) {
        try {
            String s = JSON.toJSONString(value);
            stringRedisTemplate.opsForValue().set(key, s, second, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    /**
     * 获取值
     * @param key 键
     * @return
     */
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 获取值 obj 泛型T
     * @param key 键
     * @return obj
     */
    public <T> T getObj(String key, Class<T> clazz) {
        String s = stringRedisTemplate.opsForValue().get(key);

        return JSON.parseObject(s, clazz);
    }

    /**
     * 获取集合值 泛型T
     * @param key 键
     * @return obj
     */
    public <T> List<T> getList(String key, Class<T> clazz) {
        String s = stringRedisTemplate.opsForValue().get(key);

        return JSON.parseArray(s,clazz);
    }

    /**
     * 根据key删除
     * @param key
     * @return
     */
    public boolean delete(String key) {
        try {
            stringRedisTemplate.delete(key);
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    /**
     * 模糊删除
     * @param prefix 前缀
     * @return
     */
    public boolean deleteOfPrefix(String prefix) {
        try {
            Set<String> keys = stringRedisTemplate.keys(prefix + "*");
            if (!CollectionUtils.isEmpty(keys)) {
                stringRedisTemplate.delete(keys);
                return true;
            }
            return false;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    /**
     * 查询key是否存在
     * @param key
     * @return
     */
    public boolean exist(String key) {
        try {
            return stringRedisTemplate.hasKey(key);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    /**
     * 设置过期时间
     * @param key 键
     * @param second 秒
     * @return
     */
    public boolean expire(String key, long second) {
        try {
            return stringRedisTemplate.expire(key, second, TimeUnit.SECONDS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    public Long increment(String key,long l){
        Long increment = stringRedisTemplate.opsForValue().increment(key, l);

        return increment == null?null:new Long(increment);
    }


    /**
     * 创建锁对象
     * @param name
     * @return
     */
    public RLock getLock(String name){
        RLock lock = redisson.getLock(name);

        return lock;
    }


    /**
     * 由传入锁对象取得锁 时间单位：Second
     * @param lock
     * @param waitTime  获取锁最大等待时间
     * @param leaseTime 锁有效时间
     */
    public boolean tryLock(RLock lock,long waitTime, long leaseTime){
        try {
            if (lock == null) return false;
            boolean res = lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
            return res;
        } catch (InterruptedException e) {
            LOGGER.error("获取锁出错{}", e);
            return false;
        }
    }

    /**
     * 由传入锁对象取得锁 时间单位：Second
     * @param lock
     *  -1s  获取锁最大等待时间
     *  10s  锁有效时间
     */
    public boolean tryLock(RLock lock){
        try {
            if (lock == null) return false;
            boolean res = lock.tryLock(-1, 10, TimeUnit.SECONDS);
            return res;
        } catch (InterruptedException e) {
            LOGGER.error("获取锁出错{}", e);
            return false;
        }
    }


    /**
     * 由传入锁对象释放锁
     * @param lock
     * @return
     */
    public void unlock(RLock lock){
        if (lock == null) return;
        lock.unlock();
    }




}
