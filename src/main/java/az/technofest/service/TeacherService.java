package az.technofest.service;

import static az.technofest.builder.TeacherBuilder.buildTeacher;
import static java.util.Objects.nonNull;

import az.technofest.builder.StudentBuilder;
import az.technofest.builder.TeacherBuilder;
import az.technofest.dao.entity.Student;
import az.technofest.dao.entity.Teacher;
import az.technofest.dao.repository.TeacherRepository;
import az.technofest.model.StudentCreateResponse;
import az.technofest.model.request.TeacherRequest;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {


    private final TeacherRepository teacherRepository;


    public ResponseEntity<Teacher> addTeacher(TeacherRequest teacherRequest) {

        var teacher = buildTeacher(teacherRequest);
        if (nonNull(teacherRequest.getStudents())) {
            var studentList = teacherRequest.getStudents().stream()
                    .map(StudentBuilder::buildStudent)
                    .peek(student -> student.setTeacher(teacher))
                    .toList();
            teacher.setStudents(studentList);
        }

        return new ResponseEntity<>(teacherRepository.save(teacher), HttpStatus.CREATED);
    }


    public List<Teacher> getTeachers() {

        return teacherRepository.findAll();


    }

    public void updateTeacher(Long teacherId, Long salary) {

        Teacher teacher = teacherRepository.findById(teacherId).get();

        teacher.setSalary(salary);
        teacherRepository.save(teacher);
    }
}
