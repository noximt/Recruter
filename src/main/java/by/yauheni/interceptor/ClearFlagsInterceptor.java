package by.yauheni.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClearFlagsInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.getSession().setAttribute("answersNull",0);
        request.getSession().setAttribute("optionsNull",0);
        request.getSession().setAttribute("IdExistence",0);
        request.getSession().setAttribute("moreThan", 0);
        request.getSession().setAttribute("noId", 0);
        request.getSession().setAttribute("lessThan", 0);
        request.getSession().setAttribute("noName", 0);
        return true;
    }
}
