package by.yauheni.entity.question;

import by.yauheni.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String question1;
    private String question2;
    private String question3;
    private String question4;
    private String question5;
    private String question6;
    private String question7;
    private String question8;
    private String question9;
    private String question10;
    private String status1;
    private String status2;
    private String status3;
    private String status4;
    private String status5;
    private String status6;
    private String status7;
    private String status8;
    private String status9;
    private String status10;

    public Result(String question1, String question2, String question3, String question4, String question5, String question6, String question7, String question8, String question9, String question10, String status1, String status2, String status3, String status4, String status5, String status6, String status7, String status8, String status9, String status10) {
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.question4 = question4;
        this.question5 = question5;
        this.question6 = question6;
        this.question7 = question7;
        this.question8 = question8;
        this.question9 = question9;
        this.question10 = question10;
        this.status1 = status1;
        this.status2 = status2;
        this.status3 = status3;
        this.status4 = status4;
        this.status5 = status5;
        this.status6 = status6;
        this.status7 = status7;
        this.status8 = status8;
        this.status9 = status9;
        this.status10 = status10;
    }
}
