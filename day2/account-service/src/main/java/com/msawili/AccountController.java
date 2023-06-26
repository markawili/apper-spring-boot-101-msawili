package com.msawili;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;
    private final IdGeneratorService verificationService;

    public AccountController(AccountService accountService, IdGeneratorService verificationService) {
        this.accountService = accountService;
        this.verificationService = verificationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateAccountResponse createAccount(@RequestBody CreateAccountRequest request) {
        try {
            Account newAccount = accountService.create(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword());

            CreateAccountResponse response = new CreateAccountResponse();
            int VERIFICATION_CODE_LENGTH = 6;
            response.setVerificationCode(verificationService.generateRandomCharacters(VERIFICATION_CODE_LENGTH));

            return response;
        } catch (UsernameAlreadyRegisteredException err) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, err.getMessage(), err);
        }
    }

    @PutMapping("{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateAccountResponse updateAccount(@PathVariable String accountId, @RequestBody CreateAccountRequest request) {
        try {
            UpdateAccountResponse response = new UpdateAccountResponse();
            response.setLastUpdate(accountService.update(accountId, request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword()));

            return response;
        } catch (AccountNotFoundException err) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, err.getMessage(), err);
        }
    }

    @GetMapping("{accountId}")
    public GetAccountResponse getAccount(@PathVariable String accountId) {
        try {
            Account account = accountService.get(accountId);

            return getGetAccountResponse(account);
        } catch (AccountNotFoundException err) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, err.getMessage(), err);
        }
    }

    @GetMapping
    public List<GetAccountResponse> getAllAccounts() {
        List<GetAccountResponse> responseList = new ArrayList<>();

        for (Account account : accountService.getAll()) {
            GetAccountResponse response = getGetAccountResponse(account);

            responseList.add(response);
        }
        return responseList;
    }

    @DeleteMapping("{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable String accountId) {
        try {
            accountService.delete(accountId);
        } catch (AccountNotFoundException err) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, err.getMessage(), err);
        }
    }

    private GetAccountResponse getGetAccountResponse(Account account) {
        GetAccountResponse response = new GetAccountResponse();
        response.setId(account.getId());
        response.setBalance(account.getBalance());
        response.setFirstName(account.getFirstName());
        response.setLastName(account.getLastName());
        response.setUsername(account.getUsername());
        response.setCreationDate(account.getCreationDate());
        return response;
    }
}
