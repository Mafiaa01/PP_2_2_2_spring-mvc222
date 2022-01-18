package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.DAO.UserDAO;
import web.Model.User;


@Controller
public class PeopleController {
    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    @GetMapping(value = "users")
    public String allUsers(Model model) {
        model.addAttribute("users", userDAO.getAllUsers());
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
        userDAO.addUser(user);
        return "redirect:/users";
    }

    @GetMapping(value = "users/edit/{id}")
    public String editUser(Model model, @PathVariable("id") Long id) {
        User user = userDAO.getUserById(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping(value = "users/edit")
    public String edit(@ModelAttribute("user") User user) {
        userDAO.editUser(user);
        return "redirect:/users";
    }

    @GetMapping("users/delete")
    public String deleteUserById(@RequestParam("id") Long id) {
        userDAO.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("users/{id}")
    public String show(@PathVariable("id") Long id, Model modelMap) {
        modelMap.addAttribute("user", userDAO.getUserById(id));
        return "show";
    }

}