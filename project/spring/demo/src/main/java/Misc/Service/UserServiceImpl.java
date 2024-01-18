package Misc.Service;

import Misc.repository.UserRepository;
import Misc.model.oldUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements  UserService{

    @Autowired
    private UserRepository userRepository;

    public oldUser getAllUsers(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public oldUser saveUser(oldUser user) {
        System.out.println("Saving the user");
        return userRepository.save(user);
    }

    @Override
    public oldUser updateUser(Long id) {
        oldUser user = userRepository.findById(id).get();

        if (user != null) {
            user.setFirstName("John");
            user.setLastName(user.getLastName() + "1");
        }
        System.out.println("Updating the user");
        return  userRepository.save(user);
    }
}
