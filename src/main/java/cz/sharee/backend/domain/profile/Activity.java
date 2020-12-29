package cz.sharee.backend.domain.profile;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Activity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private String activity;
}
