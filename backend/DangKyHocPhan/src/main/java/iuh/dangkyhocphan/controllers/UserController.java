package iuh.dangkyhocphan.controllers;

import iuh.dangkyhocphan.models.Account;
import iuh.dangkyhocphan.models.ResponseObject;
import iuh.dangkyhocphan.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user/login/accounts")
public class UserController {
    @Autowired
    private AccountService accountService;
    @GetMapping("")
    public ResponseEntity<ResponseObject> login(@RequestParam("username") Long username, @RequestParam("password") String password){
        Account acc = accountService.findAccountToLogin(username, password);
        Map<String, Object> result = new HashMap<>();
        if(acc == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Failed", "Can't find account with username = " + username + " and password = " + password, "")
            );
        }else {
            result = Map.of("username", username, "password", password, "role", acc.getVaiTro());
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Success", "Login successfully", result)
        );
    }
}
