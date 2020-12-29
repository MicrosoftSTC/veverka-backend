package cz.sharee.backend.dto.profile;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FollowDTO {
    private Long id;
    private UserDTO follower;
    private UserDTO followed;
    private LocalDate since;

}
