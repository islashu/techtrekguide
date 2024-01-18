package Misc.Service;

import Misc.model.UserMongo;

public interface UserMongoService {

    public UserMongo findByEmail(String email);
}
