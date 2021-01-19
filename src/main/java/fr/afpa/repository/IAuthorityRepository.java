package fr.afpa.repository;

import fr.afpa.entity.dao.AuthorityDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorityRepository extends JpaRepository<AuthorityDAO, String> {
}
