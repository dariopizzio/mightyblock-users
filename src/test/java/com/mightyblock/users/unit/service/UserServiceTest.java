package com.mightyblock.users.unit.service;

import com.mightyblock.users.config.UserRepositoryTestConfiguration;
import com.mightyblock.users.dto.UserDto;
import com.mightyblock.users.model.User;
import com.mightyblock.users.model.exceptions.ApiException;
import com.mightyblock.users.repository.UserRepository;
import com.mightyblock.users.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {UserRepository.class, UserRepositoryTestConfiguration.class})
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    private UserRepository repository;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public void initMocks(){
        User userCreated = new User("userTest", "passwordTest");
        Mockito.when(repository.findByUsername("userTest")).thenReturn(Collections.singletonList(userCreated));
        User newUser = new User("userTest2", "passwordTest2");
        User newUserWithId = new User("userTest2", "passwordTest2");
        newUserWithId.setId("id2");
        Mockito.when(repository.save(newUser)).thenReturn(newUserWithId);
    }

    @Test
    void createUser_shouldThrowAnApiException() {
        UserDto userToCreate = new UserDto("userTest", "passwordTest");
        ApiException exception = Assert.assertThrows(ApiException.class, () -> userService.createUser(userToCreate));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    }

    @Test
    void createUser_shouldReturnAnUserId() throws ApiException {
        UserDto userToCreate = new UserDto("userTest2", "passwordTest2");
        String id = userService.createUser(userToCreate);
        Assertions.assertEquals("id2", id);
    }

    @Test
    void login_withAnInvalidUserShouldThrowFORBIDDEN() {
        //TODO implement!
    }

    @Test
    void login_withValidUserShouldReturnToken() {
        //TODO implement!
    }
}
