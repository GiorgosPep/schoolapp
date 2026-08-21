package gr.aueb.cf.schoolapp10.service;

import gr.aueb.cf.schoolapp10.core.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.schoolapp10.core.exceptions.EntityInvalidArgumentException;
import gr.aueb.cf.schoolapp10.dto.UserInsertDTO;
import gr.aueb.cf.schoolapp10.dto.UserReadOnlyDTO;

public interface IUserService {
    UserReadOnlyDTO saveUser(UserInsertDTO userInsertDTO)
        throws EntityAlreadyExistsException, EntityInvalidArgumentException;
}
