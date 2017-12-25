package com.fastbuild.thread;

import java.util.concurrent.*;

/**
 *  线程池工具类
 * Created by xinch on 2017/9/22.
 */
public class CustomThreadPoolUtil {

    /**
     *  根据自己需求自行配置
     */
    //根据cpu的数量动态的配置核心线程数和最大线程数
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    //核心线程数 = CPU核心数 + 1
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    //线程池最大线程数 = CPU核心数 * 2 + 1
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    //非核心线程闲置时超时1s
    private static final int KEEP_ALIVE = 1;

    private static CustomThreadPoolUtil sInstance;
    // 线程池的对象
    private ThreadPoolExecutor threadPoolExecutor;

    private static int corePoolSize = CORE_POOL_SIZE;
    private static int maximumPoolSize = MAXIMUM_POOL_SIZE;
    private static long keepAliveTime = KEEP_ALIVE;

    public CustomThreadPoolUtil(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
        CustomThreadPoolUtil.corePoolSize = corePoolSize;
        CustomThreadPoolUtil.maximumPoolSize = maximumPoolSize;
        CustomThreadPoolUtil.keepAliveTime = keepAliveTime;
    }

    /**
     *  使用默认参数 获取单例对象
     * @return
     */
    public synchronized static CustomThreadPoolUtil getsInstance() {
        if (sInstance == null) {
            sInstance = new CustomThreadPoolUtil(corePoolSize,maximumPoolSize,keepAliveTime);
        }
        return sInstance;
    }

    /**
     * corePoolSize:核心线程数
     * maximumPoolSize：线程池所容纳最大线程数(workQueue队列满了之后才开启)
     * keepAliveTime：非核心线程闲置时间超时时长
     * unit：keepAliveTime的单位
     * workQueue：等待队列，存储还未执行的任务
     * threadFactory：线程创建的工厂
     * handler：异常处理机制
     *
     */
    private ThreadPoolExecutor initExecutor() {
        if(threadPoolExecutor == null) {
            synchronized(CustomThreadPoolUtil.class) {
                threadPoolExecutor  = new ThreadPoolExecutor(
                		corePoolSize, //核心线程数
                		maximumPoolSize,//最大线程数
                		keepAliveTime,  //保持时间
                        TimeUnit.SECONDS, //保持时间对应的单位
                        new ArrayBlockingQueue<Runnable>(20),
                        Executors.defaultThreadFactory(),//线程工厂
                        new ThreadPoolExecutor.AbortPolicy());//异常捕获器
            }
        }
        return threadPoolExecutor;
    }

    /**执行任务*/
    public void executeTask(Runnable r) {
        initExecutor();
        threadPoolExecutor.execute(r);
    }


    /**提交任务*/
    public Future<?> commitTask(Runnable r) {
        initExecutor();
        return threadPoolExecutor.submit(r);
    }

    /**删除任务*/
    public void removeTask(Runnable r) {
        initExecutor();
        threadPoolExecutor.remove(r);
    }

//    public void cancel(Runnable r) {
//        if (r != null) {
//            executor.getQueue().remove(r);//把任务移除等待队列
//        }
//    }


    static ExecutorService cachedThreadPool;

    static ExecutorService fixedThreadPool;

    static ScheduledExecutorService scheduledThreadPool;

    static ExecutorService singleThreadPool;


    static ExecutorService cachedThreadPoolRun(Runnable runnable) {
        if (cachedThreadPool == null)
            cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.submit(runnable);
        return cachedThreadPool;
    }

    public static ExecutorService fixedThreadPoolRun(Runnable runnable) {
        if (fixedThreadPool == null)
            fixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime()
                    .availableProcessors());
        fixedThreadPool.submit(runnable);
        return fixedThreadPool;
    }

    public static ExecutorService scheduledThreadPoolRun(Runnable runnable, long delay, TimeUnit unit) {
        if (scheduledThreadPool == null)
            scheduledThreadPool = Executors.newScheduledThreadPool(Runtime.getRuntime()
                    .availableProcessors());
        scheduledThreadPool.schedule(runnable, delay, unit);
        return scheduledThreadPool;
    }

    public static ExecutorService singleThreadPoolRun(Runnable runnable) {
        if (singleThreadPool == null)
            singleThreadPool = Executors.newSingleThreadExecutor();
        singleThreadPool.submit(runnable);
        return singleThreadPool;
    }


    public static void main(String[] args) {
        final Long startTime = System.currentTimeMillis();
        ExecutorService executorService = null;

        for (int i = 0; i < 1000000; i++) {
//            System.out.println(finalI);
            final int finalI = i;
//            Runnable task = () -> System.out.println(finalI);
            Runnable task = new Runnable() {
				@Override
				public void run() {
					System.out.println(finalI);
				}
			};
            executorService = CustomThreadPoolUtil.fixedThreadPoolRun(task);
        }

        if (executorService != null) {
            executorService.shutdown();
        }

        while (true) {
            if (executorService.isTerminated()) {
                System.out.println("所有的子线程都结束了！");
                break;
            }
        }

        final Long stopTime = System.currentTimeMillis();
        System.out.println("---------------------------");
        System.out.println(stopTime - startTime);


    }



}
