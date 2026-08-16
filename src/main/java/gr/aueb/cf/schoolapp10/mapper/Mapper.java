package gr.aueb.cf.schoolapp10.mapper;


import gr.aueb.cf.schoolapp10.dto.*;
import gr.aueb.cf.schoolapp10.model.Region;
import gr.aueb.cf.schoolapp10.model.Teacher;
import org.springframework.stereotype.Component;

@Component
public class Mapper {


    public Teacher mapToTeacherEntity(TeacherInsertDTO teacherInsertDTO) {
        return new Teacher(teacherInsertDTO.firstname(),teacherInsertDTO.lastname(),teacherInsertDTO.vat());
    }

    public TeacherReadOnlyDTO mapToTeacherReadOnlyDTO(Teacher teacher) {
        return new TeacherReadOnlyDTO(teacher.getUuid().toString(), teacher.getFirstname(),
                teacher.getLastname(), teacher.getVat(), teacher.getRegion().getName());
    }

    public RegionReadOnlyDTO mapToRegionReadOnlyDTO(Region region) {
        return new RegionReadOnlyDTO(region.getId(), region.getName());
    }

    public TeacherEditDTO mapToTeacherEditDTO(Teacher teacher) {
        return new TeacherEditDTO(teacher.getUuid(), teacher.getFirstname(),
                teacher.getLastname(), teacher.getVat(), teacher.getRegion().getId());
    }

}
