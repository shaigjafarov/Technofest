package az.technofest.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(value = "kafka")
public class KafkaConfig {

    String host;
    String port;
    String name;
}
