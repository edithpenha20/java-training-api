package testes.unit.mapper;

import br.com.training.controller.dto.UserForm;
import br.com.training.controller.dto.UserResponse;
import br.com.training.mapper.UserMapper;
import br.com.training.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import testes.unit.utils.UserUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = UserMapper.class)
public class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    void dtoForEntity() {
        UserForm form = UserUtils.createFakeUserForm();
        User user = mapper.toEntity(form);

        assertEquals(form.getName(), user.getName());
    }

    @Test
    void entityForResponse() {
        User user = UserUtils.createFakeUser();
        UserResponse response = mapper.toResponse(user);

        assertEquals(user.getName(), response.getName());
    }
}
