package warsztaty.spring.ailleron.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/users/{name}")
    public String getSurnameByName(@PathVariable String name) {
        if ("Mateusz".equals(name)) {
            return "Mnich";
        } else if ("Aleksander".equals(name)) {
            return "KwaśniewskiASDASDAS";
        }
        return "";
    }

}
