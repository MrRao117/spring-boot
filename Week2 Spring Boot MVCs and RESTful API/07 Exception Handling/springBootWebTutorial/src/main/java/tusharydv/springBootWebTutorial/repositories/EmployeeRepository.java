package tusharydv.springBootWebTutorial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tusharydv.springBootWebTutorial.entities.EmployeeEntity;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {

}
