package by.yauheni.repository;

import by.yauheni.entity.question.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {
    Questionnaire findById(long id);
}
