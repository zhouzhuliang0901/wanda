package com.wondersgroup.msg;

import org.apache.activemq.broker.BrokerService;
import org.cometd.annotation.server.AnnotationCometDServlet;
import org.cometd.examples.ChatService;
import org.cometd.examples.CometDDemoServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletRegistration;

@SpringBootApplication
public class AcMsgApplication implements ServletContextInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AcMsgApplication.class, args);
    }

    @Override
    public void onStartup(ServletContext servletContext) {
        ServletRegistration.Dynamic cometdServlet = servletContext.addServlet("cometd", AnnotationCometDServlet.class);
        String mapping = "/cometd/*";
        cometdServlet.addMapping(mapping);
        cometdServlet.setInitParameter("ws.cometdURLMapping", mapping);
        cometdServlet.setInitParameter("timeout", "20000");
        cometdServlet.setInitParameter("interval", "0");
        cometdServlet.setInitParameter("maxInterval", "10000");
        cometdServlet.setInitParameter("maxLazyTimeout", "5000");
        cometdServlet.setInitParameter("long-polling.multiSessionInterval", "2000");
//        cometdServlet.setInitParameter("logLevel", "0");
//        cometdServlet.setInitParameter("transports", "org.cometd.server.transport.JSONPTransport,org.cometd.server.transport.JSONTransport");
//        cometdServlet.setInitParameter("services", "reindeer.cometd.CometDServiceFactory");
        cometdServlet.setAsyncSupported(true);
        cometdServlet.setLoadOnStartup(1);

//        ServletRegistration.Dynamic demoServlet = servletContext.addServlet("demo", CometDDemoServlet.class);
//        demoServlet.addMapping("/demo");
//        demoServlet.setAsyncSupported(true);
//        demoServlet.setLoadOnStartup(2);
    }
}
