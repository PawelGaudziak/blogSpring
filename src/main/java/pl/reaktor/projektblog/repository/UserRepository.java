package pl.reaktor.projektblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import pl.reaktor.projektblog.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //wyświetlamy tylko userów
@Query(value = "SELECT u.* FROM user u inner join role r on u.role_id=r.id WHERE role =:name", nativeQuery = true)
List<User> findUserWithRoleUserSQL(@Param("name")String roleName);


@Query("SELECT u FROM User u WHERE u.role.role =:name")
List<User> findUserWithRoleUserHQL(@Param("name")String roleName);


//@Query("SELECT u FROM User u INNER JOIN Role r ON u.role.id=id_r WHERE r.role ='user'")
//List<User> findUserWithRoleUserHQL2();
}
