package reindeer.base.app;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.WebApplicationContext;
import tw.ecosystem.reindeer.config.RdConfig;
import tw.ecosystem.reindeer.web.Initializer;
import tw.ecosystem.reindeer.web.ReindeerWebContext;
import tw.tool.helper.LogHelper;
import wfc.service.config.Config;

import javax.servlet.ServletContext;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class})
@ComponentScan(value = "com.wondersgroup,reindeer")
public class BootApplication extends SpringBootServletInitializer {

    static {
        URL path = AclFilter.class.getResource("/reindeer_config.properties");
        Config.switchFilename(path.getFile());
        Config.set("wfc.service.database.connection.factory",
                "reindeer.base.utils.WfcConnectionFactory");
        Config.set("wfc.service.connection.mode", "1");
        Config.set("wfc.service.jdbc.driver",
                RdConfig.get("reindeer.service.jdbc.driver"));
        Config.set("wfc.service.connection.string",
                RdConfig.get("reindeer.service.connection.string"));
        Config.set("wfc.service.connection.user",
                RdConfig.get("reindeer.service.connection.user"));
        Config.set("wfc.service.connection.password",
                RdConfig.get("reindeer.service.connection.password"));
    }

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder application) {
        return application.sources(BootApplication.class);
    }

    @Override
    protected WebApplicationContext createRootApplicationContext(
            ServletContext servletContext) {
        WebApplicationContext context = super
                .createRootApplicationContext(servletContext);
        ReindeerWebContext.currentContext = context;
        String appPath = StringUtils.trimToEmpty(RdConfig
                .get("reindeer.apppath"));
        ReindeerWebContext.appPath = appPath.isEmpty() ? servletContext
                .getRealPath("/") : appPath;
        ReindeerWebContext.webRootPath = servletContext.getContextPath();
        LogHelper.debug("appPath = " + ReindeerWebContext.appPath);
        LogHelper.debug("webRootPath = " + ReindeerWebContext.webRootPath);
        Map<String, Initializer> initializerMap = ReindeerWebContext.currentContext
                .getBeansOfType(Initializer.class);
        if (initializerMap != null) {
            Iterator<?> var6 = initializerMap.values().iterator();
            while (var6.hasNext()) {
                Initializer initializer = (Initializer) var6.next();
                initializer.init();
            }
        }
        return context;
    }

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }
}
