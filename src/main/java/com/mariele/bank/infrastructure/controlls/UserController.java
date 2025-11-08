package com.mariele.bank.infrastructure.controlls;

import com.mariele.bank.application.usecases.CreateUserInteraction;
import com.mariele.bank.domain.dtos.UserDTO;
import com.mariele.bank.infrastructure.exceptions.InvaluableAttributeException;
import com.mariele.bank.infrastructure.exceptions.ResourceNotFoundException;
import com.mariele.bank.infrastructure.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final CreateUserInteraction createUserUseCase;

    @Autowired
    UserValidator validator;

    public UserController(CreateUserInteraction createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO request) {
        ResponseEntity<?> response;

        try {
            if (validator.canCreate(request)) {
                UserDTO userCreated = createUserUseCase.createUser(request);
                response = ResponseEntity.ok(userCreated);
            } else {
                response = ResponseEntity.status(400).build();
            }
        } catch (InvaluableAttributeException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @GetMapping
    public ResponseEntity<?> getUser(@RequestParam long account) {
        ResponseEntity<?> response;

        try {
            if (account <= 0) {
                throw new ResourceNotFoundException("Account number must be greater than zero");
            }
            UserDTO userCreated = createUserUseCase.getUser(account);
            response = ResponseEntity.ok(userCreated);
        } catch (ResourceNotFoundException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserDTO request) {
        ResponseEntity<?> response;

        try {
            if (validator.canUpdate(request)) {
                UserDTO userUpdated = createUserUseCase.updateUser(request);
                response = ResponseEntity.ok(userUpdated);
            } else {
                response = ResponseEntity.status(400).build();
            }
        } catch (InvaluableAttributeException | ResourceNotFoundException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestParam long account) {
        ResponseEntity<?> response;
        try {
            if (validator.canDelete(account)) {
                createUserUseCase.deleteUser(account);
                response = ResponseEntity.ok().build();
            }
            else {
                response = ResponseEntity.status(400).build();
            }
        } catch (ResourceNotFoundException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
}
