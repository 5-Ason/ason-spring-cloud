package com.ason.basis.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Ason on 2017/9/26.
 */
@Configuration
public class DataSourceConf {
    private static final Log log = LogFactory.getLog(DataSourceConf.class);

    //	配置数据源
    @Bean(name = "basisDataSource", initMethod = "init", destroyMethod = "close")
    public DruidDataSource initDataSource(BasisDataSourceConnectionProperties bscp,
                                          BasisDataSourceOperationProperties bdop) {
        log.info("初始化DruidDataSource");
        DruidDataSource dds = new DruidDataSource();
        dds.setDriverClassName("com.mysql.jdbc.Driver");
        dds.setUrl(bscp.getUrl());
        dds.setUsername(bscp.getUsername());
        dds.setPassword(bscp.getPassword());
        dds.setInitialSize(bdop.getInitialSize());
        dds.setMinIdle(bdop.getMinIdle());
        dds.setMaxActive(bdop.getMaxActive());
        dds.setMaxWait(bdop.getMaxWait());
        dds.setTimeBetweenEvictionRunsMillis(bdop.getTimeBetweenEvictionRunsMillis());
        dds.setMinEvictableIdleTimeMillis(bdop.getMinEvictableIdleTimeMillis());
        dds.setValidationQuery(bdop.getValidationQuery());
        dds.setTestWhileIdle(bdop.getTestWhileIdle());
        dds.setTestOnBorrow(bdop.getTestOnBorrow());
        dds.setTestOnReturn(bdop.getTestOnReturn());
        dds.setPoolPreparedStatements(bdop.getPoolPreparedStatements());
        dds.setMaxPoolPreparedStatementPerConnectionSize(bdop.getMaxPoolPreparedStatementPerConnectionSize());
        try {
            dds.setFilters(bdop.getFilters());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dds;
    }

    /**
     * druid数据源状态监控.
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServlet(BasisDataSourceOperationProperties bdop) {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //设置登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername",bdop.getLoginUsername());
        servletRegistrationBean.addInitParameter("loginPassword",bdop.getLoginPassword());
        return servletRegistrationBean;
    }

    /**
     * druid过滤器.
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
