package az.technofest.controller;

import az.technofest.configuration.DBConfig;
import az.technofest.configuration.KafkaConfig;
import az.technofest.dao.entity.Student;
import az.technofest.dao.repository.StudentDynamicQuery;
import az.technofest.dao.repository.StudentRepository;
import az.technofest.model.Employer;
import az.technofest.model.projection.StudentProjection;
import az.technofest.model.request.StudentRequest;
import az.technofest.model.StudentCreateResponse;
import az.technofest.model.response.StudentResponse;
import az.technofest.service.StudentService;
import az.technofest.service.TeacherService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository studentRepository;

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentCreateResponse> addStudent(@RequestBody @Valid StudentRequest request) {
        studentService.addStudent(request);
        return new ResponseEntity<>(new StudentCreateResponse("success"), HttpStatus.CREATED);
    }


    @GetMapping
    public StudentResponse getStudent(@RequestParam("id") Long studentId) {

        return  studentService.getStudent(studentId);
    }   
    
    
    @GetMapping("/ad")
    public String getStudentName(@RequestParam("id") Long studentId) {

        return  studentService.getStudentName(studentId);
    }


    @GetMapping("/group")
    public List<Student> sdf(@RequestParam Long groupId) {

        return  studentRepository.findStudentByGroupId(groupId);
    }


    @GetMapping("/porojection")
    public StudentResponse getStudentWithProjection(@RequestParam("id") Long studentId) {

        return  studentService.getStudentWithProjection(studentId);
    }



    @GetMapping("/porojectionsql")
    public StudentProjection getStudentWithProjectionSQL(@RequestParam("id") Long studentId) {

        return  studentService.getStudentWithProjectionSql(studentId);
    }


    @PostMapping("/filter")
    public List<Student> getStudent(@RequestBody StudentRequest studentRequest) {

        return  studentService.filterStudents(studentRequest);
    }






}
