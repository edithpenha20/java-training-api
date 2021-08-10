package br.com.training.controller;

import br.com.training.controller.dto.UserForm;
import br.com.training.controller.dto.UserResponse;
import br.com.training.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RestControllerAdvice
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserForm> createUser(@Valid @RequestBody UserForm userForm) {
		UserForm u = userService.createUser(userForm);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{cpf}")
				.buildAndExpand(u.getCpf())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping (value = "/{cpf}")
    public ResponseEntity<UserResponse> getUser (@PathVariable String cpf){
		UserResponse userResponse = userService.getUser(cpf);
		return ResponseEntity.ok(userResponse);
    }

    @PutMapping (value = "/{cpf}")
    public ResponseEntity<UserForm> updateUser (@PathVariable String cpf, @Valid @RequestBody UserForm userForm) {
		UserForm u = userService.updateUser(userForm, cpf);
		return u != null ?
				ResponseEntity.ok(u) :
				ResponseEntity.notFound().build();
	}

	@DeleteMapping (value = "/{cpf}")
	public ResponseEntity deleteUser (@PathVariable String cpf) {
		userService.deleteUser(cpf);
		return ResponseEntity.ok().build();
	}

}
