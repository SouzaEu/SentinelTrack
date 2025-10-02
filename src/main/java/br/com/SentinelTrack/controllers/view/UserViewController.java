package br.com.SentinelTrack.controllers.view;

import br.com.SentinelTrack.models.Motorcycle;
import br.com.SentinelTrack.models.User;
import br.com.SentinelTrack.models.enums.BikeType;
import br.com.SentinelTrack.models.enums.Sectors;
import br.com.SentinelTrack.services.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserViewController {

    private final UserService userService;

    public UserViewController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "users/form";
    }

    @PostMapping("/save")
    public String saveUser(@Valid @ModelAttribute("user") User user,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/form";
        }
        this.userService.postUser(user);
        return "redirect:/users";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") UUID  id){
        this.userService.deleteUserById(id);
        return "redirect:/users";
    }

    @GetMapping("/view/{id}")
    public String viewUser(@PathVariable("id") UUID id, Model model){
        User user = this.userService.findById(id);
        model.addAttribute("user", user);
        return "users/view";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") UUID id, Model model){
        User newUser = this.userService.findById(id);
        model.addAttribute("user", newUser);
        return "users/form";
    }
}

