package br.com.training.mapper;

import br.com.training.controller.dto.UserForm;
import br.com.training.controller.dto.UserResponse;
import br.com.training.model.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper {

    public User toEntity(UserForm dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setCpf(dto.getCpf());
        user.setBirthDate(dto.getBirthDate());
        return user;
    }

    public UserForm toRequest(User user) {
        UserForm userForm = new UserForm();
        userForm.setName(user.getName());
        userForm.setEmail(user.getEmail());
        userForm.setCpf(user.getCpf());
        userForm.setBirthDate(user.getBirthDate());
        return userForm;
    }

    public UserResponse toResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setCpf(user.getCpf());
        userResponse.setBirthDate(user.getBirthDate());
        return userResponse;
    }

}
