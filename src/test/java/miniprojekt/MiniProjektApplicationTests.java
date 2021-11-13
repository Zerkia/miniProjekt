package miniprojekt;

import miniprojekt.domain.MiniProjektException;
import miniprojekt.domain.models.User;
import miniprojekt.domain.models.Wishlist;
import miniprojekt.repositories.UserRepository;
import miniprojekt.repositories.UserRepositoryImplemented;
import miniprojekt.web.MyController;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class MiniProjektApplicationTests {
    @Autowired
    private MockMvc mvc;

    //private MyController controller;


    @Test
    void contextLoads() throws Exception{

    }

    @Test
    void LoginTest() throws MiniProjektException {
        UserRepositoryImplemented repo = new UserRepositoryImplemented();
        User user = repo.login("admin","admin");
        User user1 = new User("admin","admin");
        user1.setID(1);
        String str1 = user1.toString();
        String str2 = user.toString();
        assertThat(str1).isEqualTo(str2);
    }

    @Test
    void createUserTest() throws MiniProjektException {
        UserRepositoryImplemented repo = new UserRepositoryImplemented();
        Random random = new Random();
        String a ="ABCDEFGHJIKLMNOPQRSTUVWXYZ";
        int i = random.nextInt(23);
        int j = random.nextInt(23);
        int k = random.nextInt(23);
        String username = a.substring(i,i+1) + a.substring(j,j+1) + a.substring(k,k+1);
        String password = a.substring(k,k+1) + a.substring(j,j+1) + a.substring(i,i+1);

        System.out.println(username);
        System.out.println(password);

        User user1 = new User(username, password);
        User user2 = repo.createUser(user1);
        User user3 = repo.login(user2.getUsername(),user2.getPassword());
        String str1 = user1.toString();
        String str2 = user3.toString();
        System.out.println(str1);
        System.out.println(str2);
        assertThat(str1).isEqualTo(str2);

    }

    @Test
    void fetchWishListTest() throws MiniProjektException{
        UserRepositoryImplemented repo = new UserRepositoryImplemented();
        List<Wishlist> wishlist = new ArrayList<>();

        wishlist.add(new Wishlist("hund",222));
        wishlist.add(new Wishlist("hihi",5));
        wishlist.add(new Wishlist("mad",1));
        wishlist.add(new Wishlist("ad",1));

        User user = repo.login("admin","admin");
        List<Wishlist> wishlist1 = repo.fetchWishlist(user);
        String str1 = wishlist.toString();
        String str2 = wishlist1.toString();
        System.out.println(wishlist1.get(2).getWishlistID());
        System.out.println(wishlist1.get(2).getItemName());
        assertThat(str1).isEqualTo(str2);



    }

    @Test
    void addAndDeleteWishTest() throws MiniProjektException{
        UserRepositoryImplemented repo = new UserRepositoryImplemented();
        User user = repo.login("admin", "admin");

        //fetch current wishlist
        List<Wishlist> wishlist = repo.fetchWishlist(user);

        //add item
        repo.addItem("bukser",1, "https://www.google.com", user);

        //fetch new wishlist and assert it is larger in size than the initial list
        List<Wishlist> wishlist1 = repo.fetchWishlist(user);
        int i = wishlist1.size();
        assertThat(i).isGreaterThan(wishlist.size());

        //delete item, fetch updated wishlist, assert it is equal to initial
        //wishlist in size
        repo.deleteItem(wishlist1.get(i-1).getWishlistID());
        List<Wishlist> wishlist2 = repo.fetchWishlist(user);
        assertThat(wishlist.size()).isEqualTo(wishlist2.size());

    }


    @Test
    void fetchAllWishListsTest() throws MiniProjektException{
        UserRepositoryImplemented repo = new UserRepositoryImplemented();
        User user = repo.login("Me2", "meme");
        User user1 = repo.login("admin", "admin");
        List<Wishlist> wishlistUser = repo.fetchWishlist(user);
        List<Wishlist> wishlistUser1 = repo.fetchWishlist(user1);
        System.out.println(wishlistUser.toString() + " " + wishlistUser1.toString());
        List<Wishlist> wishlist1 = repo.fetchAllWishlists();
        String str1 = wishlist1.toString();
        wishlistUser1.add(wishlistUser.get(0));
        String str2 = wishlistUser1.toString();

        assertThat(str1).isEqualTo(str2);

    }


}
