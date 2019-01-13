package pl.reaktor.projektblog.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.reaktor.projektblog.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(String role);
}
