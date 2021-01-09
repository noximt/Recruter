package by.yauheni.controller;

import by.yauheni.dto.UserAuthDTO;
import by.yauheni.entity.user.Role;
import by.yauheni.entity.user.User;
import by.yauheni.repository.UserRepository;
import by.yauheni.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/auth")
    public ModelAndView userAuthorization(ModelAndView modelAndView, HttpSession httpSession) {
        modelAndView.setViewName("authorizationPage");
        modelAndView.addObject("user", new UserAuthDTO());
        httpSession.setAttribute("authorizationError", 0);
        return modelAndView;
    }

    @PostMapping(path = "/auth")
    public ModelAndView userAuthorization(@ModelAttribute("user") UserAuthDTO userAuthDTO, ModelAndView modelAndView, HttpSession httpSession) {
        User userByLogin = userRepository.findUserByUsername(userAuthDTO.getUsername());
        if (userByLogin != null) {
            if (userByLogin.getPassword().equals(userAuthDTO.getPassword())) {
                httpSession.setAttribute("user", userByLogin);
            } else {
                httpSession.setAttribute("authorizationError", 1);
                modelAndView.setViewName("authorizationPage");
                return modelAndView;
            }
        } else {
            httpSession.setAttribute("authorizationError", 2);
            modelAndView.setViewName("authorizationPage");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/user/profile");
        return modelAndView;
    }

    @GetMapping(path = "/profile")
    public ModelAndView viewProfile(ModelAndView modelAndView,HttpSession session) {
        session.setAttribute("whereToGo",0);
        modelAndView.setViewName("profilePage");
        return modelAndView;
    }

    @GetMapping(path = "/logout")
    public ModelAndView logout(ModelAndView modelAndView, HttpSession httpSession){
        httpSession.invalidate();
        modelAndView.setViewName("redirect:/user/auth");
        return modelAndView;
    }

    @GetMapping(path = "/allUsers")
    public ModelAndView viewAllUsers(ModelAndView modelAndView) {
        modelAndView.addObject("allUsers",userRepository.getUserByRole(Role.JOB_CANDIDATE));
        modelAndView.setViewName("allUsersPage");
        return modelAndView;
    }

    @GetMapping(path = "/viewUser")
    public ModelAndView viewUser(@RequestParam long userId, ModelAndView modelAndView) {
        User byId = userRepository.findUserById(userId);
        modelAndView.addObject("userById", byId);
        modelAndView.setViewName("viewUserPage");
        return modelAndView;
    }
}