package com.example.demo.rest;

import com.example.demo.domain.HttpResponse;
import com.example.demo.exception.ExceptionHandling;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
//@RequestMapping(path = { "/rest/user"})
public class EntryPointController extends ExceptionHandling {

    private static final String PING_SUCCESSFULLY ="success";

    @GetMapping("/")
    public ResponseEntity<HttpResponse> ping() {
        return response(OK, PING_SUCCESSFULLY);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }

}
