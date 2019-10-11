package com.manageuser.exception;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	private Logger logger = Logger.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler({Exception.class, java.lang.Exception.class})
    public ModelAndView handleIOException(Exception ex) {
        ModelAndView model = new ModelAndView("Error");
        model.addObject("exception", ex.getMessage());
        logger.error("An Exception Occured " + ex.getMessage());
        return model;
    }
	
}
