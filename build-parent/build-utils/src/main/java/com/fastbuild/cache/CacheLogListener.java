package com.fastbuild.cache;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 缓存的日志监听
 * Eviction and expiration  事件可由内部进程和用户执行针对高速缓存内的相关和不相关密钥的方法触发。
 * @auther xinch
 * @create 2018/1/25 11:11
 */
public class CacheLogListener implements CacheEventListener<Object, Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheLogListener.class);
    @Override
    public void onEvent(CacheEvent<?, ?> cacheEvent) {
        LOGGER.info("Event: " + cacheEvent.getType() + " Key: " + cacheEvent.getKey() + " old value: " + cacheEvent.getOldValue()
                + " new value: " + cacheEvent.getNewValue());
    }
}
