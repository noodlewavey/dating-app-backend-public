package com.wang.app.rest.Controller;

import com.wang.app.rest.Models.Role;
import com.wang.app.rest.Models.UserEntity;
import com.wang.app.rest.Repo.RoleRepository;
import com.wang.app.rest.Repo.UserRepository;
import com.wang.app.rest.Security.JWTGenerator;
import com.wang.app.rest.dto.AuthResponseDto;
import com.wang.app.rest.dto.LoginDto;
import com.wang.app.rest.dto.RegisterDto;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }


//initializing things we're using
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        if(userRepository.existsByUsername(registerDto.getUsername())){
            return new ResponseEntity<>("Username is taken", HttpStatus.BAD_REQUEST);
        }
        UserEntity user = new UserEntity();
        //if user doesnt exist, create a new user
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        //we hash the password in case theres a security breach

        Role roles = roleRepository.findByName("USER").get();
        //this fetches role entity with name "USER"..the user at hand is assigned the user role...
        user.setRoles(Collections.singletonList(roles));
        //once "USR" role is fetched, assigned to newly resgistered user
        //collections.singletonList(roles)....creaes a list with single element
        //also immutable
        //but this restricts us to assign a single role to the user...
        //not sure

        userRepository.save(user);

        return new ResponseEntity<>("User registered success",HttpStatus.OK);

        //we're creating a usr entity, we need to set roles
        //we use register data transfer object....
        //encapsulates data reequired for user registration...allows us to not expose raw passwod in entites

    }

    //this is an endpoint to register a user....
    //think of how to combine this with google .....
    //commit this finished product to git, then modify with google


    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){

        //shoudl i use getter and setter for dto?
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        //this is some type of storage
        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);

    }
}
