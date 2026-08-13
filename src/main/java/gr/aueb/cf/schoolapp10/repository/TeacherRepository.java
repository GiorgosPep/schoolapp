package gr.aueb.cf.schoolapp10.repository;

import gr.aueb.cf.schoolapp10.model.Teacher;
import jakarta.persistence.Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByVat(String vat);
    Optional<Teacher> findByUuid(UUID uuid);

    @EntityGraph(attributePaths = {"region"})
    Page<Teacher> findAllByDeletedFalse(Pageable pageable);

    Optional<Teacher> findByVatDeletedFalse(String vat);
    Optional<Teacher> findByUuidAndDeletedFalse(UUID uuid);
}
