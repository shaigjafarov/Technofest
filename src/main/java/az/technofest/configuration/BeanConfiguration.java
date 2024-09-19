package az.technofest.configuration;

import az.technofest.service.StudentService;
import az.technofest.service.TeacherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

@Configuration
public class BeanConfiguration {


    @Lazy
    @Bean("a1Group")
    @Primary
    public TeacherService getTeacherService(){
        System.out.println("Teacher Bean INITIALIZE OLUNDU");
        return new TeacherService();
    }


    @Lazy
    @Bean("b1Group")
    public TeacherService getTeacherService1(){
        System.out.println("Teacher Bean INITIALIZE OLUNDU");
        return new TeacherService();
    }


    @Lazy
    @Bean
    public StudentService getStudentService(TeacherService teacherService){
        System.out.println("Student Bean INITIALIZE OLUNDU");
        return new StudentService(teacherService);
    }


}
