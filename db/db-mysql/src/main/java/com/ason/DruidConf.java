package com.ason;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Ason on 2017/9/26.
 */
@Configuration
public class DruidConf {
    private static final Log log = LogFactory.getLog(DruidConf.class);

    @Value("${connection.url}")
    private String connectionUrl;
    @Value("${connection.username}")
    private String username;
    @Value("${connection.password}")
    private String password;
    @Value("${druid.initialSize}")
    private Integer initialSize;
    @Value("${druid.minIdle}")
    private Integer minIdle;
    @Value("${druid.maxActive}")
    private Integer maxActive;
    @Value("${druid.maxWait}")
    private Integer maxWait;
    @Value("${druid.timeBetweenEvictionRunsMillis}")
    private Integer timeBetweenEvictionRunsMillis;
    @Value("${druid.minEvictableIdleTimeMillis}")
    private Integer minEvictableIdleTimeMillis;
    @Value("${druid.validationQuery}")
    private String validationQuery;
    @Value("${druid.testWhileIdle}")
    private Boolean testWhileIdle;
    @Value("${druid.testOnBorrow}")
    private Boolean testOnBorrow;
    @Value("${druid.testOnReturn}")
    private Boolean testOnReturn;
    @Value("${druid.poolPreparedStatements}")
    private Boolean poolPreparedStatements;
    @Value("${druid.maxPoolPreparedStatementPerConnectionSize}")
    private Integer maxPoolPreparedStatementPerConnectionSize;
    @Value("${druid.filters}")
    private String filters;

    //	配置数据源
    @Bean(name = "basisDataSource", initMethod = "init", destroyMethod = "close")
    public DruidDataSource initDataSource() {
        log.info("初始化DruidDataSource");
        DruidDataSource dds = new DruidDataSource();
        dds.setDriverClassName("com.mysql.jdbc.Driver");
        dds.setUrl(connectionUrl);
        dds.setUsername(username);
        dds.setPassword(password);
        dds.setInitialSize(initialSize);
        dds.setMinIdle(minIdle);
        dds.setMaxActive(maxActive);
        dds.setMaxWait(maxWait);
        dds.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dds.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dds.setValidationQuery(validationQuery);
        dds.setTestWhileIdle(testWhileIdle);
        dds.setTestOnBorrow(testOnBorrow);
        dds.setTestOnReturn(testOnReturn);
        dds.setPoolPreparedStatements(poolPreparedStatements);
        dds.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            dds.setFilters(filters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dds;
    }
}
