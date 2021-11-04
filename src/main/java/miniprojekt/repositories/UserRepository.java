package miniprojekt.repositories;
import miniprojekt.domain.MiniProjektException;
import miniprojekt.domain.models.User;
import miniprojekt.domain.models.Wishlist;

import java.util.List;

public interface UserRepository {
    public User createUser(User user) throws MiniProjektException;
    public User login(String username, String password) throws MiniProjektException;
    public List<Wishlist> fetchWishlist(User user);
    public List<Wishlist> fetchAllWishlists();
    public Wishlist addItem(String itemName, int itemQuantity, User user) throws MiniProjektException;
    public String deleteItem(int wishlistID);
}
