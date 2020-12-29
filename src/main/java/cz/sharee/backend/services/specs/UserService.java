package cz.sharee.backend.services.specs;

import cz.sharee.backend.dto.profile.UserDTO;
import cz.sharee.backend.exceptions.domain.BadRequestException;
import cz.sharee.backend.exceptions.domain.EmailAlreadyRegisteredException;
import cz.sharee.backend.exceptions.domain.UserNotFoundException;
import cz.sharee.backend.exceptions.domain.UsernameAlreadyExistsException;
import org.springframework.http.HttpHeaders;

public interface UserService {
//    User register(
//            @NotNull String username,
//            @NotNull String email,
//            @NotNull String password,
//            @NotNull String firstName,
//            @NotNull String lastName,
//            @NotNull String country,
//            @NotNull String city,
//            @Nullable String street,
//            @Nullable String doorNumber,
//            @Nullable String schoolName
//    );

    UserDTO register(UserDTO userDTO) throws UsernameAlreadyExistsException, EmailAlreadyRegisteredException;

    HttpHeaders login(UserDTO userDTO) throws BadRequestException, UserNotFoundException;

    UserDTO findUserByUsername(String username) throws UserNotFoundException;
}
