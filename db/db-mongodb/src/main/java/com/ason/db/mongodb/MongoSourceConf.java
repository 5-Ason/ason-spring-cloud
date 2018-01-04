package com.ason.db.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.net.UnknownHostException;

@Configuration
public class MongoSourceConf {

    private static final Log log = LogFactory.getLog(MongoSourceConf.class);

    @Bean
    public MongoClientURI mongoClientURI(MongoProperties mongoProperties){
        log.info("初始化mongoClientURI");
        return new MongoClientURI(mongoProperties.toString());
    }

    @Bean
    public MongoClient mongoClient(MongoClientURI mongoClientURI){
        log.info("初始化mongoClient");
        return new MongoClient(mongoClientURI);
    }

    @Bean(name = "mongoDbFactory", destroyMethod = "destroy")
    public SimpleMongoDbFactory mongoDbFactory(MongoClient mongoClient, MongoClientURI mongoClientURI) throws UnknownHostException {
        log.info("初始化mongoDbFactory");
        return new SimpleMongoDbFactory(mongoClient, mongoClientURI.getDatabase());
    }

    @Bean
    public MongoTemplate mongoDaoSupport(@Qualifier("mongoDbFactory") SimpleMongoDbFactory mongoDbFactory) throws UnknownHostException {
        log.info("初始化mongoDaoSupport");
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory);
        return mongoTemplate;
    }




    public static void main(String[] args) {
        MongoClientOptions.Builder build = new MongoClientOptions.Builder();
        build.connectionsPerHost(50);   //与目标数据库能够建立的最大connection数量为50
        build.threadsAllowedToBlockForConnectionMultiplier(50); //如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待
            /*
             * 一个线程访问数据库的时候，在成功获取到一个可用数据库连接之前的最长等待时间为2分钟
             * 这里比较危险，如果超过maxWaitTime都没有获取到这个连接的话，该线程就会抛出Exception
             * 故这里设置的maxWaitTime应该足够大，以免由于排队线程过多造成的数据库访问失败
             */
        build.maxWaitTime(1000*60*2);
        build.connectTimeout(1000*60*1);    //与数据库建立连接的timeout设置为1分钟

        MongoClientOptions myOptions = build.build();


        MongoClient mongoClient = new MongoClient("127.0.0.1", myOptions);
        MongoTemplate mongoTemplate = new MongoTemplate(new SimpleMongoDbFactory(mongoClient, "ason"));

    }

}
