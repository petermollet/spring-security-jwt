package fr.afpa.controller;

import fr.afpa.controller.exception.UsernameAlreadyExistException;
import fr.afpa.entity.dto.UserDTO;
import fr.afpa.entity.dto.UserSecurity;
import fr.afpa.security.AuthorityConstant;
import fr.afpa.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * REST: {POST: /register}
     * Controlleur pour pouvoir créer un nouveau compte
     * Vérifie d'abord si le compte existe ou non en BDD
     *
     * @param userSecurity
     * @return Long: id du compte créé
     */
    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody UserSecurity userSecurity) {
        UserDTO userDTO = userService.findOneByUsername(userSecurity.getUsername());
        if(userDTO != null) {
            throw new UsernameAlreadyExistException();
        }
        Long idSaved = userService.save(userSecurity);
        return ResponseEntity.ok(idSaved);
    }

    /**
     * Simpl controlleur sécurisé, pour tester le token JWT
     *
     * @return
     */
    @GetMapping("/hello")
    public String hello() {
        return "Hello world";
    }

    @Secured({AuthorityConstant.ROLE_USER, AuthorityConstant.ROLE_ADMIN})
    @GetMapping("/hello-user")
    public String helloAdmin() {
        return "Hello user";
    }

    @Secured(AuthorityConstant.ROLE_ADMIN)
    @GetMapping("/hello-admin")
    public String helloUser() {
        return "Hello admin";
    }
}
