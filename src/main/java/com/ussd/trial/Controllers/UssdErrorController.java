package com.ussd.trial.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UssdErrorController implements ErrorController {
    @Autowired
    TemplateEngine templateEngine;

    String viewName = "error";
    Map<String, Object> model = new HashMap<String, Object>();


    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletResponse response) {
        ModelAndView modelAndView;

        if(response.getStatus() == HttpStatus.NOT_FOUND.value()) {
            model.put("error", "error-404");
            model.put("error_message", "Sorry, requested resource was not found");
            modelAndView =  new ModelAndView(viewName , model);
        }
        else if(response.getStatus() == HttpStatus.FORBIDDEN.value()) {
            model.put("error", "error-403");
            model.put("error_message", "Sorry, a forbidden error has occurred");
            modelAndView =  new ModelAndView(viewName , model);
        }
        else if(response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            model.put("error", "error-500");
            model.put("error_message", "Sorry, an internal server error has occurred");
            modelAndView =  new ModelAndView(viewName , model);
        }
        else if(response.getStatus() == HttpStatus.BAD_REQUEST.value()) {
            model.put("error", "error-400");
            model.put("error_message", "Sorry, a bad request error has occurred");
            modelAndView =  new ModelAndView(viewName , model);
        }
        else {
            model.put("error", "error-500");
            model.put("error_message", "Sorry, an error has occurred");
            modelAndView = new ModelAndView(viewName , model);
        }

        return modelAndView;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
