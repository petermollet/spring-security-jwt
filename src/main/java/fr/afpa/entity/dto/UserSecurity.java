package fr.afpa.entity.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserSecurity {
    private String username;
    private String password;
}