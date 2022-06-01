package br.com.franca.springbootdeveficiente.controller;

import br.com.franca.springbootdeveficiente.dto.in.NewUserForm;
import br.com.franca.springbootdeveficiente.validator.DuplicateEmailValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class UsersController {

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new DuplicateEmailValidator());
    }

    /**
     * @param form
     * @return
     */
    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String newUserJson(@Valid @RequestBody NewUserForm form) {
        log.info("form: {}, bindResult: {}", form);
        return "Hello World";
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String newUser(@Valid NewUserForm form) {
        log.info("form: {}, bindResult: {}", form);
        return "Hello World";
    }

}
