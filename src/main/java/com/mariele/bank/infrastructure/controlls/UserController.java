package com.mariele.bank.infrastructure.controlls;

import com.mariele.bank.application.usecases.CreateUserInteraction;
import com.mariele.bank.domain.dtos.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final CreateUserInteraction createUserUseCase;

    public UserController(CreateUserInteraction createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO request) {
        UserDTO userCreated = createUserUseCase.createUser(request);
        return ResponseEntity.ok( userCreated );
    }
}
