package com.fastbuild.cache;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.*;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.event.CacheEventListener;
import org.ehcache.event.EventFiring;
import org.ehcache.event.EventOrdering;
import org.ehcache.event.EventType;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.spi.service.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 自定义缓存控制器
 *
 * @auther xinch
 * @create 2018/1/26 16:34
 */
public class CustomCacheManager {

    /**
     *  缓存持久化 落地目录
     */
    private String diskFilePath;

    /**
     *  缓存持久化 目录名称
     */
    private String diskAppName;

    /**
     *  缓存器名称
     */
    private String cacheName;

    /**
     *  默认缓存器名称
     */
    private String defaultCacheName = "TEST_CACHE";

    /**
     *  缓存控制器
     */
    private CacheManager cacheManager;

    public CustomCacheManager(String diskFilePath,String diskAppName,String cacheName) {
        this.diskFilePath = diskFilePath;
        this.diskAppName = diskAppName;
        this.cacheName = cacheName;
        // 注册事件监听
        CacheEventListenerConfigurationBuilder cacheEventListenerConfiguration = this.setEventListers(new CacheLogListener(), EventType.CREATED, EventType.UPDATED);
        // 初始化 缓存控制器
        CacheManagerBuilder<CacheManager> cacheBuilder = CacheManagerBuilder.newCacheManagerBuilder();
        // 设置持久化目录
        this.setDiskPersistence(cacheBuilder);
        // 设置线程池
        Map<String, Integer[]> poolMap = new HashMap<>();
        poolMap.put("defaultEventPool", new Integer[]{1, 3});
        poolMap.put("cache2Pool", new Integer[]{2, 2});
        this.setThreadPool(cacheBuilder, poolMap);
        // 构建缓存器
        Map<CacheEnum.CacheResourceType, ResourceTypeModel> resourceMap = new HashMap<>();
        resourceMap.put(CacheEnum.CacheResourceType.head, new ResourceTypeModel(10L, null));
        resourceMap.put(CacheEnum.CacheResourceType.offhead, new ResourceTypeModel(10L, MemoryUnit.MB));
        resourceMap.put(CacheEnum.CacheResourceType.disk, new ResourceTypeModel(400L, MemoryUnit.MB));
        ResourcePoolsBuilder resourcePoolsBuilder = this.setResourcePolls(CacheEnum.CacheResourceType.none, resourceMap);

        CacheConfigurationBuilder configBuilder = this.buildCacheConfig(Object.class, Object.class, resourcePoolsBuilder);
        configBuilder.withExpiry(Expirations.timeToLiveExpiration(Duration.of(20, TimeUnit.SECONDS))); // 设置过期时间
        // 设置过期时间
        setExpiryOfCache(configBuilder,3600L,TimeUnit.SECONDS);
        // 添加事件监听
        addEventListenerOfCache(configBuilder,cacheEventListenerConfiguration);
        // 指出所需的并发级别
        setConcurrencyOfCache(configBuilder,10);
        // 设置默认使用线程池
        setThreadPoolOfCache(configBuilder,"cache2Pool",2);

        if (StringUtils.isEmpty(this.cacheName)) cacheName = defaultCacheName;
        // 设置 缓存器
        this.setCache(cacheBuilder,cacheName ,configBuilder);
        cacheManager = cacheBuilder.build(true);
        System.out.println("diskFilePath = [" + diskFilePath + "], diskAppName = [" + diskAppName + "], cacheName = [" + cacheName + "]");
    }

    public void closeManager(){
        this.cacheManager.close();;
    }

    /**
     *  获取缓存器
     * @param cacheName
     * @param keyCls
     * @param valCls
     * @return
     */
    public Cache getCache(String cacheName,Class keyCls,Class valCls){
        return this.cacheManager.getCache(cacheName,keyCls,valCls);
    }

    /**
     *  在获取缓存器后设置缓存事件监听
     * @param cache
     * @param listener
     * @param EventTypes
     */
    public void setCacheEventListener(Cache cache,CacheEventListener<?, ?> listener, Set<EventType> EventTypes){
        cache.getRuntimeConfiguration().registerCacheEventListener(listener, EventOrdering.ORDERED,
                EventFiring.ASYNCHRONOUS, EventTypes);
    }

