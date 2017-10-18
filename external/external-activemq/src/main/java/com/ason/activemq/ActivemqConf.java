package com.ason.activemq;

import com.sun.jndi.ldap.pool.PooledConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;

import javax.jms.ConnectionFactory;

/**
 * Created by Ason on 2017/10/17.
 */
public class ActivemqConf {
    private static final Log log = LogFactory.getLog(ActivemqConf.class);

    @Bean(name = "targetConnectionFactory")
    public ActiveMQConnectionFactory activeMQConnectionFactory(){
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL("tcp://ason-hostname:61616");
        activeMQConnectionFactory.setUserName("");
        activeMQConnectionFactory.setPassword("");
        return activeMQConnectionFactory;
    }

//    @Bean(name = "pooledConnectionFactory")
//    public PooledConnectionFactory pooledConnectionFactory(){
//        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
//        activeMQConnectionFactory.setBrokerURL("tcp://ason-hostname:61616");
//        activeMQConnectionFactory.setUserName("");
//        activeMQConnectionFactory.setPassword("");
//        return activeMQConnectionFactory;
//    }
}
