package br.com.franca.springbootdeveficiente.service;

import br.com.franca.springbootdeveficiente.domain.model.User;
import br.com.franca.springbootdeveficiente.dto.in.NewUserForm;
import br.com.franca.springbootdeveficiente.repository.IUserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final IUserRepository repository;

    @Transactional
    public void save(NewUserForm form){
        User user = new User();
        BeanUtils.copyProperties(form, user);
        User save = repository.save(user);
        log.info("save user: {}", save);
    }
}