    /**
     *  注销在使用过程中注入的监听器
     * @param cache
     * @param listener
     */
    public void destoryCacheEventListener(Cache cache,CacheEventListener<?, ?> listener){
        cache.getRuntimeConfiguration().deregisterCacheEventListener(listener);
    }


    /**
     *  设置缓存键值 过期时间
     * @param builder
     * @param expiryNum
     * @param timeType
     */
    public void setExpiryOfCache(CacheConfigurationBuilder builder, Long expiryNum, TimeUnit timeType) {
        builder.withExpiry(Expirations.timeToLiveExpiration(Duration.of(expiryNum, timeType))); // 设置过期时间
    }

    /**
     *  设置缓存并发数
     * @param builder
     * @param ConcurrencyNum
     */
    public void setConcurrencyOfCache(CacheConfigurationBuilder builder, Integer ConcurrencyNum) {
        builder.withDispatcherConcurrency(ConcurrencyNum); // 指出所需的并发级别
    }

    /**
     *  设置缓存事件监听
     * @param builder
     * @param eventListenerConfigurationBuilder
     */
    public void addEventListenerOfCache(CacheConfigurationBuilder builder, CacheEventListenerConfigurationBuilder eventListenerConfigurationBuilder) {
        builder.add(eventListenerConfigurationBuilder); // 添加事件监听
    }

    /**
     *  设置缓存使用线程池
     * @param builder
     * @param poolName
     * @param concurrency
     */
    public void setThreadPoolOfCache(CacheConfigurationBuilder builder, String poolName,int concurrency) {
        builder.withDiskStoreThreadPool(poolName, concurrency); // 设置缓存使用的 线程池及并发数
    }

    /**
     * 设置资源池
     * 其中 堆外内存 默认为 MB
     *
     * @param cacheResourceType
     * @return
     */
    public ResourcePoolsBuilder setResourcePolls(CacheEnum.CacheResourceType cacheResourceType, Map<CacheEnum.CacheResourceType, ResourceTypeModel> resourceMap) {
        switch (cacheResourceType) {
            case head:
                return ResourcePoolsBuilder.newResourcePoolsBuilder().heap(resourceMap.get(CacheEnum.CacheResourceType.head).getSize(), resourceMap.get(CacheEnum.CacheResourceType.head).getEntryUnit());
            case disk:
                return ResourcePoolsBuilder.newResourcePoolsBuilder().disk(resourceMap.get(CacheEnum.CacheResourceType.disk).getSize(), resourceMap.get(CacheEnum.CacheResourceType.disk).getMemoryUnit(), true);
            case offhead:
                return ResourcePoolsBuilder.newResourcePoolsBuilder().offheap(resourceMap.get(CacheEnum.CacheResourceType.offhead).getSize(), resourceMap.get(CacheEnum.CacheResourceType.offhead).getMemoryUnit());
            default:
                return ResourcePoolsBuilder.newResourcePoolsBuilder()
                        .heap(resourceMap.get(CacheEnum.CacheResourceType.head).getSize(), resourceMap.get(CacheEnum.CacheResourceType.head).getEntryUnit())
                        .offheap(resourceMap.get(CacheEnum.CacheResourceType.offhead).getSize(), resourceMap.get(CacheEnum.CacheResourceType.offhead).getMemoryUnit())
                        .disk(resourceMap.get(CacheEnum.CacheResourceType.disk).getSize(), resourceMap.get(CacheEnum.CacheResourceType.disk).getMemoryUnit(), true);
        }
    }

    /**
     *  构建缓存配置
     * @param keyCls
     * @param valCls
     * @param resourcePoolsBuilder
     * @return
     */
    private CacheConfigurationBuilder  buildCacheConfig(Class keyCls,Class valCls,ResourcePoolsBuilder resourcePoolsBuilder){
        return CacheConfigurationBuilder.newCacheConfigurationBuilder(keyCls, valCls, resourcePoolsBuilder);
    }

    /**
     *  构建缓存器
     * @param builder
     * @param cacheName
     * @param cacheConfigurationBuilder
     */
    public void setCache(CacheManagerBuilder<CacheManager> builder,String cacheName,CacheConfigurationBuilder cacheConfigurationBuilder){
        builder.withCache(cacheName, cacheConfigurationBuilder);
    }

