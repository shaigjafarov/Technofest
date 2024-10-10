package az.technofest.client;

import az.technofest.configuration.client.FeignConfig;
import az.technofest.model.dto.FakeStudentDTO;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "fakeStudent", url = "https://fake-json-api.mock.beeceptor.com", configuration = FeignConfig.class)
public interface StudentClient {

    @GetMapping("/users")
    List<FakeStudentDTO> getFakeStudent();

}