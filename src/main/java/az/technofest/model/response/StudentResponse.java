package az.technofest.model.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentResponse {


    String  name;
    Long groupNumber;
    Long scholarship;


    public StudentResponse(String name, Long groupNumber, Long scholarship) {
        this.name = name;
        this.groupNumber = groupNumber;
        this.scholarship = scholarship;
    }
}
