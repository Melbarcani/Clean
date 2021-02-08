import com.clean.library.user.domain.User;
import com.clean.library.user.infrastructure.controller.UserController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserControllerTest {
    private final static String LIBRARIAN_ROLE = "librarian";
    private final static String MEMBER_ROLE = "member";

    @Test
    @DisplayName("getUser() is librarian")
    public void testGetUserIsLibrarian(){
        //Given userLogin is librarian
        UserController userController = new UserController("librarian");

        // When I get the User
        User user = userController.getUser();

        //Then the user Exist
        Assertions.assertThat(userController.isUserExist()).isTrue();
        // And his role should be librarian
        Assertions.assertThat(user.getUserRole()).isEqualTo(LIBRARIAN_ROLE);
        Assertions.assertThat(user.getUserRole()).isNotEqualTo(MEMBER_ROLE);
    }

    @Test
    @DisplayName("getUser() is member")
    public void testGetUserIsMember(){
        //Given userLogin is librarian
        UserController userController = new UserController("member");

        // When I get the User
        User user = userController.getUser();

        //Then the user Exist
        Assertions.assertThat(userController.isUserExist()).isTrue();
        // And his role should be member
        Assertions.assertThat(user.getUserRole()).isEqualTo(MEMBER_ROLE);
        Assertions.assertThat(user.getUserRole()).isNotEqualTo(LIBRARIAN_ROLE);
    }

    @Test
    @DisplayName("getUser() is a guest")
    public void testGetUserIsGuest(){
        //Given userLogin is librarian
        UserController userController = new UserController("Any");

        // When I get the User
        User user = userController.getUser();

        //Then his role should not be librarian neither member
        Assertions.assertThat(user.getUserRole()).isNotEqualTo(MEMBER_ROLE);
        Assertions.assertThat(user.getUserRole()).isNotEqualTo(LIBRARIAN_ROLE);
    }

}
