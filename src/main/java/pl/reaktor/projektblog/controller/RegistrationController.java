package pl.reaktor.projektblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.reaktor.projektblog.model.User;
import pl.reaktor.projektblog.service.UserService;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration") //mapuje stronę registration
    public String registration(Model model) {

        //inicjalizujemy obiekt user żeby można było go uzupełnić na formularzu danymi
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String save(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
if(bindingResult.hasErrors()){
    //wyświetlenie strony, jeśli znajdziemy błędy walidacyjne
    return "registration";

}
        //chcemy zapisać użytkownika do bazy danych
        //do tego potrzebujemy serwisu, który wywoła przy pomocy repository zapis do bazy danych
        User userSaved = userService.addUserWithRoleUser(user);
        System.out.println("Saved user: " + userSaved);


        //wyczysczenie formularza, gdy pozostajemy na tym samym widoku
        //tworzymy nowego usera
        User user1 = new User();
        model.addAttribute("success", "User poprawnie się zarejestrował");
        model.addAttribute("user", user1);
        return "registration";
    }
}

