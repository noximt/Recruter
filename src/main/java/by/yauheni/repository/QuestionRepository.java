package by.yauheni.repository;

import by.yauheni.entity.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findById(long id);
}
