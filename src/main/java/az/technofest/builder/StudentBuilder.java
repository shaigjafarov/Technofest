package az.technofest.builder;


import az.technofest.dao.entity.Student;
import az.technofest.model.request.StudentRequest;

public class StudentBuilder {

    public static Student buildStudent(StudentRequest request){

        return Student.builder()
                .fullName(request.getName())
                .scholarShip(request.getSalary())
                .groupId(request.getGroup())
                .build();
    }
}
