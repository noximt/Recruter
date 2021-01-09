package by.yauheni;


import by.yauheni.interceptor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    IfLogoutInterceptor ifLogoutInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new IfLogoutInterceptor())
                .addPathPatterns("/user")
                .addPathPatterns("/questions")
                .addPathPatterns("/questions/viewQuestion")
                .addPathPatterns("/questions/createQuest")
                .addPathPatterns("/questionnaire")
                .addPathPatterns("/user/profile")
                .addPathPatterns("/user/allUsers")
                .addPathPatterns("/user/viewUser");

        registry.addInterceptor(new IfLoggedInInterceptor())
                .addPathPatterns("/user/auth");

        registry.addInterceptor(new QuestionOrQuestionnaireInterceptor())
                .addPathPatterns("/questions/createQuest");

        registry.addInterceptor(new ClearFlagsInterceptor())
                .addPathPatterns("/questions/viewQuestion")
                .addPathPatterns("/questions/clear")
                .addPathPatterns("/questionnaire")
                .addPathPatterns("/user/profile");
    }
}
