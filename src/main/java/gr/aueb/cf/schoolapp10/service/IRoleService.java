package gr.aueb.cf.schoolapp10.service;

import gr.aueb.cf.schoolapp10.dto.RoleReadOnlyDTO;

import java.util.List;

public interface IRoleService {
    List<RoleReadOnlyDTO> findAllRolesSortedByName();
}
