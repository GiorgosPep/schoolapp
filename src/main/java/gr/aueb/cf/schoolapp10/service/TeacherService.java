package gr.aueb.cf.schoolapp10.service;

import gr.aueb.cf.schoolapp10.core.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.schoolapp10.core.exceptions.EntityInvalidArgumentException;
import gr.aueb.cf.schoolapp10.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp10.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp10.mapper.Mapper;
import gr.aueb.cf.schoolapp10.model.Region;
import gr.aueb.cf.schoolapp10.model.Teacher;
import gr.aueb.cf.schoolapp10.repository.RegionRepository;
import gr.aueb.cf.schoolapp10.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeacherService implements ITeacherService{

    private final TeacherRepository teacherRepository;
    private final RegionRepository regionRepository;
    private final Mapper mapper;

    @Override
    @Transactional(rollbackFor = {EntityAlreadyExistsException.class, EntityInvalidArgumentException.class})
    public TeacherReadOnlyDTO saveTeacher(TeacherInsertDTO dto)
            throws EntityAlreadyExistsException, EntityInvalidArgumentException {

        try{
            if(dto.vat() != null && isTeacherExistsByVat(dto.vat())) {
                throw new EntityAlreadyExistsException("Teacher with VAT= " + dto.vat() + " already exists");
            }

            Region region = regionRepository.findById(dto.regionId())
                    .orElseThrow(() -> new EntityInvalidArgumentException("Region with id= " + dto.regionId() + " not found"));

            Teacher teacher = mapper.mapToTeacherEntity(dto);
            region.addTeacher(teacher);
            teacherRepository.save(teacher);        // pre-persist - saved teacher
            log.info("Teacher saved with vat= {} saved successfully ", dto.vat()); // Structured Logging -- parameterized placeholder pattern
            return mapper.mapToTeacherReadOnlyDTO(teacher);

        } catch (EntityAlreadyExistsException e) {
            log.warn("Save failed for teacher with VAT= {}. Teacher already exists ", dto.vat());
            throw e;
        } catch (EntityInvalidArgumentException e) {
            log.warn("Save failed for teacher with VAT= {}. Region with id={} invalid", dto.vat(), dto.regionId());
            throw e;
        } catch (DataIntegrityViolationException e) {
            log.warn("Save failed for teacher with VAT= {}. Teacher already exists ", dto.vat());
            throw new EntityAlreadyExistsException("Save failer for teacher with VAT= " + dto.vat() + " already exists");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isTeacherExistsByVat(String vat) {
        return teacherRepository.findByVAT(vat).isPresent();
    }
}
