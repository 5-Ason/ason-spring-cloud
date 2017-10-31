package com.ason.basis.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.ason.utils.BlankUtil;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * Created by Ason on 2017/8/15.
 */
@Configuration
public class MybatisPlusConf {

	private static final Log log = LogFactory.getLog(MybatisPlusConf.class);

	//    mybatisPlus全局配置
	@Bean(name = "globalConfig")
	public GlobalConfiguration globalConfig(
			@Value("${mybatisPlus.globalConfig.idType}") Integer idType, //主键类型  0:"数据库ID自增", 1:"用户输入ID",2:"全局唯一ID (数字类型唯一ID)", 3:"全局唯一ID UUID";
			@Value("${mybatisPlus.globalConfig.fieldStrategy}") Integer fieldStrategy, //字段策略 0:"忽略判断",1:"非 NULL 判断"),2:"非空判断"
			@Value("${mybatisPlus.globalConfig.dbColumnUnderline}") Boolean dbColumnUnderline, //驼峰下划线转换
			@Value("${mybatisPlus.globalConfig.isRefresh}") Boolean isRefresh, //刷新mapper 调试神器
			@Value("${mybatisPlus.globalConfig.isCapitalMode}") Boolean isCapitalMode, //数据库大写下划线转换
			@Value("${mybatisPlus.globalConfig.logicDeleteValue}") String logicDeleteValue, //逻辑删除配置
			@Value("${mybatisPlus.globalConfig.logicNotDeleteValue}") String logicNotDeleteValue //逻辑删除配置
	) {
		log.info("初始化GlobalConfiguration");
		GlobalConfiguration globalConfig = new GlobalConfiguration();
		// 主键类型
		if ( !BlankUtil.isBlank(idType)) {
			globalConfig.setIdType(idType);
		}
		// 字段策略
		if ( !BlankUtil.isBlank(fieldStrategy)) {
			//		globalConfig.setFieldStrategy(fieldStrategy);
		}
		// 驼峰下划线转换
		if ( !BlankUtil.isBlank(dbColumnUnderline)) {
			globalConfig.setDbColumnUnderline(dbColumnUnderline);
		}
		// 刷新mapper 调试神器
		if ( !BlankUtil.isBlank(isRefresh)) {
			//		globalConfig.setRefresh(isRefresh);
		}
		// 数据库大写下划线转换
		if ( !BlankUtil.isBlank(isCapitalMode)) {
			globalConfig.setCapitalMode(isCapitalMode);
		}
		// 逻辑删除配置
		if ( !BlankUtil.isBlank(logicDeleteValue)) {
			//		globalConfig.setLogicDeleteValue(logicDeleteValue);
		}
		// 逻辑删除配置
		if ( !BlankUtil.isBlank(logicNotDeleteValue)) {
			//		globalConfig.setLogicNotDeleteValue(logicNotDeleteValue);
		}
		return globalConfig;
	}

	@Bean(name = "basisSqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier(value = "globalConfig")GlobalConfiguration globalConfig,
											   @Qualifier(value = "basisDataSource")DruidDataSource dataSource) throws Exception {
		log.info("初始化SqlSessionFactory");
		String mapperLocations = "classpath:db-ason/sql/**/*.xml";
		String configLocation = "classpath:db-ason/mybatis/mybatis-sqlconfig.xml";
		String typeAliasesPackage = "com.ason.entity.**";
		MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
		// 数据源
		sqlSessionFactory.setDataSource(dataSource);
		// 全局配置
		sqlSessionFactory.setGlobalConfig(globalConfig);
		Interceptor[] interceptor = {new PaginationInterceptor()};
		// 分页插件
		sqlSessionFactory.setPlugins(interceptor);
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			// 自动扫描Mapping.xml文件
			sqlSessionFactory.setMapperLocations(resolver.getResources(mapperLocations));
			sqlSessionFactory.setConfigLocation(resolver.getResource(configLocation));
			sqlSessionFactory.setTypeAliasesPackage(typeAliasesPackage);
			return sqlSessionFactory.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Bean(name = "basisSqlSession")
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory){
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	//    MyBatis 动态扫描
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		log.info("初始化MapperScannerConfigurer");
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		String basePackage = "com.ason.db.mapper";
		mapperScannerConfigurer.setBasePackage(basePackage);
		return mapperScannerConfigurer;
	}

	//    配置事务管理
	@Bean(name = "basisTransactionManager")
	public DataSourceTransactionManager transactionManager(@Qualifier(value = "basisDataSource")DruidDataSource dataSource) {
		log.info("初始化DataSourceTransactionManager");
		return new DataSourceTransactionManager(dataSource);
	}
}
