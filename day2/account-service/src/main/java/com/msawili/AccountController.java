package com.msawili;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

        Account newAccount = accountService.create(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword());

        CreateAccountResponse response = new CreateAccountResponse();
        int VERIFICATION_CODE_LENGTH = 6;
        response.setVerificationCode(verificationService.generateRandomCharacters(VERIFICATION_CODE_LENGTH));

        System.out.println(request);

        return response;
    }

    @GetMapping("{accountId}")
    public GetAccountResponse getAccount(@PathVariable String accountId) {
        Account account = accountService.get(accountId);

        return getGetAccountResponse(account);
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
        accountService.delete(accountId);
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
