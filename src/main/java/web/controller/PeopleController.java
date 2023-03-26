package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.Model.User;
import web.Service.UserService;


@Controller
public class PeopleController {
    private UserService userService;

    public PeopleController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "users")
    public String allUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping(value = "users/add")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "addUser";
    }

    @PostMapping(value = "users")
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping(value = "users/edit/{id}")
    public String editUser(Model model, @PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping(value = "users/edit")
    public String edit(@ModelAttribute("user") User user) {
        userService.editUser(user);
        return "redirect:/users";
    }

    @GetMapping("users/delete")
    public String deleteUserById(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("users/{id}")
    public String show(@PathVariable("id") Long id, Model modelMap) {
        modelMap.addAttribute("user", userService.getUserById(id));
        return "show";
    }

}