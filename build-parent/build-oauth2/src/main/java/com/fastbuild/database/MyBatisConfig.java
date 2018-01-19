package com.fastbuild.database;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * mybatis配置类
 *
 * @auther xinch
 * @create 2017/12/21 14:21
 */
@Configuration
@MapperScan(basePackages = {"com.fastbuild.mapper"},sqlSessionTemplateRef = "sqlSessionTemplate")
public class MyBatisConfig extends AbstractDruidDBConfig{

    @Autowired
    private DruidDbProperties druidDbProperties;

    @Autowired
    private Environment env;

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

    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     * @return
     */
    @Bean(name = "dynamicDataSource")
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

    @Bean(name = "sqlSessionFactor")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        return super.sqlSessionFactory(dynamicDataSource());
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException {
        return new DataSourceTransactionManager(dynamicDataSource());
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate userSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory()); // 使用上面配置的Factory
    }
}