    /**
     *  设置线程池
     * @param builder
     * @param poolMap
     */
    public void setThreadPool(CacheManagerBuilder<CacheManager> builder, Map<String,Integer[]> poolMap){
        if(MapUtils.isEmpty(poolMap)) return;
        PooledExecutionServiceConfigurationBuilder poolBuilder = PooledExecutionServiceConfigurationBuilder.newPooledExecutionServiceConfigurationBuilder();
        for(String key :poolMap.keySet()){
            Integer[] val = poolMap.get(key);
            poolBuilder.pool(key,val[0],val[1]);
        }
        builder.using(poolBuilder.build())
                .withDefaultEventListenersThreadPool("defaultEventPool");
    }

    /**
     *  设置缓存落地目录
     * @param builder
     */
    public void setDiskPersistence(CacheManagerBuilder<CacheManager> builder){
        builder.with(CacheManagerBuilder.persistence(new File(getStoragePath(), this.diskAppName)));
    }


    /**
     *  设置事件监听
     * @param listener
     * @param eventType
     * @param eventTypes
     * @return
     */
    public CacheEventListenerConfigurationBuilder setEventListers(CacheEventListener<?, ?> listener, EventType eventType, EventType... eventTypes) {
       return  CacheEventListenerConfigurationBuilder
                // CacheEventListenerConfiguration使用构建器创建一个指示侦听器和要接收的事件（在这种情况下，创建和更新事件）
                .newEventListenerConfiguration(listener, eventType, eventTypes)
                // 可选地指示交付模式 - 默认值是异步的和无序的（出于性能原因）
                .unordered().asynchronous();
    }

    /**
     *  设置缓存持久化落地目录
     * @return
     */
    private File getStoragePath() {
        if(StringUtils.isEmpty(this.diskFilePath)) throw new RuntimeException("创建缓存控制器失败：缓存落地目录为空");
        return new File(diskFilePath);
    }

    public void setDiskFilePath(String diskFilePath) {
        this.diskFilePath = diskFilePath;
    }

    public void setDiskAppName(String diskAppName) {
        this.diskAppName = diskAppName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    public CacheManager getCacheManager() {
        return cacheManager;
    }


//    public void createCacheManager(){
//        // 缓存的事件监听
//        CacheEventListenerConfigurationBuilder cacheEventListenerConfiguration = CacheEventListenerConfigurationBuilder
//                // CacheEventListenerConfiguration使用构建器创建一个指示侦听器和要接收的事件（在这种情况下，创建和更新事件）
//                .newEventListenerConfiguration(new CacheLogListener(), EventType.CREATED, EventType.UPDATED)
//                // 可选地指示交付模式 - 默认值是异步的和无序的（出于性能原因）
//                .unordered().asynchronous();
//
//        PersistentCacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
//                .with(CacheManagerBuilder.persistence(new File(getStoragePath(), "myData")))
//                .using(newPooledExecutionServiceConfigurationBuilder()
//                        .pool("defaultEventPool", 1, 3)
//                        .pool("cache2Pool", 2, 2)
//                        .build())
//                .withDefaultEventListenersThreadPool("defaultEventPool")
//                .withCache("cache1",
//                        CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
//                                ResourcePoolsBuilder.newResourcePoolsBuilder().heap(10, EntryUnit.ENTRIES))
//                                .add(CacheEventListenerConfigurationBuilder
//                                        .newEventListenerConfiguration(new CacheLogListener(), EventType.CREATED, EventType.UPDATED))
//                                .withDispatcherConcurrency(10) // 指出所需的并发级别
//                                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(20, TimeUnit.SECONDS)))) // 设置过期时间
//                .withCache(CACHE_NAME,
//                        CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
//                                ResourcePoolsBuilder.newResourcePoolsBuilder()
//                                        .heap(10, EntryUnit.ENTRIES)
//                                        .offheap(1, MemoryUnit.MB)
//                                        .disk(20, MemoryUnit.MB, true))
//                                .add(cacheEventListenerConfiguration)
//                                .withDispatcherConcurrency(10) // 指出所需的并发级别
//                                .withDiskStoreThreadPool("cache2Pool", 2)
//                ).build(true);
//
//
//        Cache<Long, String> threeTieredCache =cacheManager.getCache(CACHE_NAME, Long.class, String.class);
////        threeTieredCache.put(1L, "stillAvailableAfterRestart");
//        System.out.println("args = [" +   threeTieredCache.get(1L) + "]");
//        cacheManager.close();
//    }
}
