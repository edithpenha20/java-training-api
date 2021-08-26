package testes.unit.service;

import br.com.training.mapper.UserMapper;
import br.com.training.model.User;
import br.com.training.repository.UserRepository;
import br.com.training.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import testes.unit.utils.UserUtils;

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
    private UserMapper mapper;

    @BeforeEach
    void setup() {

        when(userRepository.findByCpf(anyString())).thenReturn(Optional.of(UserUtils.createFakeUser()));

        User entity = mapper.toEntity(UserUtils.createFakeUserForm());
        when(userRepository.save(entity)).thenReturn(UserUtils.createFakeUser());

        doNothing().when(userRepository).delete(any(User.class));
    }

    @Test
    void getByCpfAndReturnUserWhenSuccessful() {
        String expectedCpf = UserUtils.createFakeUserResponse().getCpf();

        User response = userService.getUser("000.000.000-00");

        assertThat(response.getCpf()).isNotNull().isEqualTo(expectedCpf);
    }

    @Test
    void createUserAndReturnUserWhenSuccessful() {

        User form = UserUtils.createFakeUser();

        User user = userService.createUser(UserUtils.createFakeUser());

        assertThat(user.getName()).isNotNull().isEqualTo(form.getName());

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
