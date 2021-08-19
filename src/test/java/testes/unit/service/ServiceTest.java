package testes.unit.service;

import br.com.training.controller.dto.UserForm;
import br.com.training.controller.dto.UserResponse;
import br.com.training.mapper.UserMapper;
import br.com.training.model.User;
import br.com.training.repository.UserRepository;
import br.com.training.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import testes.unit.utils.UserUtils;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper mapper = mock(UserMapper.class);

    @BeforeEach
    void setup() {

        when(userRepository.findByCpf(anyString())).thenReturn(Optional.of(UserUtils.createFakeUser()));

        //when(userRepository.save(any(User.class))).thenReturn(Optional.of(UserUtils.createFakeUser()));

        when(userRepository.save(any(User.class))).thenReturn(UserUtils.createFakeUser());

        doNothing().when(userRepository).delete(any(User.class));
    }

    @Test
    void getByCpfAndReturnUserWhenSuccessful() {
//        String expectedCpf = UserUtils.createFakeUserResponse().getCpf();
//        assertThat(expectedCpf).isNotNull().isEqualTo(response.get().getCpf());

        UserResponse expectedCpf = UserUtils.createFakeUserResponse();
        Optional<User> response = userService.getUser("000.000.000-00");

        assertThat(expectedCpf.getName()).isNotNull().isEqualTo(response.get().getName());
        assertThat(expectedCpf.getEmail()).isNotNull().isEqualTo(response.get().getEmail());
        assertThat(expectedCpf.getCpf()).isNotNull().isEqualTo(response.get().getCpf());
        assertThat(expectedCpf.getBirthDate()).isNotNull().isEqualTo(response.get().getBirthDate());
    }

    @Test
    void createUserAndReturnUserWhenSuccessful() {

//        UserForm form = UserUtils.createFakeUserForm();
//        User userDTO = mapper.toEntity(form);
//
//        User user = userService.createUser(userDTO);

//        User user = UserUtils.createFakeUser();

    }

    @Test
    void updateUserAndReturnUserWhenSuccessful() {

        assertThatCode(() -> userService.updateUser(UserUtils.createFakeUserForm(),
                UserUtils.createFakeUserForm().getCpf()));

    }

    @Test
    void deleteUserAndReturnUserWhenSuccessful() {

        assertThatCode(() -> userService.deleteUser("000.000.000-00"));

    }
}
