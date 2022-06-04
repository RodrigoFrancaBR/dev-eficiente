package br.com.franca.springbootdeveficiente.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class NewUserForm {

    @NotBlank(message = "Não permite valor em branco nem nulo")
    private String name;

    @Getter
    @NotBlank(message = "Não permite valor em branco nem nulo")
    @Email
    private String email;

    @NotNull(message = "Não permite valor nulo, mas permite vazio")
    @Length(min = 0, max = 100, message = "mín de 0 e max 100")
    private String description;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @NotNull(message = "Não permite valor nulo, mas permite vazio")
    @Past
    private LocalDate birth;

    @NotEmpty(message = "não permite valor menor que 1 nem nulo")
    private String textoDeTeste;
}
