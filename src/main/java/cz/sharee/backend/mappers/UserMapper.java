package cz.sharee.backend.mappers;

import cz.sharee.backend.domain.profile.User;
import cz.sharee.backend.dto.profile.UserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User userDTOToUser(UserDTO userDTO);

    UserDTO userToUserDTO(User user);
}
