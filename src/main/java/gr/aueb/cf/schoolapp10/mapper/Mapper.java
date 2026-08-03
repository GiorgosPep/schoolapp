package gr.aueb.cf.schoolapp10.mapper;


import gr.aueb.cf.schoolapp10.dto.RegionReadOnlyDTO;
import gr.aueb.cf.schoolapp10.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp10.dto.TeacherReadOnlyDTO;
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

}
