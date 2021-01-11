package by.yauheni.controller;

import by.yauheni.dto.QuestionnaireDTO;
import by.yauheni.entity.question.Questionnaire;
import by.yauheni.entity.question.Result;
import by.yauheni.entity.user.Role;
import by.yauheni.entity.user.User;
import by.yauheni.repository.*;
import by.yauheni.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/questionnaire")
public class QuestionnaireController {
    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResultRepository resultRepository;


    @GetMapping
    public ModelAndView viewPage(ModelAndView modelAndView) {
        modelAndView.setViewName("allQuestionnairesPage");
        modelAndView.addObject("questionnaires", questionnaireRepository.findAll());
        return modelAndView;
    }

    @PostMapping(path = "/deleteQuestionnaire")
    public ModelAndView deleteQuest(ModelAndView modelAndView, @RequestParam(defaultValue = "-1") long delete, HttpSession session) {
        if (questionnaireRepository.existsById(delete)) {
            questionnaireRepository.deleteById(delete);
        }else{
            session.setAttribute("flag", 1);
        }
        modelAndView.setViewName("redirect:/questionnaire");
        return modelAndView;
    }

    @GetMapping(path = "/viewQuestionnaire")
    public ModelAndView viewQuestionnaire(@RequestParam long questId, ModelAndView modelAndView) {
        Questionnaire byId = questionnaireRepository.findById(questId);
        modelAndView.addObject("questionnaireId", byId.getId());
        modelAndView.addObject("question1", questionRepository.findById(byId.getQuestionId1()));
        modelAndView.addObject("question2", questionRepository.findById(byId.getQuestionId2()));
        modelAndView.addObject("question3", questionRepository.findById(byId.getQuestionId3()));
        modelAndView.addObject("question4", questionRepository.findById(byId.getQuestionId4()));
        modelAndView.addObject("question5", questionRepository.findById(byId.getQuestionId5()));
        modelAndView.addObject("question6", questionRepository.findById(byId.getQuestionId6()));
        modelAndView.addObject("question7", questionRepository.findById(byId.getQuestionId7()));
        modelAndView.addObject("question8", questionRepository.findById(byId.getQuestionId8()));
        modelAndView.addObject("question9", questionRepository.findById(byId.getQuestionId9()));
        modelAndView.addObject("question10", questionRepository.findById(byId.getQuestionId10()));
        modelAndView.addObject("questionnaire", new QuestionnaireDTO());
        modelAndView.setViewName("viewQuestionnairePage");
        return modelAndView;
    }

    @PostMapping(path = "/questionnaireSubmit")
    public ModelAndView submit(@ModelAttribute("questionnaire") QuestionnaireDTO questionnaireDTO, ModelAndView modelAndView) {
        Result result = new Result(questionnaireDTO.getQuestion1(),
                questionnaireDTO.getQuestion2(),
                questionnaireDTO.getQuestion3(),
                questionnaireDTO.getQuestion4(),
                questionnaireDTO.getQuestion5(),
                questionnaireDTO.getQuestion6(),
                questionnaireDTO.getQuestion7(),
                questionnaireDTO.getQuestion8(),
                questionnaireDTO.getQuestion9(),
                questionnaireDTO.getQuestion10(),
                questionnaireDTO.getAnswer1(),
                questionnaireDTO.getAnswer2(),
                questionnaireDTO.getAnswer3(),
                questionnaireDTO.getAnswer4(),
                questionnaireDTO.getAnswer5(),
                questionnaireDTO.getAnswer6(),
                questionnaireDTO.getAnswer7(),
                questionnaireDTO.getAnswer8(),
                questionnaireDTO.getAnswer9(), questionnaireDTO.getAnswer10());
        resultRepository.save(result);
        userRepository.save(new User(questionnaireDTO.getUsername(),
                questionnaireDTO.getName(), questionnaireDTO.getSurname(), questionnaireDTO.getMobile(), Role.JOB_CANDIDATE, result));
        modelAndView.setViewName("index");
        return modelAndView;
    }
    }
