package testes.unit.controllers;

import br.com.training.controller.UserController;
import br.com.training.controller.dto.UserForm;
import br.com.training.controller.dto.UserResponse;
import br.com.training.mapper.UserMapper;
import br.com.training.model.User;
import br.com.training.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import testes.unit.utils.UserUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static testes.unit.utils.UserUtils.createFakeUserResponse;

@ExtendWith(SpringExtension.class)
public class ControllersTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private UserMapper mapper;

    @BeforeEach
    void setup() {
//        HttpServletRequest mockRequest = new MockHttpServletRequest();
//        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(mockRequest);
//        RequestContextHolder.setRequestAttributes(servletRequestAttributes);

        when(userService.getUser(anyString())).thenReturn(Optional.of(UserUtils.createFakeUser()));

        when(userService.createUser(any(UserForm.class))).thenReturn(UserUtils.createFakeUser());

//        doNothing().when(userService).updateUser(any(UserForm.class), anyString());

        when(userService.updateUser(any(UserForm.class), anyString())).thenReturn(UserUtils.createFakeUser());

        doNothing().when(userService).deleteUser(anyString());
    }

//    @Test
//    void getByCpfAndReturnUserWhenSuccessful() {
//        User expectedCpf = mapper.toEntity(createFakeUserResponse().getCpf());
//
//        UserResponse response = userController.getUser("000.000.000-00");
//
//        assertThat(response).isNotNull();
//
//        assertThat(response.getCpf()).isNotNull().isEqualTo(expectedCpf);
//    }

    @Test
    void createUserAndReturnUserWhenSuccessful() {

        User userForm = userController.createUser(UserUtils.createFakeUserForm()).getBody();

        assertThat(userForm).isNotNull().isEqualTo(UserUtils.createFakeUser());
    }

    @Test
    void updateUserAndReturnUserWhenSuccessful() {

        //method n.1
        assertThatCode(() -> userController.updateUser(UserUtils.createFakeUserForm().getCpf(),
                UserUtils.createFakeUserForm()));

        //method n.2
        ResponseEntity<Void> updateUser = userController.updateUser(UserUtils.createFakeUserForm().getCpf(),
                UserUtils.createFakeUserForm());
        assertThat(updateUser).isNotNull();
        assertThat(updateUser.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

    @Test
    void deleteUserAndReturnUserWhenSuccessful() {

        //method n.1
        assertThatCode(() -> userController.deleteUser("000.000.000-00"));

        //method n.2
        ResponseEntity<Void> deleteUser = userController.deleteUser("000.000.000-00");
        assertThat(deleteUser).isNotNull();
        assertThat(deleteUser.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

//    @AfterEach
//    public void teardown() {
//        RequestContextHolder.resetRequestAttributes();
//    }
}
