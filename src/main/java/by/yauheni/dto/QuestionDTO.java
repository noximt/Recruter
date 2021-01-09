package by.yauheni.dto;

import lombok.Data;

@Data
public class QuestionDTO {
    String newQuestion;
    String answer1;
    String answer2;
    String answer3;
    String answer4;
    String option1;
    String option2;
    String option3;
    String option4;

    public QuestionDTO() {
        this.option1 = "Неправильный";
        this.option2 = "Неправильный";
        this.option3 = "Неправильный";
        this.option4 = "Неправильный";
    }
}
