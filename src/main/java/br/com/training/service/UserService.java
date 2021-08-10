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

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserForm createUser(UserForm userForm) {
        User userToSave = mapper.toEntity(userForm);
        userRepository.save(userToSave);
        return mapper.toRequest(userToSave);
    }

    @Transactional
    public UserResponse getUser(String cpf) {
        return userRepository.findByCpf(cpf)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado"));
    }

    @Transactional
    public UserForm updateUser(UserForm userForm, String cpf) {

        Optional<User> u = userRepository.findByCpf(cpf);
        if (u.isPresent()) {
            User userDB = u.get();
            userDB.setName(userForm.getName());
            userDB.setEmail(userForm.getEmail());
            userDB.setCpf(userForm.getCpf());
            userDB.setBirthDate(userForm.getBirthDate());

            userRepository.save(userDB);
            return mapper.toRequest(userDB);
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteUser(String cpf) {
        userRepository.deleteByCpf(cpf);
    }
}
