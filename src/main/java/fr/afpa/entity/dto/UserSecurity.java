package fr.afpa.entity.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserSecurity {
    private String username;
    private String password;
    private List<String> authorities = new ArrayList<>();
}