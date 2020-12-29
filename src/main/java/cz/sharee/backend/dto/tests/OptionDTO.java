package cz.sharee.backend.dto.tests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionDTO {
    private Long id;
    private Boolean isCorrect;
    private String value;
    private QuestionDTO question;
}
