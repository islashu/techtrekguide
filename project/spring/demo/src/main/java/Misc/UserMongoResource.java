package Misc;

import Misc.Service.UserMongoService;
import Misc.model.UserMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserMongoResource {

    @Autowired
    private UserMongoService userMongoService;

    @GetMapping("/email")
    ResponseEntity<UserMongo> getAllUsers() {
        UserMongo user = userMongoService.findByEmail("hello@gmai.com");
        System.out.println("User: " + user);
        return ResponseEntity.ok().body(user);
    }
}
