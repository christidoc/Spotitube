package service;

import datasource.UserDAO;
import domain.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

/**
 * Created by Christiaan on 3-12-2017.
 */
public class LoginServiceTest {
    @Mock
    private TokenService tokenService;
    @Mock
    private UserDAO userDAO;
    User user1;
    User user2;
    List<User> users;

    @InjectMocks
    private LoginService loginService;

    @Before
    public void setUP() throws Exception {
        MockitoAnnotations.initMocks(this);
        user1 = new User("username", "password");
        user2 = new User("username2", "password2");
        users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
    }

    @Test
    public void testGetUserByLoginSuccess() {
        when(tokenService.getRandomToken()).thenReturn("123");
        when(userDAO.getAllUsers()).thenReturn(users);

        User user = loginService.getUserByLogin("username", "password");

        assertEquals(user.getUserName(), "username");
        assertEquals(user.getToken(), "123");
    }

    @Test
    public void testGetUserByLoginWrongPassword() {
        when(tokenService.getRandomToken()).thenReturn("123");
        when(userDAO.getAllUsers()).thenReturn(users);

        User user = loginService.getUserByLogin("username", "password3");

        assertNull(user);
    }
}
