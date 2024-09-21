package az.technofest.dao.repository;

import az.technofest.dao.entity.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


    @Query("select s.fullName from Student s where s.id=:studentId")
    String adininiTap( Long studentId);



    List<Student> findStudentByGroupId(Long groupId);



}
