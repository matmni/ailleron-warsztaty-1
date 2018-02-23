package warsztaty.spring.ailleron.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import warsztaty.spring.ailleron.exceptions.UserExistException;
import warsztaty.spring.ailleron.exceptions.UserNotFoundException;
import warsztaty.spring.ailleron.model.User;
import warsztaty.spring.ailleron.services.UserService;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/users/{name}")
    public User getSurnameByName(@PathVariable String name) throws UserNotFoundException {
        return service.getUserByName(name);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping("/users")
    public Long addUSer(@RequestBody User user) throws UserExistException {
        return service.addUser(user);
    }


}
