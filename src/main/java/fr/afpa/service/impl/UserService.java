package fr.afpa.service.impl;

import fr.afpa.entity.dao.AuthorityDAO;
import fr.afpa.entity.dao.UserDAO;
import fr.afpa.entity.dto.UserDTO;
import fr.afpa.entity.dto.UserSecurity;
import fr.afpa.repository.IUserRepository;
import fr.afpa.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    // password encode permet d'encoder le mot de passe avant de l'enregistrer en BDD
    // Pour fonctionner, PasswordEncoder est défini dans SecurityConfig (dans le package Security)
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Long save(UserSecurity userSecurity) {
        UserDAO userDAO = new UserDAO();
        userDAO.setUsername(userSecurity.getUsername());
        userDAO.setPassword(passwordEncoder.encode(userSecurity.getPassword())); //On encode le password avec PasswordEncoder
        userDAO.setAuthorities(
                userSecurity.getAuthorities()
                            .stream()
                            .map(AuthorityDAO::new)
                            .collect(Collectors.toSet())
        );
        return userRepository.save(userDAO).getId();
    }

    @Override
    public UserDTO findOneByUsername(String username) {
        UserDAO userDAO = userRepository.findByUsername(username);
        if(userDAO == null) return  null; //Si jamais le user n'existe pas, on renvoi directement null
        UserDTO userDTO = new UserDTO(userDAO.getId(), userDAO.getUsername(), userDAO.getAuthorities().stream().map(AuthorityDAO::getAuthority).collect(Collectors.toList()));
        return userDTO;
    }

}
