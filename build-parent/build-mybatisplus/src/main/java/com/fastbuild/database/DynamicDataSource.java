package com.fastbuild.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 获取数据源
 *
 * @auther xinch
 * @create 2017/12/20 14:35
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final Logger log = LoggerFactory.getLogger(DynamicDataSource.class);

    /**
     * 取得当前使用哪个数据源
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        log.debug("数据源为{}", DynamicDataSourceContextHolder.getDataSourceType());
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}
