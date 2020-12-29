package cz.sharee.backend.services.impl;

import cz.sharee.backend.domain.profile.User;
import cz.sharee.backend.dto.profile.UserDTO;
import cz.sharee.backend.exceptions.domain.BadRequestException;
import cz.sharee.backend.exceptions.domain.EmailAlreadyRegisteredException;
import cz.sharee.backend.exceptions.domain.UserNotFoundException;
import cz.sharee.backend.exceptions.domain.UsernameAlreadyExistsException;
import cz.sharee.backend.mappers.UserMapper;
import cz.sharee.backend.repositories.UserRepository;
import cz.sharee.backend.security.JwtTokenProvider;
import cz.sharee.backend.services.specs.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    @Override
    public UserDTO register(UserDTO userDTO) throws UsernameAlreadyExistsException, EmailAlreadyRegisteredException {
        String username = userDTO.getUsername();
        String email = userDTO.getEmail();

        boolean usernameRegistered = userRepository.findByUsername(username).isPresent();
        boolean emailRegistered = userRepository.findByEmail(email).isPresent();

        if(usernameRegistered){
            throw new UsernameAlreadyExistsException("some message");
        }else if(emailRegistered){
            throw new EmailAlreadyRegisteredException("some message");
        }else{
            User onBoardingUser = userMapper.userDTOToUser(userDTO);
            // encode the password
            onBoardingUser.setPassword(passwordEncoder.encode(onBoardingUser.getPassword()));
            User onBoardedUser = userRepository.save(onBoardingUser);
            // return registered user
            return userMapper.userToUserDTO(onBoardedUser);
        }
    }

    @Override
    public HttpHeaders login(UserDTO userDTO) throws BadRequestException, UserNotFoundException {
        if(userDTO.getUsername() != null && userDTO.getPassword() != null && !userDTO.getUsername().isBlank() && !userDTO.getPassword().isBlank()){
            String username = userDTO.getUsername();
            String password = userDTO.getPassword();
            // this method will throw exception if credentials are bad
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            // find user by username
            User loggedInUser = userRepository.findByUsername(username).get();
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, tokenProvider.generateJwtToken(loggedInUser));

            return headers;
        }else{
            throw new BadRequestException("Values can not be null or blank");
        }
    }

    public UserDTO findUserByUsername(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.userToUserDTO(user);
    }

//    private HttpHeaders getJwtHeader(UserDomain userDetails) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.AUTHORIZATION, tokenProvider.generateJwtToken(userDetails));
//        return headers;
//    }
}
