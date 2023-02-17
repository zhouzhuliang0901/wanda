package com.wondersgroup.msg;

import org.apache.activemq.broker.BrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ActiveMQListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(ActiveMQListener.class);
    private String serviceURL;

    public ActiveMQListener(String brokerUrl) {
        this.serviceURL = brokerUrl;
    }

    /**
     * 应用启动时自动执行
     *
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            logger.info("开始启动内嵌 ActiveMQ......");
            /**BrokerService 表示 ActiveMQ 服务，每一个 BrokerService 表示一个消息服务器实例
             * 如果想启动多个，只需要 start 多个不同端口的 BrokerService 即可*/
            BrokerService brokerService = new BrokerService();
            brokerService.setUseJmx(true);//设置是否应将代理的服务公开到jmx中。默认是 true
            brokerService.addConnector(serviceURL);//为指定地址添加新的传输连接器
            /**启动 ActiveMQ 服务，此时客户端便可以使用提供的地址进行连接，然后发送消息过来，或者从这里消费消息。
             * 注意：这里内嵌启动后，默认是没有提供 8161 端口的 web 管理界面的，照样能做消息中间件使用*/
            brokerService.start();
            logger.info("启动内嵌 ActiveMQ 服务器完成......");
        } catch (Exception e) {
            logger.error("启动内嵌 ActiveMQ 服务器失败...");
        }
    }

    /**
     * 应用销毁时自动执行
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("内嵌 ActiveMQ关闭......");
    }
}