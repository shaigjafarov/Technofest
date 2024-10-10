package az.technofest.service;

import az.technofest.client.ProductClient;
import az.technofest.client.StudentClient;
import az.technofest.dao.entity.Student;
import az.technofest.dao.repository.StudentDynamicQuery;
import az.technofest.dao.repository.StudentRepository;
import az.technofest.exception.StudentNotFountException;
import az.technofest.model.dto.FakeStudentDTO;
import az.technofest.model.dto.ProductDto;
import az.technofest.model.projection.StudentProjection;
import az.technofest.model.request.StudentRequest;
import az.technofest.model.response.StudentResponse;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final StudentDynamicQuery studentDynamicQuery;
    private final StudentClient studentClient;
    private final ProductClient productClient;

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
        } else throw new ArithmeticException(String.format("user id: %s", studentId));


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


    public List<Student> filterStudents(StudentRequest studentRequest) {

        return studentDynamicQuery.filterStudent(studentRequest);


    }


    public Page<Student> getStudent(Integer pageNumber, Integer pageSize, Integer group) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "scholarShip"));
        return studentRepository.findAllByGrup(group, pageable);
    }


    public List<FakeStudentDTO> getFakeStudent() {
//        String url = "https://fake-json-api.mock.beeceptor.com/users";
//
//        RestTemplate restTemplate= new RestTemplate();
//
//       return restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<FakeStudentDTO>>() {
//                }).getBody();

        return studentClient.getFakeStudent();
    }

    public ProductDto addPost(ProductDto productDto) {

 /*       String url = "https://fakestoreapi.com/products";

        RestTemplate restTemplate= new RestTemplate();

        HttpEntity<ProductDto> request = new HttpEntity<>(productDto);

        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                ProductDto.class)
                .getBody();

*/

        return productClient.addProduct(productDto);
    }

    public ProductDto getFakeProduct() {

        return productClient.getProduct();
    }
}
