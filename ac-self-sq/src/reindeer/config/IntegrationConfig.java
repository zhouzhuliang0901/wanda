package reindeer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

@Configuration
@IntegrationComponentScan(value = "com.wondersgroup")
@EnableIntegration
public class IntegrationConfig {

}
