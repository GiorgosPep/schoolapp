package gr.aueb.cf.schoolapp10.validator;

import gr.aueb.cf.schoolapp10.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp10.model.Teacher;
import gr.aueb.cf.schoolapp10.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Slf4j
@Component
public class TeacherInsertValidator implements Validator {

    private final TeacherService teacherService;

    @Override
    public boolean supports(Class<?> clazz) {
        return TeacherInsertDTO.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        TeacherInsertDTO teacherInsertDTO = (TeacherInsertDTO) target;

        if (teacherInsertDTO.vat() != null &&
                teacherService.isTeacherExistsByVat(teacherInsertDTO.vat())) {
            log.info("Validation failed. Teacher with VAT= {} already exists", teacherInsertDTO.vat());
            errors.rejectValue("vat", "vat.teacher.exists");     //TODO localization
        }
    }
}
