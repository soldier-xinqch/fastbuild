package com.fastbuild;

import com.fastbuild.cache.CustomCacheManager;
import org.ehcache.Cache;

/**
 * @auther xinch
 * @create 2018/1/26 18:24
 */
public class TestCustomCache {

    public static void main(String[] args) {
        String cahceName = "MY_CACHE";
        CustomCacheManager customCacheManager = new CustomCacheManager("D:\\home\\ehcacheDir","TEST_MY_CACHE",cahceName);
        Cache cache = customCacheManager.getCache(cahceName, Object.class, Object.class);

        cache.put("testKey","xinqch_testCache");

        cache.put(1000,"xinqch_testCacheInteger");

        customCacheManager.closeManager();
    }
}
