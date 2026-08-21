package gr.aueb.cf.schoolapp10.service;

import gr.aueb.cf.schoolapp10.dto.RoleReadOnlyDTO;
import gr.aueb.cf.schoolapp10.mapper.Mapper;
import gr.aueb.cf.schoolapp10.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {
    private final RoleRepository roleRepository;
    private final Mapper mapper;

    @Override
    public List<RoleReadOnlyDTO> findAllRolesSortedByName() {
        return roleRepository.findAllByOrderByNameAsc()
                .stream()
                .map(mapper::mapToRoleReadOnlyDTO)
                .toList();
    }
}
