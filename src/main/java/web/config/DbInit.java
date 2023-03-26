package web.config;

import org.springframework.stereotype.Component;
import web.Model.User;
import web.Service.UserService;

import javax.annotation.PostConstruct;

@Component
public class DbInit {

    private final UserService userService;

    public DbInit(UserService userService) {
        this.userService = userService;
    }


    @PostConstruct
    private void createDefaultusers() {

        User user = new User("user", "email");
        userService.addUser(user);

        User user1 = new User("users", "emails");
        userService.addUser(user1);
    }

}