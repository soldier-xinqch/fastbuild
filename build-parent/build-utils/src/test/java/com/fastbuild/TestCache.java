package com.fastbuild;

import com.fastbuild.cache.CacheLogListener;
import org.ehcache.Cache;
import org.ehcache.CachePersistenceException;
import org.ehcache.PersistentCacheManager;
import org.ehcache.config.builders.*;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.event.EventType;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;

import java.io.File;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

/**
 * @auther xinch
 * @create 2018/1/25 14:27
 */
@SuppressWarnings("Duplicates")
public class TestCache {

    private static String CACHE_NAME = "totalCache";

    public static void main(String[] args) throws CachePersistenceException, MalformedURLException {
        // 缓存的时间监听
        CacheEventListenerConfigurationBuilder cacheEventListenerConfiguration = CacheEventListenerConfigurationBuilder
                // CacheEventListenerConfiguration使用构建器创建一个指示侦听器和要接收的事件（在这种情况下，创建和更新事件）
                .newEventListenerConfiguration(new CacheLogListener(), EventType.CREATED, EventType.UPDATED)
                // 可选地指示交付模式 - 默认值是异步的和无序的（出于性能原因）
                .unordered().asynchronous();

        PersistentCacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .with(CacheManagerBuilder.persistence(new File(getStoragePath(), "myData")))
                .using(PooledExecutionServiceConfigurationBuilder.newPooledExecutionServiceConfigurationBuilder()
                        .pool("defaultEventPool", 1, 3)
                        .pool("cache2Pool", 2, 2)
                        .build())
                .withDefaultEventListenersThreadPool("defaultEventPool")
                .withCache("cache1",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
                                ResourcePoolsBuilder.newResourcePoolsBuilder().heap(10, EntryUnit.ENTRIES))
                                .add(CacheEventListenerConfigurationBuilder
                                        .newEventListenerConfiguration(new CacheLogListener(), EventType.CREATED, EventType.UPDATED))
                                .withDispatcherConcurrency(10) // 指出所需的并发级别
                                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(20, TimeUnit.SECONDS)))) // 设置过期时间
                .withCache(CACHE_NAME,
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
                                ResourcePoolsBuilder.newResourcePoolsBuilder()
                                        .heap(10, EntryUnit.ENTRIES)
                                        .offheap(1, MemoryUnit.MB)
                                        .disk(20, MemoryUnit.MB, true))
                                .add(cacheEventListenerConfiguration)
                                .withDispatcherConcurrency(10) // 指出所需的并发级别
                                .withDiskStoreThreadPool("cache2Pool", 2)
                ).build(true);


        Cache<Long, String> threeTieredCache =cacheManager.getCache(CACHE_NAME, Long.class, String.class);
//        threeTieredCache.put(1L, "stillAvailableAfterRestart");
        System.out.println("args = [" +   threeTieredCache.get(1L) + "]");
        cacheManager.close();

    }


//    public static void main(String[] args) throws CachePersistenceException, MalformedURLException {
//        // 缓存的事件监听
////        CacheEventListenerConfigurationBuilder cacheEventListenerConfiguration = CacheEventListenerConfigurationBuilder
////                // CacheEventListenerConfiguration使用构建器创建一个指示侦听器和要接收的事件（在这种情况下，创建和更新事件）
////                .newEventListenerConfiguration(new CacheLogListener(), EventType.CREATED, EventType.UPDATED)
////                // 可选地指示交付模式 - 默认值是异步的和无序的（出于性能原因）
////                .unordered().asynchronous();
//
//        //        final CacheManager manager = CacheManagerBuilder.newCacheManagerBuilder()
////                .withCache("foo",
////                        // 将侦听器的配置传递给缓存配置
////                        CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class, ResourcePoolsBuilder.heap(10))
////                                .add(cacheEventListenerConfiguration)
////                ).build(true);
//
//        // 在使用过程中使用监听器
////        ListenerObject listener = new ListenerObject();
////        cache.getRuntimeConfiguration().registerCacheEventListener(listener, EventOrdering.ORDERED,
////                EventFiring.ASYNCHRONOUS, EnumSet.of(EventType.CREATED, EventType.REMOVED));
//        // 注销在使用过程中注入的监听器
////        cache.getRuntimeConfiguration().deregisterCacheEventListener(listener);
//
//        // 设置缓存键值过期时间
////        CacheConfiguration<Long, String> cacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
////                ResourcePoolsBuilder.heap(100))
////                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(20, TimeUnit.SECONDS)))
////                .build();
////      //调整可用于传递事件的并发级别
////        CacheConfiguration<Long, String> cacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
////                ResourcePoolsBuilder.heap(5L))
////                .withDispatcherConcurrency(10) // 指出所需的并发级别
////                .withEventListenersThreadPool("listeners-pool")
////                .build();
//
////        Cache<Long, String> writeBehindCache = cacheManager.createCache("writeBehindCache",
////                CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10))
////                        // 配置CacheLoaderWriter
////                        .withLoaderWriter(new SampleLoaderWriter<Long, String>(singletonMap(41L, "zero")))
////                        .add(WriteBehindConfigurationBuilder
////                                 // 我们将批量配置为3或最大写入延迟为1秒
////                                .newBatchedWriteBehindConfiguration(1, TimeUnit.SECONDS, 3)
////                                .queueSize(3) // 我们还设置了后写队列的最大大小
////                                .concurrencyLevel(1) // 定义后写队列的并发级别。这表明有多少个写线程并行工作以异步更新记录的底层系统。
////                                .enableCoalescing()) // 启用写入合并行为，这可确保每个批次的每个键只有一个更新到达记录的底层系统。
////                        .build());
//
//
//        PersistentCacheManager persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder()
//                .with(CacheManagerBuilder.persistence(new File(getStoragePath(), "myData")))
//                .withCache("threeTieredCache",
//                        CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
//                                ResourcePoolsBuilder.newResourcePoolsBuilder()
//                                        .heap(10, EntryUnit.ENTRIES)
//                                        .offheap(1, MemoryUnit.MB)
//                                        .disk(20, MemoryUnit.MB, true)
//                        )
//                ).build(true);
//        // build(true) == persistentCacheManager.init();
//        Cache<Long, String> threeTieredCache = persistentCacheManager.getCache("threeTieredCache", Long.class, String.class);
////        threeTieredCache.put(1L, "stillAvailableAfterRestart");
//        System.out.println("args = [" +   threeTieredCache.get(1L) + "]");
//        persistentCacheManager.close();
//
//    }

    private static File getStoragePath() {
        return new File("D:\\home\\ehcacheDir");
    }


