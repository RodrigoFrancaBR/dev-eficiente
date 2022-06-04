package br.com.franca.springbootdeveficiente.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDetailsDTO {
    private String name;
    private String email;
    private String description;
    private LocalDate birth;
    private String textoDeTeste;
}
