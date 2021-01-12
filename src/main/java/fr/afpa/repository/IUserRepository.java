package fr.afpa.repository;

import fr.afpa.entity.dao.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserDAO, Long> {

    UserDAO findByUsername(String username);

}
