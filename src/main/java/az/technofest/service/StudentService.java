package az.technofest.service;

import az.technofest.dao.entity.Student;
import az.technofest.dao.repository.StudentDynamicQuery;
import az.technofest.dao.repository.StudentRepository;
import az.technofest.exception.StudentNotFountException;
import az.technofest.model.projection.StudentProjection;
import az.technofest.model.request.StudentRequest;
import az.technofest.model.response.StudentResponse;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final StudentDynamicQuery studentDynamicQuery;

    private Optional<Student> byId;


    public void addStudent(StudentRequest request) {

        Student student = Student.builder()
                .fullName(request.getName())
                .groupId(request.getGroup())
                .scholarShip(request.getSalary())
                .build();

        studentRepository.save(student);
    }

    public StudentResponse getStudent(Long studentId) {

        var studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {

            var student = studentOptional.get();
            return StudentResponse.builder()
                    .name(student.getFullName())
                    .groupNumber(student.getGroupId())
                    .scholarship(student.getScholarShip())
                    .build();
        } else throw new StudentNotFountException(String.format("user id: %s", studentId));


    }

    public String getStudentName(Long studentId) {

//        return studentRepository.adininiTap(studentId);
        return studentRepository.findName(studentId);
    }

    public StudentResponse getStudentWithProjection(Long studentId) {

        return studentRepository.findStudentResponseWithJpql(studentId);
    }

    public StudentProjection getStudentWithProjectionSql(Long studentId) {


        return studentRepository.findAllById(studentId);
    }


    public List<Student> filterStudents (StudentRequest studentRequest){

        return studentDynamicQuery.filterStudent(studentRequest);


    }

}
