package presentation;

import domain.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import presentation.dto.LoginRequest;
import presentation.dto.LoginResponse;
import service.ActiveUser;
import service.LoginService;

public class LoginAPITest {
    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginAPI api;

    @Before
    public void setUP() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPathReturnResponse() {
        when(loginService.loginUser(any(String.class), any(String.class))).thenReturn(new ActiveUser("user", "token"));

        LoginResponse loginResponse = api.login(new LoginRequest("",""));

        assertEquals(loginResponse.getToken(), "token");
    }
}
