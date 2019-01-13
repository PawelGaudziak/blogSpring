package pl.reaktor.projektblog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.reaktor.projektblog.model.Role;
import pl.reaktor.projektblog.model.User;
import pl.reaktor.projektblog.repository.RoleRepository;
import pl.reaktor.projektblog.service.UserService;

import javax.persistence.ManyToMany;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;
private RoleRepository roleRepository;

@Autowired
    public UserController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

     @GetMapping("/users/{id}")
    //przemapowanie po id zadeklarowanych powyżej
    public String view(@PathVariable(name = "id") long userId, Model model) {

        User userById = userService.getUserById(userId);
        model.addAttribute("user", userById);
        //folder user a wnim strona html view.html, która będzie wyświetlała dane użytkownika
        return "user/view"; //wyświetelenie strony html
    }

    @GetMapping("/users/delete/{id}") //adres w przegladarce, mogą być różne id
    public String delete (@PathVariable(name = "id")long userId){

       userService.deleteUser(userId);
        //redirect - przekierowanie na konkretny adres url
        return "redirect:/admin/config";
    }

    @GetMapping("/users/edit/{id}")
    public String edit(@PathVariable(name="id") long userId, Model model){

        User userById = userService.getUserById(userId);
        model.addAttribute("user", userById);
        List<Role> allRole = roleRepository.findAll();
        model.addAttribute("roles",allRole);
        return"user/edit";
    }
    @PostMapping("/editUser")
    public  String userEdit (@Valid @ModelAttribute User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/edit";
        }
userService.editUser(user);
       //przekierowanie na widok do wyświetlenia użytkownika, którego zapisaliśmy
        return "redirect:/users/" + user.getId();
    }
}
