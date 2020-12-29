package cz.sharee.backend.dto.tests;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class QuestionDTO {
    private Long id;
    private TestDTO test;
    private String actualQuestion;
    private Set<OptionDTO> options;
}
