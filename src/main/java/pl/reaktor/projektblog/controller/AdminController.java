package pl.reaktor.projektblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.reaktor.projektblog.model.User;
import pl.reaktor.projektblog.service.UserService;

import java.util.List;

@Configuration
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/config")
    public String config (Model model){
        List<User> allUser = userService.getUsersWithRoleUser();
        model.addAttribute("users", allUser);
        return "admin/config";
    }


}
