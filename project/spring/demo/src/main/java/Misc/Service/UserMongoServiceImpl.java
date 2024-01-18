package Misc.Service;

import Misc.model.UserMongo;
import Misc.repository.UserMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMongoServiceImpl implements UserMongoService {

    @Autowired
    private UserMongoRepository userMongoRepository;

    public UserMongo findByEmail(String email) {
        return userMongoRepository.findByEmail(email);
    }


}
