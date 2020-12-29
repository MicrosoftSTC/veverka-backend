package cz.sharee.backend.domain.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.sharee.backend.domain.profile.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    @JsonIgnore
    private Long id;

    @NonNull
    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<User> users;

    @Singular
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Authority> authorities;
}
