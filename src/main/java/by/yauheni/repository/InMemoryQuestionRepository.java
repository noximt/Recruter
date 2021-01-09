package by.yauheni.repository;

import by.yauheni.entity.question.Question;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryQuestionRepository {
    private static List<Question> questions = new ArrayList<>();

    public boolean save(Question question) {
        questions.add(question);
        return true;
    }

    public Question getById(long id) {
        for (Question question : questions) {
            if (question.getId() == id) {
                return question;
            }
        }
        return null;
    }

    public List<Question> getAll(){
        return questions;
    }

    public boolean removeById(long id) {
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getId() == id){
                questions.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean clear(){
        questions.clear();
        return true;
    }

    public boolean contains(long id) {
        for (Question question : questions) {
            if (question.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
