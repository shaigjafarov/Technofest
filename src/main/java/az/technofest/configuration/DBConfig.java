package az.technofest.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {

    @Value("${db.host.postgre}")
    String host;

    @Value("${db.name.postgre}")
    String dbName;

    @Value("${db.port.postgre}")
    String port;


    public void getConnection(){
        System.out.println(host);
        System.out.println(dbName);
        System.out.println(port);
    }



}
