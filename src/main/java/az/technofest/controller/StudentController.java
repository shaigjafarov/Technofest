package az.technofest.controller;

import az.technofest.configuration.DBConfig;
import az.technofest.configuration.KafkaConfig;
import az.technofest.model.Employer;
import az.technofest.model.request.StudentRequest;
import az.technofest.model.StudentCreateResponse;
import az.technofest.service.StudentService;
import az.technofest.service.TeacherService;
import jakarta.validation.Valid;
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
@RequestMapping("/student")
@Lazy
public class StudentController {


    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private DBConfig dbConfig;

    @Autowired
    private KafkaConfig kafkaConfig;


    @Autowired
    private Environment environment;


    @GetMapping
    public StudentRequest getUserByIdWithRequestParam(@RequestParam Long id) {
        StudentService studentService = new StudentService((TeacherService) applicationContext.getBean("a1Group"));
        dbConfig.getConnection();
        System.out.println(kafkaConfig.getName());
        System.out.println(kafkaConfig.getHost());
        System.out.println(kafkaConfig.getPort());

        System.out.println("get property from environment by name: "+environment.getProperty("check.flag"));

        return studentService.getById(id);

    }

    @GetMapping("/{userId}")
    public StudentRequest getUserByIdWithPathVariable(@PathVariable("userId") Long id) {
        StudentService studentService = new StudentService((TeacherService) applicationContext.getBean("a1Group"));

        return studentService.getById(id);
    }



    @PutMapping
    public ResponseEntity<StudentCreateResponse> updateStudent(@RequestBody StudentRequest request) {
        StudentService studentService = (StudentService) applicationContext.getBean("getStudentService");
        studentService.updateStudent(request);
        return new ResponseEntity<>(new StudentCreateResponse("success"), HttpStatus.OK);
    }



    @PostMapping("/employer")
    public ResponseEntity validEmployer(@Valid  @RequestBody Employer employer) {



        System.out.println(employer);

        return new ResponseEntity<>( HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<StudentCreateResponse> addStudent(@RequestBody @Valid StudentRequest student) {
        StudentService studentService = (StudentService) applicationContext.getBean("getStudentService");
        studentService.addStudent(student);
        return new ResponseEntity<>(new StudentCreateResponse("success"), HttpStatus.CREATED);
    }

}
