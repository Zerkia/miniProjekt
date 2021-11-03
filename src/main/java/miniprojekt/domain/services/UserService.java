package miniprojekt.domain.services;

import miniprojekt.domain.models.User;
import miniprojekt.domain.MiniProjektException;
import miniprojekt.domain.models.Wishlist;

import java.util.List;

import miniprojekt.repositories.UserRepository;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username, String password) throws MiniProjektException {
        return userRepository.login(username, password);
    }

    public User createUser(String username, String password) throws MiniProjektException {
        User user = new User(username, password);
        return userRepository.createUser(user);
    }

    public List<Wishlist> fetchWishList(int i) {
        return userRepository.fetchWishList(i);
    }
}
