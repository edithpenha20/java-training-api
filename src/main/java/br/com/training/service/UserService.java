package br.com.training.service;

import br.com.training.model.User;
import br.com.training.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUser(String cpf) {
        return userRepository.findByCpf(cpf);
    }

    public User updateUser(User user, String cpf) {
        Optional<User> u = userRepository.findByCpf(cpf);
        if (u.isPresent()) {
            User userDB = u.get();
            userDB.setName(user.getName());
            userDB.setEmail(user.getEmail());
            userDB.setCpf(user.getCpf());
            userDB.setBirthDate(user.getBirthDate());

            return  userRepository.save(userDB);
        } else {
            return null;
        }
    }

    public Optional<User> deleteUser(String cpf) {
        userRepository.findByCpf(cpf);
        return userRepository.deleteByCpf(cpf);
    }
}
