package gr.aueb.cf.schoolapp10.service;

import gr.aueb.cf.schoolapp10.core.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.schoolapp10.core.exceptions.EntityInvalidArgumentException;
import gr.aueb.cf.schoolapp10.dto.UserInsertDTO;
import gr.aueb.cf.schoolapp10.dto.UserReadOnlyDTO;
import gr.aueb.cf.schoolapp10.mapper.Mapper;
import gr.aueb.cf.schoolapp10.model.Role;
import gr.aueb.cf.schoolapp10.model.User;
import gr.aueb.cf.schoolapp10.repository.RoleRepository;
import gr.aueb.cf.schoolapp10.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final Mapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    @Transactional(rollbackFor = { EntityAlreadyExistsException.class, EntityInvalidArgumentException.class })
    public UserReadOnlyDTO saveUser(UserInsertDTO userInsertDTO)
            throws EntityAlreadyExistsException, EntityInvalidArgumentException {
        try {
            if (userRepository.findByUsername(userInsertDTO.username()).isPresent()) {
                throw new EntityAlreadyExistsException("User with username=" + userInsertDTO.username() + " already exists");
            }
            User user = mapper.mapToUserEntity(userInsertDTO);
            user.setPassword(passwordEncoder.encode(userInsertDTO.password()));
            Role role = roleRepository.findById(userInsertDTO.roleId())
                    .orElseThrow(() -> new EntityInvalidArgumentException("Role id=" + userInsertDTO.roleId() + " invalid"));
            role.addUser(user);
            userRepository.save(user);
            log.info("Save succeeded for user with username={}.", userInsertDTO.username());
            return mapper.mapToUserReadOnlyDTO(user);
        } catch (EntityAlreadyExistsException e) {
            log.error("Save failed. User with username={} already exists", userInsertDTO.username());
            throw e;
        } catch (EntityInvalidArgumentException e) {
            log.error("Save failed. Invalid arguments for user with username={}", userInsertDTO.username());
            throw e;
        }
    }
}
