package miniprojekt.domain.services;

import miniprojekt.domain.models.User;
import miniprojekt.domain.miniProjektException;
import miniprojekt.repositories.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import miniprojekt.repositories.UserRepository;
import miniprojekt.repositories.UserRepositoryImplemented;

public class UserService {
    private UserRepository userRepository = null;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username, String password) throws miniProjektException {
        return userRepository.login(username, password);
    }

    public User createUser(String username, String password) throws miniProjektException {
        User user = new User(username, password);
        return userRepository.createUser(user);
    }
}