/**
 *   测试ehcache3.x 线程池的使用
 */
//    public static void main(String[] args) throws CachePersistenceException, MalformedURLException {
//        CacheManager cacheManager
//                = CacheManagerBuilder.newCacheManagerBuilder()
//                .using(PooledExecutionServiceConfigurationBuilder.newPooledExecutionServiceConfigurationBuilder()
//                        .pool("defaultEventPool", 1, 3)
//                        .pool("cache2Pool", 2, 2)
//                        .build())
//                .withDefaultEventListenersThreadPool("defaultEventPool") //告诉CacheManagerBuilder对所有没有明确指定的磁盘存储使用默认的线程池。
//                .withCache("cache1",
//                        CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
//                                ResourcePoolsBuilder.newResourcePoolsBuilder().heap(10, EntryUnit.ENTRIES))
//                                .add(CacheEventListenerConfigurationBuilder
//                                        .newEventListenerConfiguration(new CacheLogListener(), EventType.CREATED, EventType.UPDATED)))
//                .withCache("cache2",
//                        CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
//                                ResourcePoolsBuilder.newResourcePoolsBuilder().heap(10, EntryUnit.ENTRIES))
//                                .add(CacheEventListenerConfigurationBuilder
//                                        .newEventListenerConfiguration(new CacheLogListener(), EventType.CREATED, EventType.UPDATED))
//                                .withEventListenersThreadPool("cache2Pool"))
//                .build(true);
//
//        Cache<Long, String> cache1 =
//                cacheManager.getCache("cache1", Long.class, String.class);
//        Cache<Long, String> cache2 =
//                cacheManager.getCache("cache2", Long.class, String.class);
//
//        cache1.put(1L,"xinqch");
//        System.out.println("cache1.key = [" + cache1.get(1L) + "]");
//        cache2.put(2L,"helloword");
//        System.out.println("cache2.key = [" + cache2.get(2L) + "]");
//
//        cacheManager.close();
//
//
//
//CacheManager cacheManager
//        = CacheManagerBuilder.newCacheManagerBuilder()
//        .using(PooledExecutionServiceConfigurationBuilder.newPooledExecutionServiceConfigurationBuilder()
//                .defaultPool("dflt", 0, 10)
//                .pool("defaultDiskPool", 1, 3)
//                .pool("cache2Pool", 2, 2)
//                .build())
//        .with(new CacheManagerPersistenceConfiguration(new File(getStoragePath(), "myData")))
//        .withDefaultDiskStoreThreadPool("defaultDiskPool")
//        .withCache("cache1",
//                CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
//                        ResourcePoolsBuilder.newResourcePoolsBuilder()
//                                .heap(10, EntryUnit.ENTRIES)
//                                .disk(10L, MemoryUnit.MB)))
//        .withCache("cache2",
//                CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
//                        ResourcePoolsBuilder.newResourcePoolsBuilder()
//                                .heap(10, EntryUnit.ENTRIES)
//                                .disk(10L, MemoryUnit.MB))
//                        .withDiskStoreThreadPool("cache2Pool", 2)) //  告诉缓存为其磁盘存储使用特定的线程池。
//        .build(true);
//
//    Cache<Long, String> cache1 =
//            cacheManager.getCache("cache1", Long.class, String.class);
//    Cache<Long, String> cache2 =
//            cacheManager.getCache("cache2", Long.class, String.class);
//
//    cacheManager.close();
//}
}
