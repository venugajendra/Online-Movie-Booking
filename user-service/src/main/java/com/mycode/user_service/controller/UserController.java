package com.mycode.user_service.controller;

import com.mycode.user_service.model.User;
import com.mycode.user_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/users")
@Tag(name = "User Management", description = "API related to user management")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @Operation(summary = "User Login", description = "Allows users to log in with their email and password")
    @GetMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String pass) {
        log.info("Inside the loginUser method of UserController.");
        return userService.getUserByEmail(email)
                .filter(usr -> passwordEncoder.matches(pass, usr.getPassword()))
//                .filter(user -> pass.matches(user.getPassword()))
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"));
    }

    @Operation(summary = "User Registration")
    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        log.info("Inside the createUser method of UserController.");
        User created = userService.createUser(user);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    @Operation(summary = "Get User by ID")
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        log.info("Inside the getUser method of UserController.");
        return userService.getUserById(id);
    }

}




//    @Autowired
//    private SecurityConfig securityConfig;


/*    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.createUser(user));
    }*/

/*
    @GetMapping("/login")
    public ResponseEntity<User> loginUser1(@RequestParam String email, @RequestParam String password) {
        return userService.getUserByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
//                .filter(user -> password.matches(user.getPassword()))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return Optional.ofNullable(userService.getUserById(id))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/me")
    public ResponseEntity<String> getUserDetails(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok("User email: " + jwt.getClaimAsString("sub"));
    }
*/

/*    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }*/
