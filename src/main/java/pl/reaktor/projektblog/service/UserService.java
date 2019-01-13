package pl.reaktor.projektblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.reaktor.projektblog.model.Role;
import pl.reaktor.projektblog.model.User;
import pl.reaktor.projektblog.repository.RoleRepository;
import pl.reaktor.projektblog.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service //logika rejestracji użytkownika
public class UserService {

    //odwołanie do user repository
    private UserRepository userRepository;

    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User addUserWithRoleUser(User user) {

        //aktywujemy użytkownika przed zapisem do bazy danych
        user.setActive(true);

        //kodowanie hasła użytkownika przed zapisem do bazy danych
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        //ustawienie roli user dla użytkownika
        Role role = roleRepository.findByRole("user");
        if (role == null) {
            //jeśli rola nie istnieje, to ją tworzymy i robimy zapsi do bazy danych

            Role roleToSave = new Role("user");
            //po utworzeniu i sprawdzeniu czy rola istnieje ustawiamy ją dla użytkownika
            role = roleRepository.save(roleToSave);
        }
        user.setRole(role);

        //zapis usera do bazy danych
        User saveUser = userRepository.save(user);

        return saveUser;
    }
public List<User> getAllUser() {
    List<User> users = userRepository.findAll(Sort.by("role.role", "email").ascending());
    return users;
}

public List<User> getUsersWithRoleUser(){
        List<User> users = userRepository.findUserWithRoleUserSQL("user");
        return users;
}
public User getUserById(long userId) {
    Optional<User> optionalUser = userRepository.findById(userId);

    //sprawdzenie czy klasa posiada wartość  - usera pobranego z bazy danych
    if (optionalUser.isPresent()) {
       //pobranie z Optionala konkretnego użytkownika który został pobrany z bazy danych o zadanym id
        return optionalUser.get();
    }
    return null;
}
public void deleteUser(long userId) {
        userRepository.deleteById(userId);
}

public  User editUser(User user) {
        //pobieramy użytkownika z bazy danych w celu pobrania jego hasła
    //ponieważ na formularzu nie mamy zmiany hasła
        User userFromDB = getUserById(user.getId());
        //ustawienie hasła z bazy danych do obiektu z formularza
        user.setPassword(userFromDB.getPassword());
        //zapisukje hasło do bazy
        User saveUser = userRepository.save(user);
        return saveUser;
}
}
