package gr.aueb.cf.schoolapp10.service;

import gr.aueb.cf.schoolapp10.core.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.schoolapp10.core.exceptions.EntityInvalidArgumentException;
import gr.aueb.cf.schoolapp10.core.exceptions.EntityNotFoundException;
import gr.aueb.cf.schoolapp10.dto.TeacherEditDTO;
import gr.aueb.cf.schoolapp10.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp10.dto.TeacherReadOnlyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITeacherService {

    TeacherReadOnlyDTO saveTeacher(TeacherInsertDTO teacherInsertDTO)
        throws EntityAlreadyExistsException, EntityInvalidArgumentException;

    TeacherReadOnlyDTO updateTeacher(TeacherEditDTO teacherEditDTO)
        throws EntityNotFoundException, EntityAlreadyExistsException, EntityInvalidArgumentException;

    boolean isTeacherExistsByVat(String vat);
    Page<TeacherReadOnlyDTO> getPaginatedTeachersDeletedFalse(Pageable pageable);
    Page<TeacherReadOnlyDTO> getPaginatedTeachers(Pageable pageable);



    }
