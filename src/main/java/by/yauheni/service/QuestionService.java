package by.yauheni.service;

import by.yauheni.entity.errors.QuestionDoesNotExitsError;
import by.yauheni.entity.question.Question;
import by.yauheni.repository.InMemoryQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private InMemoryQuestionRepository inMemoryQuestionRepository;

    public boolean save(Question question){
        inMemoryQuestionRepository.save(question);
        return true;
    }

    public Question getById(long id){
        if (inMemoryQuestionRepository.contains(id)){
            return inMemoryQuestionRepository.getById(id);
        }
        throw  new QuestionDoesNotExitsError();
    }
    public List<Question> getAll(){
        return inMemoryQuestionRepository.getAll();
    }

    public boolean removeById(long id){
        if (inMemoryQuestionRepository.contains(id)){
            inMemoryQuestionRepository.removeById(id);
            return true;
        }
        throw new QuestionDoesNotExitsError();
    }
    public boolean clear(){
        return inMemoryQuestionRepository.clear();
    }
}
