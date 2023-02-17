package com.wondersgroup.msg;

import org.cometd.annotation.server.AnnotationCometDServlet;
import org.cometd.examples.ChatService;
import org.cometd.examples.CometDDemoServlet;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ServletInitializer.class);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
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
    }
}
