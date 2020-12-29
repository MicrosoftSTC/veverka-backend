package cz.sharee.backend.controllers;

import cz.sharee.backend.dto.profile.UserDTO;
import cz.sharee.backend.dto.validation.UserRegistrationInfo;
import cz.sharee.backend.exceptions.domain.BadRequestException;
import cz.sharee.backend.exceptions.domain.EmailAlreadyRegisteredException;
import cz.sharee.backend.exceptions.domain.UserNotFoundException;
import cz.sharee.backend.exceptions.domain.UsernameAlreadyExistsException;
import cz.sharee.backend.services.specs.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("user/register")
    public ResponseEntity<UserDTO> registerUser(@Validated(UserRegistrationInfo.class) @RequestBody UserDTO userDTO) throws EmailAlreadyRegisteredException, UsernameAlreadyExistsException {
        UserDTO registeredUser = userService.register(userDTO);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/user/login")
    public ResponseEntity<UserDTO> login(@Validated(UserRegistrationInfo.class) @RequestBody UserDTO userDTO) throws BadRequestException, UserNotFoundException {
        // perform login and generate JwtToken
        HttpHeaders jwtHeader = userService.login(userDTO);
        // load user info
        UserDTO loggedInUserInfo = userService.findUserByUsername(userDTO.getUsername());
        return new ResponseEntity<>(loggedInUserInfo, jwtHeader, HttpStatus.OK);
    }

    @GetMapping("/user/profile/{username}")
    public ResponseEntity<UserDTO> loadUserDetailsByUsername(@PathVariable String username){
        UserDTO returned = UserDTO.builder().username("Janko").password("Fjordal").build();
        return new ResponseEntity<>(returned, HttpStatus.OK);
    }
}
