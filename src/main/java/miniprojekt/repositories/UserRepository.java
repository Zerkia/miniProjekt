package miniprojekt.repositories;
import miniprojekt.domain.miniProjektException;
import miniprojekt.domain.models.User;

public interface UserRepository {
    public User createUser(User user) throws miniProjektException;
    public User login(String username, String password) throws miniProjektException;
}
