package az.technofest.dao.repository;

import az.technofest.dao.entity.Student;
import az.technofest.model.request.StudentRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDynamicQuery {
    @PersistenceContext
    private EntityManager entityManager;



    public List<Student> filterStudent(StudentRequest request) {

        // Step 1: Get CriteriaBuilder
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        // Step 2: Create CriteriaQuery
        CriteriaQuery<Student> query = cb.createQuery(Student.class);

        // Step 3: Define the root for the query (from Student)
        Root<Student> studentRoot = query.from(Student.class);

        // Step 4: Build Predicates for the WHERE clause
        List<Predicate> predicates = new ArrayList<>();

        if (request.getSalary() != null) {
            predicates.add(cb.greaterThan(studentRoot.get("scholarShip"), request.getSalary()));
        }

        if (request.getGroup() != null ) {
            predicates.add(cb.equal(studentRoot.get("groupId"), request.getGroup()));
        }

        if (request.getName() != null && !request.getName().isEmpty()) {
            predicates.add(cb.equal(studentRoot.get("fullName"), request.getName()));
        }

        query.where(cb.and(predicates.toArray(new Predicate[0])));

        // Step 6: Execute the query
        return entityManager.createQuery(query).getResultList();
    }



}





