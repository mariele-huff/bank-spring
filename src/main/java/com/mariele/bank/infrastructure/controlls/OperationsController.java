package com.mariele.bank.infrastructure.controlls;

import com.mariele.bank.application.usecases.CreateUserInteraction;
import com.mariele.bank.domain.dtos.OperationalDTO;
import com.mariele.bank.domain.dtos.TransferDTO;
import com.mariele.bank.infrastructure.exceptions.ResourceNotFoundException;
import com.mariele.bank.infrastructure.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/operations")
public class OperationsController {

    private final CreateUserInteraction createUserUseCase;

    @Autowired
    UserValidator validator;

    public OperationsController( CreateUserInteraction createUserUseCase ) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transferFunds(@RequestBody TransferDTO transferDTO ) {
        ResponseEntity<?> response;
        try
        {
            if ( validator.userExists( transferDTO.fromAccount() ) && validator.userExists( transferDTO.toAccount() ) )
            {
                createUserUseCase.transferFunds(transferDTO);
                response = ResponseEntity.ok().body( "Transfer completed successfully." );
            }
            else
            {
                response = ResponseEntity.status( 400 ).body( "One or both accounts do not exist." );
            }
        }

        catch ( ResourceNotFoundException e )
        {
            response = ResponseEntity.status( 400 ).body( e.getMessage() );
        }

        catch ( Exception e )
        {
            response = ResponseEntity.status( 500 ).body( e.getMessage() );
        }

        return response;
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> depositFunds( @RequestBody OperationalDTO operationalDTO ) {
        ResponseEntity<?> response;
        try {
            createUserUseCase.depositFunds(operationalDTO);
            response = ResponseEntity.ok().body( "Deposit completed successfully." );
        }
        catch ( Exception e ) {
            response = ResponseEntity.status( 500 ).body( e.getMessage() );
        }
        return response;
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdrawFunds( @RequestBody OperationalDTO operationalDTO ) {
        ResponseEntity<?> response;
        try {
            createUserUseCase.withdrawFunds(operationalDTO);
            response = ResponseEntity.ok().body( "Withdrawal completed successfully." );
        }
        catch ( Exception e ) {
            response = ResponseEntity.status( 500 ).body( e.getMessage() );
        }
        return response;
    }

    @GetMapping("/balance")
    public ResponseEntity<?> balanceAccount(@RequestParam long account) {
        ResponseEntity<?> response;
        try {
            if ( validator.userExists( account ) )
            {
                BigDecimal balance = createUserUseCase.getBalance(account );
                response = ResponseEntity.ok().body( balance );
            }
            else {
                response = ResponseEntity.status( 400 ).body( "Account does not exist." );
            }
        }
        catch ( ResourceNotFoundException e ) {
            response = ResponseEntity.status(400).body(e.getMessage());
        }
        catch ( Exception e ) {
            response = ResponseEntity.status( 500 ).body( e.getMessage() );
        }
        return response;
    }
}
