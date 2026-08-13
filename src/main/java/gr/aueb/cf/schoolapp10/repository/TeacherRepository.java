package gr.aueb.cf.schoolapp10.repository;

import gr.aueb.cf.schoolapp10.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByVat(String vat);
    Optional<Teacher> findByUuid(UUID uuid);

}
