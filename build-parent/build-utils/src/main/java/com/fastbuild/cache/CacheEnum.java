package com.fastbuild.cache;

import org.ehcache.Cache;

public interface CacheEnum {


    /**
     *  缓存资源池使用类型
     */
    enum CacheResourceType implements CacheEnum{
        none,
        head,
        disk,
        offhead;
    }
}
