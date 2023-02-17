package com.wondersgroup.msg.config;

import com.wondersgroup.msg.ActiveMQListener;
import org.apache.activemq.xbean.BrokerFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@ConfigurationProperties(prefix = "spring.activemq") //指明配置节点
public class ActiveMQConfig {

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Bean
    public BrokerFactoryBean activemq() throws Exception {
        BrokerFactoryBean broker = new BrokerFactoryBean();
        broker.setConfig(new ClassPathResource("activemq.xml"));
        broker.setStart(true);
        return broker;
    }

    /**
     * 注册 Servlet 三大组件 之  Listner
     * 添加 ServletListenerRegistrationBean ，就相当于以前在 web.xml 中配置的 <listener></listener>标签
     */
    @Bean
    public ServletListenerRegistrationBean myListener() {
        /**ServletListenerRegistrationBean<T extends EventListener> 使用的是泛型，可以注册常见的任意监听器
         * 将自己的监听器注册进来*/
        ServletListenerRegistrationBean registrationBean =
                new ServletListenerRegistrationBean(new ActiveMQListener(brokerUrl));
        return registrationBean;
    }
}
