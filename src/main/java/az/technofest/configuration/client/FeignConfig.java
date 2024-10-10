package az.technofest.configuration.client;

import feign.Request;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }


    @Bean
    public Request.Options options() {
        int connectTimeoutMillis = 5000; // Bağlantı zaman aşımı süresi
        int readTimeoutMillis = 1000;    // Okuma zaman aşımı süresi
        return new Request.Options(connectTimeoutMillis, readTimeoutMillis);
    }
}