package az.technofest.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.lang.NonNull;
@Data
public class StudentRequest {




    String name;
    Long group;
    Long salary;

}
