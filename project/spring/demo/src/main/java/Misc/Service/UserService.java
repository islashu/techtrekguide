package Misc.Service;

import Misc.model.oldUser;

public interface UserService {

    public oldUser getAllUsers(Long id);

    public oldUser saveUser(oldUser user);

    public oldUser updateUser(Long id);
}
