package by.yauheni.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/info")
public class CompanyInformationController {
    @GetMapping(path = "/about")
    public ModelAndView about(ModelAndView modelAndView) {
        modelAndView.setViewName("aboutPage");
        return modelAndView;
    }
}
