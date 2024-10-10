package az.technofest.dao.repository;

import az.technofest.dao.entity.Student;
import az.technofest.model.projection.StudentProjection;
import az.technofest.model.response.StudentResponse;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


    @Query("select s.fullName from Student s where s.id=:studentId")
    String adininiTap(Long studentId);

    List<Student> findStudentByGroupId(Long groupId);


    @Query(value = "select s.name from company.STUDENTS s where s.id=:studentId", nativeQuery = true)
    String findName(Long studentId);


    @Query(value = "select s.name from company.STUDENTS s where s.id=:studentId", nativeQuery = true)
    StudentResponse findStudentResponseWithNative(Long studentId);


    @Query(value = """
            select new az.technofest.model.response.StudentResponse ( s.fullName, s.groupId, s.scholarShip) 
            from Student s 
            where s.id=:studentId
            """)
    StudentResponse findStudentResponseWithJpql(Long studentId);


    //    @Query("SELECT new com.example.UserDTO(u.firstName, u.lastName) FROM User u WHERE u.lastName = :lastName")
    StudentProjection findAllById(Long studentId);


    @Query("select s from Student s where s.groupId=:groupId")
    Page<Student> findAllByGrup(@Param("groupId") Integer group, Pageable pageable);


//
//    List<UserDTO> findUsersByLastName(String lastName);


}
