package br.com.training.controller;

import javax.validation.Valid;

import br.com.training.service.UserService;

import br.com.training.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
//@RestControllerAdvice
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
		User u = userService.createUser(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{cpf}")
				.buildAndExpand(u.getCpf())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping (value = "/{cpf}")
	@ResponseStatus(HttpStatus.OK)
    public Optional<User> getUser (@PathVariable String cpf){
		return userService.getUser(cpf);
    }

    @PutMapping (value = "/{cpf}")
	@ResponseStatus(HttpStatus.OK)
    public String updateUser (@PathVariable String cpf, @RequestBody User user) {
		User u = userService.updateUser(user, cpf);
		return "Atualização realizada com sucesso" + u;
	}

	@DeleteMapping (value = "/{cpf}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Optional<User> deleteUser (@PathVariable String cpf) {
		return userService.deleteUser(cpf);
	}

}
