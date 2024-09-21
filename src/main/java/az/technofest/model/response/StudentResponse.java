package az.technofest.model.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentResponse {


    String  name;
    Long groupNumber;
    Long scholarship;
}
