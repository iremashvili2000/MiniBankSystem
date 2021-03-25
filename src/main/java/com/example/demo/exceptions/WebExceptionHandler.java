package com.example.demo.exceptions;



import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WebExceptionHandler extends ResponseEntityExceptionHandler {
        private Logger log= LoggerFactory.getLogger(WebExceptionHandler.class);

        @ExceptionHandler(NotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        @ResponseBody
        public Response handleUserNotFoundException(final NotFoundException ex){
        log.error("Contact not found thrown");
        return new Response("CONTACT_NOT_FOUND","THE contact was not found");

        }
        @ExceptionHandler(DontFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        @ResponseBody
        public ResponseEntity handlerDontFoundException(DontFoundException dontFoundException){
            return new ResponseEntity(dontFoundException.getMessage(),HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(BadDataException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ResponseBody
        public ResponseEntity handlerBadDataException(BadDataException badDataException){
            return new ResponseEntity(badDataException.getMessage(),HttpStatus.BAD_REQUEST);
        }


}
