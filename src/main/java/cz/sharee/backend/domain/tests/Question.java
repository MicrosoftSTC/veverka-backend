package cz.sharee.backend.domain.tests;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Test test;

    @Column(nullable = false)
    private String actualQuestion;

    @OneToMany(mappedBy = "question")
    private Set<Option> options;
}
