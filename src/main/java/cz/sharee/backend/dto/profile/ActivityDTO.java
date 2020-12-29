package cz.sharee.backend.dto.profile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityDTO {
    private Long id;
    private UserDTO user;
    private String activity;
}
