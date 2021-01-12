package fr.afpa.controller;

import fr.afpa.entity.dto.AuthenticationResponse;
import fr.afpa.entity.dto.UserSecurity;
import fr.afpa.security.JwtUtil;
import fr.afpa.service.impl.JwtUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtUserDetailService userDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Request REST: {POST /login}
     * Permet de se connecter, si on reçoit des Credentials correctes
     * Si cela est bon, envoi au front un objet avec le JsonWebToken et le username de l'utilisateur
     *
     * @param userSecurity
     * @return AuthenticationResponse
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody UserSecurity userSecurity) {
        UsernamePasswordAuthenticationToken tokenSpring = new UsernamePasswordAuthenticationToken(
                userSecurity.getUsername(), userSecurity.getPassword()
        );
        try {
            //L'authentification se fait ici. C'est SpringSecurity qui vérifie les Crédentials reçu, et va pouvoir m'authentifier
            //Dans le cas contraire, renvoi une exception BadCredentialsException
            authenticationManager.authenticate(tokenSpring);
        } catch (BadCredentialsException ex) {
            throw ex;
        }
        //Je récupére le UserDetails coresspondant au username reçu
        UserDetails userDetails = userDetailService.loadUserByUsername(userSecurity.getUsername());
        //Avec UserDetails, je crée mon JsonWebToken
        String jwt = jwtUtil.generateToken(userDetails);
        //Je renvoi au front l'objet AuthenticationResponse contenant le JWT et username de l'utilisateur
        //Toutes requetes sécurisé en back aura donc besoin de ce JWT pour être effectué
        return ResponseEntity.ok(new AuthenticationResponse(jwt, userDetails.getUsername()));
    }

}
