package br.com.training.controller;

import br.com.training.controller.dto.UserForm;
import br.com.training.controller.dto.UserResponse;
import br.com.training.exception.ObjectNotFoundException;
import br.com.training.mapper.UserMapper;
import br.com.training.model.User;
import br.com.training.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;


@RestController
@RestControllerAdvice
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserMapper mapper;

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody @Valid UserForm userForm) {

		return new ResponseEntity<>(userService.createUser(userForm), HttpStatus.CREATED);
	}

	@GetMapping (value = "/{cpf}")
    public UserResponse getUser (@PathVariable String cpf){
		return userService.getUser(cpf)
				.map(mapper::toResponse)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//		UserResponse userResponse = userService.getUser(cpf);
//		return ResponseEntity.ok(userResponse);
    }

    @PutMapping (value = "/{cpf}")
    public ResponseEntity<Void> updateUser (@PathVariable String cpf, @Valid @RequestBody UserForm userForm) {
		userService.updateUser(userForm, cpf);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping (value = "/{cpf}")
	public ResponseEntity<Void> deleteUser (@PathVariable String cpf) {
		userService.deleteUser(cpf);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
