package fr.afpa.service.impl;

import fr.afpa.entity.dao.UserDAO;
import fr.afpa.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDAO userDAO = userRepository.findByUsername(username);
        if(userDAO == null) throw new UsernameNotFoundException("User not found with username : " + username);
        return new User(userDAO.getUsername(), userDAO.getPassword(), userDAO.getAuthorities());
    }
}
