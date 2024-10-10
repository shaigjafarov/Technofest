package az.technofest.builder;

import az.technofest.dao.entity.Teacher;
import az.technofest.model.request.TeacherRequest;

public class TeacherBuilder {

    public static Teacher buildTeacher(TeacherRequest request){

        return Teacher.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .subject(request.getSubject())
                .salary(request.getSalary())
                .info(request.getInfo())
                .build();
    }
}
