package az.technofest.controller;


import az.technofest.dao.entity.Teacher;
import az.technofest.model.request.TeacherRequest;
import az.technofest.service.TeacherService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<Teacher> addTeacher(@RequestBody TeacherRequest request) {

        return teacherService.addTeacher(request);
    }



    @PutMapping
    public void sdcfsdc(@RequestParam Long teacherId, @RequestParam Long salary ) {
         teacherService.updateTeacher(teacherId, salary);
    }


    @GetMapping
    public List<Teacher> getTeacher() {

        return teacherService.getTeachers();
    }



}
