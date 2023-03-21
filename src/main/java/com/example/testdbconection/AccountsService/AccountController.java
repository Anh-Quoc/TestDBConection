package com.example.testdbconection.AccountsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.util.ArrayList;

@RestController
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @GetMapping("/login")
    public String login(@RequestBody Account account){
        ArrayList<Account> listAccount = (ArrayList<Account>) accountRepository.findAll();
        for(Account accountDb : listAccount){
            if(accountDb.getUser_name().equals(account.getUser_name())){
                if(accountDb.getPassword().equals(sha256(account.getPassword()))){
                    return "Success, hello " + accountDb.getUser_name();
                }
                return "Wrong Password";
            }
        }
        System.out.println(account.getUser_name());
        return "Wrong user name";
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody String request) throws JSONException {
        JSONObject jsonObject = new JSONObject(request);
        Account newAccount = new Account();

        newAccount.setUser_name((String) jsonObject.get("user_name"));
        newAccount.setPassword(sha256((String) jsonObject.get("password")));

        accountRepository.save(newAccount);
        return "success";
    }

    public static String sha256(final String base) {
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
