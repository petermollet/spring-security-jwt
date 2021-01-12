package fr.afpa.service;

import fr.afpa.entity.dto.UserDTO;
import fr.afpa.entity.dto.UserSecurity;

public interface IUserService {

    Long save(UserSecurity userSecurity);

    UserDTO findOneByUsername(String username);

}
