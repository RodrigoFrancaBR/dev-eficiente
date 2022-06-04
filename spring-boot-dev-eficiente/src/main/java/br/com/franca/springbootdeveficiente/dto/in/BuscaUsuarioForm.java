package br.com.franca.springbootdeveficiente.dto.in;

import br.com.franca.springbootdeveficiente.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BuscaUsuarioForm {
    private String name;
    private String email;

    public Specification<User> toSpec() {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(name)) {
                Path<String> fildName = root.<String>get("name");
                // Predicate predicateName = criteriaBuilder.equal(fildName, name);
                Predicate predicateName = criteriaBuilder.like(fildName, "%"+name+"%");
                predicates.add(predicateName);
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
