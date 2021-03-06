package fr.afpa.entity.dao;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authority")
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class AuthorityDAO implements GrantedAuthority {

    @Id
    @Column(length = 20)
    private String name;


    @Override
    public String getAuthority() {
        return name;
    }
}
