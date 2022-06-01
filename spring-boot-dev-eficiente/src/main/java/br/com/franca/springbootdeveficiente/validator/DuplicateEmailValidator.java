package br.com.franca.springbootdeveficiente.validator;

import br.com.franca.springbootdeveficiente.dto.in.NewUserForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
public class DuplicateEmailValidator implements Validator {
    private final String EMAIL = "rfrancacosta@gmail.com";

    /**
     * @param clazz NewUserForm ou filhas
     * @return true se for NewUserForm ou filhas
     */
    @Override
    public boolean supports(Class<?> clazz) {
        log.info("clazz: {} ", clazz);
        return NewUserForm.class.isAssignableFrom(clazz);
    }

    /**
     * @param target formulário q é dessa classe NewUserForm
     * @param errors objeto que pode conter erros ou não
     */
    @Override
    public void validate(Object target, Errors errors) {
        log.info("target: {}, errors: {} ", target, errors);
        if (errors.hasErrors()) {
            return;
        }
        NewUserForm form = (NewUserForm) target;
        if (EMAIL.equals(form.getEmail())) {
            errors.reject("email", null, "deplicate email");
        }
    }
}
