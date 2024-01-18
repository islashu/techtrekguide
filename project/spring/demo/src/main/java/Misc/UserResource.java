package Misc;

import Misc.Service.UserService;
import Misc.model.InsurancePolicies;
import Misc.model.oldUser;
import Misc.repository.InsurancePoliciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path ="/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private InsurancePoliciesRepository insurancePoliciesRepository;

    @GetMapping(path = "/test")
    ResponseEntity<Void> test() {
        System.out.println("Server is running");
        return ResponseEntity.ok().body(null);
    }

    @GetMapping( "/{id}")
    ResponseEntity<oldUser> getAllUsers(@PathVariable Long id ) {
        oldUser user = userService.getAllUsers(id);
        System.out.println("User: " + user);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/save/{id}")
    ResponseEntity<Void> saveUser(@PathVariable Long id) {
        oldUser user = new oldUser();
        user = userService.saveUser(user);
        System.out.println("User: " + user.toString());
        InsurancePolicies insurancePolicies = new InsurancePolicies();
        insurancePolicies.setUser(user);
        insurancePolicies = insurancePoliciesRepository.save(insurancePolicies);
        System.out.println("InsurancePolicies: " + insurancePolicies.toString());
        oldUser newUser = userService.getAllUsers(id);
        System.out.println("getting new user details:" +  newUser.toString());
        System.out.println("getting new user details:" +  newUser.getInsurancePolicies().toString());
        return ResponseEntity.ok().body(null);
    }

    @PutMapping("/update/{id}")
    ResponseEntity<Void> updateUser(@PathVariable Long id) {
        userService.updateUser(id);
        return ResponseEntity.ok().body(null);
    }
}
