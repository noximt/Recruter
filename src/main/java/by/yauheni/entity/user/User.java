package by.yauheni.entity.user;

import by.yauheni.entity.question.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String mobile;
    private String email;
    private String position;
    private String gitHub;
    private String insta;
    private String twitter;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @OneToOne(cascade = CascadeType.ALL)
    private Result result;


    public User(String username, String name, String surname, String mobile, Role role, Result result) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.mobile = mobile;
        this.role = role;
        this.result = result;
    }
}
