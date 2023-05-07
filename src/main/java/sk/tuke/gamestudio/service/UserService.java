package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.User;

public interface UserService {
    void registerUser(User user);
    User getUserByUsername(String username);
}
