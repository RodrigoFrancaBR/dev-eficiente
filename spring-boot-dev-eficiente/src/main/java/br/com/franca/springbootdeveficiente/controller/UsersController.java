package br.com.franca.springbootdeveficiente.controller;

import br.com.franca.springbootdeveficiente.controller.contract.IUsersController;
import br.com.franca.springbootdeveficiente.domain.model.User;
import br.com.franca.springbootdeveficiente.dto.in.NewUserForm;
import br.com.franca.springbootdeveficiente.dto.out.UserDetailsDTO;
import br.com.franca.springbootdeveficiente.service.UserService;
import br.com.franca.springbootdeveficiente.validator.DuplicateEmailValidator;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@RequestMapping(path = "/v1/users")
@RestController
public class UsersController implements IUsersController {
    private final UserService service;

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new DuplicateEmailValidator());
    }

    public String newUserJson(NewUserForm form) {
        log.info("form: {}, bindResult: {}", form);
        service.save(form);
        return "Hello World";
    }

    public String newUser(NewUserForm form) {
        log.info("form: {}, bindResult: {}", form);
        return "Hello World";
    }

    public Map<String, Object> getUserById(Long id) {
        log.info("id: {}", id);
        // simulando um retorno de um usuário do banco de dados
        User user = User.builder()
            .name("Rodrigo")
            .email("rfrancacosta@gmail.com")
            .description("Uma descrição")
            .birth(LocalDate.of(1985, 9, 4))
            .textoDeTeste("Texto de teste")
            .build();
        Map<String, Object> myMap = new HashMap<String, Object>() {{
            put("name", user.getName());
            put("email", user.getEmail());
            put("description", user.getDescription());
            put("birth", user.getBirth());
            put("textoDeTeste", user.getTextoDeTeste());
        }};
        return myMap;
    }

    public UserDetailsDTO getUserDetailsDTOById(Long id) {
        log.info("id: {}", id);
        // simulando um retorno de um usuário do banco de dados
        User user = User.builder()
            .name("Rodrigo")
            .email("rfrancacosta@gmail.com")
            .description("Uma descrição")
            .birth(LocalDate.of(1985, 9, 4))
            .textoDeTeste("Texto de teste")
            .build();
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        BeanUtils.copyProperties(user, userDetailsDTO);
        // simulando uma exception
        return userDetailsDTO;
    }

}
