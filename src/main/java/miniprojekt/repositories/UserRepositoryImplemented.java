package miniprojekt.repositories;

import miniprojekt.domain.models.User;
import miniprojekt.domain.MiniProjektException;
import miniprojekt.domain.models.Wishlist;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImplemented implements UserRepository {

    public User createUser(User user) throws MiniProjektException {

        try{
            String sqlStr = "INSERT INTO users(username, password) VALUES (?, ?)";
            Connection conn = DBManager.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement(sqlStr);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();

            return user;
        } catch (SQLException regErr) {
            System.out.println("Error in creating user");
            throw new MiniProjektException(regErr.getMessage());
        }
    }

    public User login(String username, String password) throws MiniProjektException {
        try{
            Connection conn = DBManager.getConnection();
            String sqlStr = "SELECT userID FROM users WHERE username = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sqlStr);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                int userID = rs.getInt("userID");
                User user = new User(username, password);
                user.setID(userID);
                return user;
            } else {
                throw new MiniProjektException("Could not figure out ID");
            }
        } catch (SQLException loginErr) {
            System.out.println("Error in logging in");
            throw new MiniProjektException(loginErr.getMessage());
        }
    }

    public List<Wishlist> fetchWishlist(User user) {
        List<Wishlist> wishlist = new ArrayList<>();
        int userID = user.getID();

        try{
            String sqlStr = "SELECT * FROM wishlists WHERE userID = ?";
            Connection conn = DBManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlStr);

            ps.setInt(1,  userID);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Wishlist list = new Wishlist(
                    rs.getString("itemName"),
                    rs.getInt("itemQuantity"),
                    rs.getString("itemLink"),
                    rs.getInt("wishlistID")
                );
                wishlist.add(list);
            }
            //return wishlist;
        } catch (SQLException wlErr) {
            System.out.println("Something went wrong");
            System.out.println(wlErr.getMessage());
        }
        return wishlist;
    }

    public List<Wishlist> fetchAllWishlists() {
        List<Wishlist> wishlist = new ArrayList<>();

        try{
            //Make if statement to fetch all if admin? (either specific name/id or userroles for multiple admins)
            String sqlStr = "SELECT * FROM wishlists";
            Connection conn = DBManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlStr);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Wishlist list = new Wishlist(
                        rs.getString("itemName"),
                        rs.getInt("itemQuantity"),
                        rs.getString("itemLink"),
                        rs.getInt("wishlistID")
                );
                wishlist.add(list);
            }
            //return wishlist;
        } catch (SQLException wlErr) {
            System.out.println("Something went wrong");
            System.out.println(wlErr.getMessage());
        }
        return wishlist;
    }

    public Wishlist addItem(String itemName, int itemQuantity, String itemLink, User user) throws MiniProjektException {
        try {
            int userID = user.getID();
            String sqlStr = "INSERT INTO wishlists(itemName, itemQuantity, itemLink, userID) VALUES (?, ?, ?, ?);";
            Connection conn = DBManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlStr);
            ps.setString(1, itemName);
            ps.setInt(2, itemQuantity);
            ps.setString(3, itemLink);
            ps.setInt(4, userID);
            ps.executeUpdate();

            return new Wishlist(itemName, itemQuantity);

        } catch (SQLException itemErr) {
            System.out.println("Something went wrong with making your wish");
            throw new MiniProjektException(itemErr.getMessage());
        }

    }

    public String deleteItem(int wishlistID) {
        try{
            String sqlStr = "DELETE wishlists.* FROM wishlists WHERE wishlistID = ?";
            Connection conn = DBManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlStr);
            ps.setInt(1, wishlistID);
            ps.executeUpdate();

            return "Success!";
        } catch (SQLException delErr) {
            System.out.println("Couldn't delete item, Error");
            System.out.println(delErr.getMessage());
        }
        return "redirect:/myPage";
    }

}
