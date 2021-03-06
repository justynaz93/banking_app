package pl.sda.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.demo.dto.UserDto;
import pl.sda.demo.service.UserService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("user", new UserDto());
        return "registration-form";
    }


    @PostMapping("/registration")
    public String registrationAdding(Model model, @ModelAttribute("user") @Valid UserDto user2,
                                     BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            userService.add(user2);
            return "login-form";
        }

        model.addAttribute("users", new UserDto());
        return "registration-form";
    }

}
