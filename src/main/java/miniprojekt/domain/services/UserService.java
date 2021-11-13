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

    public List<Wishlist> fetchWishlist(User user) {
        return userRepository.fetchWishlist(user);
    }

    public List<Wishlist> fetchAllWishlists() { return userRepository.fetchAllWishlists(); }

    public Wishlist addItem(String itemName, int itemQuantity, String itemLink, User user) throws MiniProjektException {
        return userRepository.addItem(itemName, itemQuantity, itemLink, user);
    }

    public String deleteItem(int wishlistID){ return userRepository.deleteItem(wishlistID); }
}
