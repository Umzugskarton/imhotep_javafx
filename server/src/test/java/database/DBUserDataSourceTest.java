package database;

import data.user.User;
import database.userdata.DBUserDataSource;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import user.UserIdentifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DBUserDataSourceTest {


    User user;

    @Mock
    DBHelper dbHelper;


    DBUserDataSource dbUDS;

    @Before
    public void init() {
        dbUDS = new DBUserDataSource();
    }

    @Test //Wegen DB umständlich
    public void createUserTest() {


    }

    @Test // deleteUser wird noch nicht von uns genutzt
    public void deleteUserTest() {

    }

    @Test //Wegen DB umständlich
    public void changeUserTest() {

    }

    @Test
    public void getUserTest() {
        User dbUser = dbUDS.getUser(UserIdentifier.USERNAME, "test");
        assertEquals("hallo@test.de", dbUser.getEmail());
    }

    @Test(expected = NullPointerException.class)
    public void failGetUserTest() {
        User failUser = dbUDS.getUser(UserIdentifier.USERNAME, "derNichtExistierendeUser");
        assertEquals(null, failUser.getId());
    }

    @Test
    public void validateLoginTest() {
        String username = "test";
        String password = "testtest";
        assertEquals(true, dbUDS.validateLogin(username, password));
        assertEquals(false, dbUDS.validateLogin("test", "test123"));

    }
}
