package br.com.training.service;

import br.com.training.controller.dto.UserForm;
import br.com.training.controller.dto.UserResponse;
import br.com.training.exception.ObjectNotFoundException;
import br.com.training.mapper.UserMapper;
import br.com.training.model.User;
import br.com.training.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class UserService {


    private UserMapper mapper;

    private UserRepository userRepository;

    @Autowired
    public UserService(UserMapper mapper, UserRepository userRepository) {
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(UserForm userForm) {
        return userRepository.save(mapper.toEntity(userForm));
    }

    @Transactional
    public User getUser(String cpf) {
        Optional<User> user = userRepository.findByCpf(cpf);
        return user.orElseThrow(() -> new ObjectNotFoundException(
                "Usuário não encontrado! CPF: " + cpf + ", Tipo: " + User.class.getName()));
    }

    @Transactional
    public User updateUser(UserForm userForm, String cpf) {

        Optional<User> u = userRepository.findByCpf(cpf);
        if (u.isPresent()) {
            User userDB = u.get();
            userDB.setName(userForm.getName());
            userDB.setEmail(userForm.getEmail());
            userDB.setCpf(userForm.getCpf());
            userDB.setBirthDate(userForm.getBirthDate());

            mapper.toEntity(userDB);
            return userRepository.save(userDB);
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteUser(String cpf) {
        userRepository.deleteByCpf(cpf);
    }
}
