package br.com.franca.springbootdeveficiente.controller.contract;

import br.com.franca.springbootdeveficiente.dto.in.NewUserForm;
import br.com.franca.springbootdeveficiente.dto.out.UserDetailsDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

public interface IUsersController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    String newUserJson(@Valid @RequestBody NewUserForm form);

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String newUser(@Valid NewUserForm form);

    @GetMapping(path = "/{identificador}")
    Map<String, Object> getUserById(@PathVariable("identificador") Long id);

    @GetMapping
    UserDetailsDTO getUserDetailsDTOById(@RequestParam Long id);
}
