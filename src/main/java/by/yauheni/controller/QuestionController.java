package by.yauheni.controller;

import by.yauheni.dto.QuestionDTO;
import by.yauheni.entity.question.Question;
import by.yauheni.entity.question.Questionnaire;
import by.yauheni.repository.QuestionRepository;
import by.yauheni.repository.QuestionnaireRepository;
import by.yauheni.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(path = "/questions")
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @GetMapping
    public ModelAndView showQuestionPage(ModelAndView modelAndView, HttpSession session) {
        List<Question> questions = questionRepository.findAll();
        modelAndView.addObject("questionDTO", new QuestionDTO());
        modelAndView.addObject("questions", questions);
        session.setAttribute("idDoesntExist", "Введенный ID не существует");
        session.setAttribute("pickOneOption", "Выберите один правильный ответ");
        session.setAttribute("fieldEmpty", "Это поле не может быть пустым");
        modelAndView.setViewName("AllQuestionsPage");
        return modelAndView;
    }

    @GetMapping(path = "/viewQuestion")
    public ModelAndView viewQuestion(@RequestParam long questionId, ModelAndView modelAndView) {
        Question byId = questionRepository.findById(questionId);
        modelAndView.addObject("question", byId);
        modelAndView.setViewName("viewQuestionPage");
        return modelAndView;
    }

    @PostMapping(path = "/addQuestion")
    public ModelAndView addQuestion(@ModelAttribute("question") QuestionDTO questionDTO, ModelAndView modelAndView, HttpSession session) {
        ModelAndView modelAndView1 = optionCheck(questionDTO, modelAndView, session);
        if (modelAndView1 != null) return modelAndView1;
        session.setAttribute("answersNull", 0);
        session.setAttribute("optionsNull", 0);
        session.setAttribute("IdExistence", 0);
        if (questionDTO.getAnswer1().equals("") || questionDTO.getAnswer2().equals("") || questionDTO.getAnswer3().equals("") || questionDTO.getAnswer4().equals("") || questionDTO.getNewQuestion().equals("")) {
            session.setAttribute("answersNull", 1);
            modelAndView.setViewName("redirect:/questions");
            return modelAndView;
        }

        questionRepository.save(createQuestion(questionDTO.getNewQuestion(), questionDTO.getAnswer1(), questionDTO.getAnswer2(), questionDTO.getAnswer3(), questionDTO.getAnswer4(), questionDTO.getOption1(), questionDTO.getOption2(), questionDTO.getOption3(), questionDTO.getOption4()));
        modelAndView.setViewName("redirect:/questions");
        return modelAndView;
    }


    @PostMapping(path = "/deleteQuestion")
    public ModelAndView delete(@RequestParam(defaultValue = "-1") long delete, ModelAndView modelAndView, HttpSession session) {
        if (questionRepository.existsById(delete)) {
            questionRepository.deleteById(delete);
            session.setAttribute("IdExistence", 0);
        } else {
            session.setAttribute("IdExistence", 1);
        }
        session.setAttribute("answersNull", 0);
        session.setAttribute("optionsNull", 0);
        modelAndView.setViewName("redirect:/questions");
        return modelAndView;
    }

    @GetMapping(path = "/createQuest")
    public ModelAndView createQuest(ModelAndView modelAndView, HttpSession session) {
        List<Question> questions = questionRepository.findAll();
        List<Question> inMemoQuests = questionService.getAll();
        modelAndView.addObject("questions", questions);
        session.setAttribute("inMemoQuests", inMemoQuests);
        session.setAttribute("idDoesntExist", "Введенный ID не существует");
        session.setAttribute("moreThan10", "Опрос не может содержать более 10 вопросов");
        session.setAttribute("lessThan10", "Опрос не может содержать меньше 10 вопросов");
        session.setAttribute("questionnaireName", "Введите название опросника");
        modelAndView.setViewName("createQuestionnairePage");
        return modelAndView;
    }

    @PostMapping(path = "/clear")
    public ModelAndView clear(ModelAndView modelAndView) {
        questionService.clear();
        modelAndView.setViewName("redirect:/questions/createQuest");
        return modelAndView;
    }

    @PostMapping(path = "/addQuestionToQuestionnaire")
    public ModelAndView addQuestToQuest(ModelAndView modelAndView, HttpSession session, @RequestParam(defaultValue = "-1") long questId) {
        session.setAttribute("noId", 0);
        session.setAttribute("moreThan", 0);
        session.setAttribute("lessThan", 0);
        session.setAttribute("noName", 0);
        if (questionRepository.existsById(questId)) {
            if (questionService.getAll().size() > 9) {
                session.setAttribute("moreThan", 1);
                modelAndView.setViewName("redirect:/questions/createQuest");
                return modelAndView;
            } else {
                questionService.save(questionRepository.findById(questId));
                session.setAttribute("moreThan", 0);
            }
        } else {
            session.setAttribute("noId", 1);
        }
        modelAndView.setViewName("redirect:/questions/createQuest");
        return modelAndView;
    }

    @PostMapping(path = "/saveQuestionnaire")
    public ModelAndView saveQuestionnaire(ModelAndView modelAndView, HttpSession session, @RequestParam String description) {
        session.setAttribute("lessThan", 0);
        session.setAttribute("noName", 0);
        session.setAttribute("noId", 0);
        session.setAttribute("moreThan", 0);
        if (description.equals("")) {
            modelAndView.setViewName("redirect:/questions/createQuest");
            session.setAttribute("noName", 1);
            return modelAndView;
        }
        Questionnaire questionnaire = new Questionnaire();
        List<Question> all = questionService.getAll();
        if (all.size() < 10) {
            modelAndView.setViewName("redirect:/questions/createQuest");
            session.setAttribute("lessThan", 1);
            return modelAndView;
        }
        questionnaire.setQuestionId1(all.get(0).getId());
        questionnaire.setQuestionId2(all.get(1).getId());
        questionnaire.setQuestionId3(all.get(2).getId());
        questionnaire.setQuestionId4(all.get(3).getId());
        questionnaire.setQuestionId5(all.get(4).getId());
        questionnaire.setQuestionId6(all.get(5).getId());
        questionnaire.setQuestionId7(all.get(6).getId());
        questionnaire.setQuestionId8(all.get(7).getId());
        questionnaire.setQuestionId9(all.get(8).getId());
        questionnaire.setQuestionId10(all.get(9).getId());
        questionnaire.setDescription(description);
        questionnaireRepository.save(questionnaire);
        questionService.clear();
        modelAndView.setViewName("redirect:/questions/createQuest");
        return modelAndView;
    }


    private ModelAndView optionCheck(QuestionDTO questionDTO, ModelAndView modelAndView, HttpSession session) {
        int count = 0;
        if (questionDTO.getOption1().equals("Правильный")) {
            count++;
        }
        if (questionDTO.getOption2().equals("Правильный")) {
            count++;
        }
        if (questionDTO.getOption3().equals("Правильный")) {
            count++;
        }
        if (questionDTO.getOption4().equals("Правильный")) {
            count++;
        }
        if (count > 1 || count == 0) {
            session.setAttribute("optionsNull", 1);
            modelAndView.setViewName("redirect:/questions");
            return modelAndView;
        }
        return null;
    }

    private Question createQuestion(String question,
                                    String answer1,
                                    String answer2,
                                    String answer3,
                                    String answer4,
                                    String option1,
                                    String option2,
                                    String option3,
                                    String option4) {
        Question newQuest = new Question();
        newQuest.setQuestion(question);
        newQuest.setAnswer1(answer1);
        newQuest.setAnswer2(answer2);
        newQuest.setAnswer3(answer3);
        newQuest.setAnswer4(answer4);
        newQuest.setOption1(option1);
        newQuest.setOption2(option2);
        newQuest.setOption3(option3);
        newQuest.setOption4(option4);
        return newQuest;
    }
}
