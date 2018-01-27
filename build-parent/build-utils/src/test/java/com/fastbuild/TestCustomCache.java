package com.fastbuild;

import com.fastbuild.cache.CustomCacheManager;
import com.fastbuild.common.CheckOsInfo;
import com.fastbuild.common.EPlatform;
import org.ehcache.Cache;

/**
 * @auther xinch
 * @create 2018/1/26 18:24
 */
public class TestCustomCache {

    public static void main(String[] args) {
        String cahceName = "MY_CACHE";
        String cacheBaseDir = "";
        EPlatform platform = CheckOsInfo.getOSname();
        if(platform.equals(EPlatform.Windows)){
            cacheBaseDir = "D:\\home\\ehcacheDir\\";
        }else if (platform.equals(EPlatform.Mac_OS)||platform.equals(EPlatform.Mac_OS_X)||platform.equals(EPlatform.Linux)){
            String usrHome = System.getProperty("user.home");
            cacheBaseDir = usrHome+"/ehcacheDir/";
        }else{
            throw new RuntimeException("当前系统不匹配，此仅支持 windows，linux ，mac");
        }
        CustomCacheManager customCacheManager = new CustomCacheManager(cacheBaseDir,"TEST_MY_CACHE",cahceName);
        Cache cache = customCacheManager.getCache(cahceName, String.class, String.class);

//        cache.put("testKey","xinqch_testCache");
//
//        cache.put("100000","xinqch_testCacheInteger");
        System.out.println("args = [" + cache.get("100000") + "]");
        System.out.println("args = [" + cache.get("testKey") + "]");

        customCacheManager.closeManager();
    }
}
