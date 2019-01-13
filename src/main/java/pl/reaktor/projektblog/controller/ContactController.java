package pl.reaktor.projektblog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.reaktor.projektblog.model.Contact;
import pl.reaktor.projektblog.service.EmailService;

import javax.validation.Valid;

@Controller
public class ContactController {

    private EmailService emailService;

    @Autowired
    public ContactController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/contact")
    public String contact(Model model) {

        Contact contact = new Contact();
        model.addAttribute("contact", contact);
        return "contact";
    }

    @PostMapping("/sentMessage")
    public String sent(@Valid @ModelAttribute Contact contact, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "contact";
        }

        emailService.sendMessage(contact);
        model.addAttribute("contact", new Contact());
        return "contact";
    }
}