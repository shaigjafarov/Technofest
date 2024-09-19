package az.technofest.service;

import az.technofest.exception.StudentAlreadyExistException;
import az.technofest.exception.StudentNotFountException;
import az.technofest.model.request.StudentRequest;
import java.util.HashMap;
import java.util.Map;

public class StudentService {

    private  final TeacherService teacherService;

    public StudentService( TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    static Map<Long, StudentRequest> studentMap= new HashMap<>(Map.of(12L, new StudentRequest(12L, "Hafiz"), 13L , new StudentRequest(13L, "Elmir") ));


    public StudentRequest getById(Long id){
        teacherService.result();
        return studentMap.get(id);
    }

    public void addStudent(StudentRequest student){
        if(studentMap.containsKey(student.getId()))
            throw new StudentAlreadyExistException(student.getId() +" whit this id student exist");
        studentMap.put(student.getId(), student);
    }


    public void updateStudent(StudentRequest student) {
        if(!studentMap.containsKey(student.getId()))
            throw new StudentNotFountException(student.getId() +"with id student not found");
        studentMap.put(student.getId(), student);

    }
}
