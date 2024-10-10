package az.technofest.configuration.client;

import az.technofest.exception.StudentNotFountException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;

@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 400:
                return new BadRequestException("Bad Request from Feign Client");
            case 404:
                log.info("bele api yoxdu faake");
                System.out.println("bele api yoxdut");
                return new StudentNotFountException("Resource not found from Feign Client");
            default:
                return defaultErrorDecoder.decode(methodKey, response);
        }
    }
}