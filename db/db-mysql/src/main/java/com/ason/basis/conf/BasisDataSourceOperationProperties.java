package com.ason.basis.conf;


import com.ason.DataSourceOperationProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
/**
 * Created by Ason on 2017/10/31.
 */
@Configuration
@ConfigurationProperties(prefix = "basis.druid")
public class BasisDataSourceOperationProperties extends DataSourceOperationProperties {

}
