package com.fastbuild.database;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

/**
 * mybatis配置类
 *
 * @auther xinch
 * @create 2017/12/21 14:21
 */
@Configuration
@MapperScan(basePackages = {"com.fastbuild.mapper*"})
public class MyBatisConfig extends AbstractDruidDBConfig{

    @Autowired
    private DruidDbProperties druidDbProperties;

    @Autowired
    private Environment env;

    /**
     * mybatis-plus分页插件<br>
     * 文档：http://mp.baomidou.com<br>
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean(name="masterDataSource", destroyMethod = "close", initMethod="init")
    @Primary
    public DruidDataSource masterDataSource() {
        System.out.println("#################################"+env.getProperty("datasource.master.jdbcUrl"));
        return super.createDataSource(env.getProperty("datasource.master.driverClass"),env.getProperty("datasource.master.jdbcUrl"),env.getProperty("datasource.master.username"),env.getProperty("datasource.master.password"),druidDbProperties);
    }

    @Bean(name="slaveDataSource", destroyMethod = "close", initMethod="init")
    public DruidDataSource slaveDataSource() {
        System.out.println("#################################"+env.getProperty("datasource.slave.jdbcUrl"));
        return super.createDataSource(env.getProperty("datasource.slave.driverClass"),env.getProperty("datasource.slave.jdbcUrl"),env.getProperty("datasource.slave.username"),env.getProperty("datasource.slave.password"),druidDbProperties);
    }

    // druid 根据配置文件获取数据源必要参数  参考 http://blog.csdn.net/qq_31591883/article/details/74784016
//    public DruidDataSource customDataSource() {
//        druidDbProperties.setConnectionProperties("config.decrypt=true;config.decrypt.key="+env.getProperty("datasource.custom.db_pubkey")+";config.file="+env.getProperty("datasource.custom.db_config_url")+";");
//        return super.createDataSource(env.getProperty("datasource.slave.driverClass"),env.getProperty("datasource.slave.jdbcUrl"),env.getProperty("datasource.slave.username"),env.getProperty("datasource.slave.password"),druidDbProperties);
//    }

    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     * @return
     */
    @Bean(name = "dataSource")
    public DynamicDataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource());

        // 配置多数据源
        Map<Object, Object> dataSourceMap = new HashMap(5);
        dataSourceMap.put(DataSourceEnum.DATASOURCEKEY.MASTER.getCode(), masterDataSource());
        dataSourceMap.put(DataSourceEnum.DATASOURCEKEY.SLAVE.getCode(), slaveDataSource());
        // 将 master 和 slave 数据源作为指定的数据源
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        // 将数据源的 key 放到数据源上下文的 key 集合中，用于切换时判断数据源是否有效
        DynamicDataSourceContextHolder.dataSourceKeys.addAll(dataSourceMap.keySet());
        return dynamicDataSource;
    }

//    @Bean(name = "sqlSessionFactor")
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        return super.sqlSessionFactory(dynamicDataSource());
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() throws SQLException {
//        return new DataSourceTransactionManager(dynamicDataSource());
//    }
//
//    @Bean(name = "sqlSessionTemplate")
//    public SqlSessionTemplate userSqlSessionTemplate() throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory()); // 使用上面配置的Factory
//    }
}
