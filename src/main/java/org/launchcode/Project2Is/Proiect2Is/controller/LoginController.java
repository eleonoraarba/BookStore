package org.launchcode.Project2Is.Proiect2Is.controller;


import org.launchcode.Project2Is.Proiect2Is.model.User;
import org.launchcode.Project2Is.Proiect2Is.service.authentification.AuthentificationService;
import org.launchcode.Project2Is.Proiect2Is.service.security.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("users")
public class LoginController {

    @Autowired
    private AuthentificationService authentificationService;

    @Autowired
    private RoleService roleUserService;

    @PostMapping("/insert")
    public ResponseEntity<String> insertUser(@RequestBody User user ){
        User newuser = User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .role((roleUserService.findByRole("USER")))
                .build();

        String message=authentificationService.createAccount(newuser);

       if(message.equals("Account Created")){

           return ResponseEntity.ok().body(message);
       }
       else{
           return ResponseEntity.badRequest().body(message);
       }

    }

    @PostMapping("/signIn")
    public ResponseEntity<Object> loginUser(@RequestBody User user ){

        User newuser=authentificationService.logIn(user.getUsername(), user.getPassword());

        if(newuser==null){
            return ResponseEntity.badRequest().body("Invalid Credentials");
        }
        else{

            return ResponseEntity.ok(newuser.getRoles().get(0).getRole());

        }
    }
}
