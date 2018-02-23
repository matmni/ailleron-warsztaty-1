package warsztaty.spring.ailleron.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import warsztaty.spring.ailleron.exceptions.UserExistException;
import warsztaty.spring.ailleron.exceptions.UserNotFoundException;
import warsztaty.spring.ailleron.model.User;
import warsztaty.spring.ailleron.services.UserService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserController {

    @Autowired
    UserService service;

//    @GetMapping("/users/{name}")
//    public User getSurnameByName(@PathVariable String name) throws UserNotFoundException {
//        return service.getUserByName(name);
//    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) throws UserNotFoundException {
        return service.getUserById(id);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping("/users")
    public ResponseEntity<Resource<Long>> addUser(@RequestBody @Valid User user) throws UserExistException, UserNotFoundException {
        Long id = service.addUser(user);
        Resource<Long> resource = new Resource<>(id);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getUserById(id));
        resource.add(linkTo.withRel("get-user"));

        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }

    @PutMapping("/users")
    public ResponseEntity<User> modifyUser(@RequestBody User user) throws UserNotFoundException {
        User createdUser = service.modifyUser(user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(createdUser);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) throws UserNotFoundException {
        service.deleteUser(id);
    }

}
