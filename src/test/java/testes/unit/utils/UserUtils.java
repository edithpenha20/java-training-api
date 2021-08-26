package testes.unit.utils;

import br.com.training.controller.dto.UserForm;
import br.com.training.controller.dto.UserResponse;
import br.com.training.mapper.UserMapper;
import br.com.training.model.User;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;

public class UserUtils {

    private static final String NAME = "Endy";
    private static final String EMAIL = "endy@gmail.com";
    private static final String CPF_NUMBER = "000.000.000-00";
    private static final long USER_ID = 1L;
    public static final LocalDate BIRTH_DATE = LocalDate.of(1985, 07, 13);


    public static User createFakeUser(){
        User user = new User();
        user.setId(USER_ID);
        user.setName(NAME);
        user.setEmail(EMAIL);
        user.setCpf(CPF_NUMBER);
        user.setBirthDate(BIRTH_DATE);
        return user;
    }

    public static UserForm createFakeUserForm(){
        UserForm userForm = new UserForm();
        userForm.setName(NAME);
        userForm.setEmail(EMAIL);
        userForm.setCpf(CPF_NUMBER);
        userForm.setBirthDate(BIRTH_DATE);
        return userForm;
    }


    public static User convertUserFakeUserDTO(UserForm userDTO){
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setCpf(userDTO.getCpf());
        user.setBirthDate(userDTO.getBirthDate());
        return user;
    }

    public static UserResponse createFakeUserResponse(){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(USER_ID);
        userResponse.setName(NAME);
        userResponse.setEmail(EMAIL);
        userResponse.setCpf(CPF_NUMBER);
        userResponse.setBirthDate(BIRTH_DATE);
        return userResponse;
    }

    public static UserResponse convertUserFakeUserResponse(User user){
        UserResponse response = new UserResponse();
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setCpf(user.getCpf());
        response.setBirthDate(user.getBirthDate());
        return response;
    }


    public static String asJsonString(UserForm userDTO) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModules(new JavaTimeModule());

            return objectMapper.writeValueAsString(userDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String asJsonString(UserResponse response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModules(new JavaTimeModule());

            return objectMapper.writeValueAsString(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
