package az.technofest.model.request;

import az.technofest.dao.entity.Info;
import java.util.List;
import lombok.Data;

@Data
public class TeacherRequest {


    private String name;

    private String surname;

    private Long salary;

    private String subject;

    private Info info;

    private List<StudentRequest> students;

}
