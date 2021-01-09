package by.yauheni.entity.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    private long questionId1;
    private long questionId2;
    private long questionId3;
    private long questionId4;
    private long questionId5;
    private long questionId6;
    private long questionId7;
    private long questionId8;
    private long questionId9;
    private long questionId10;
}
