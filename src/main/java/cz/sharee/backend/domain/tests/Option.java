package cz.sharee.backend.domain.tests;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Option {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Boolean isCorrect;

    @Column(nullable = false)
    private String value;

    @ManyToOne
    private Question question;
}
