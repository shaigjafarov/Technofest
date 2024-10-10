package az.technofest.client;

import az.technofest.configuration.client.FeignConfig;
import az.technofest.model.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "fakeProduct", url = "https://fakestoreapi.com", configuration = FeignConfig.class)
public interface ProductClient {



    @PostMapping("/products")
    ProductDto addProduct(@RequestBody ProductDto productDto);

    @GetMapping("/asa")
    ProductDto getProduct();
}
