package com.MediLabo.MSFrontend.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

// To personalize the error message on the error page.
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(PatientBadRequestException.class)
    public ModelAndView handlePatientBadRequestException(PatientBadRequestException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }
}
