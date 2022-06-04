package br.com.franca.springbootdeveficiente.controller;

import br.com.franca.springbootdeveficiente.domain.model.User;
import br.com.franca.springbootdeveficiente.dto.in.BuscaUsuarioForm;
import br.com.franca.springbootdeveficiente.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PaginationDynamicQueryController {

    private final IUserRepository repository;

    @GetMapping(path = "/paginacao-simples")
    public String paginacaoSimples() {
        // page 1 indice 0 com 10 elementos
        Page<User> all = repository.findAll(PageRequest.of(0, 10));
        return all.getContent().toString();
    }

    @GetMapping(path = "/paginacao-com-parametros")
    public String paginacaoComParametros(Pageable pageable) {
        Page<User> all = repository.findAll(pageable);
        return all.getContent().toString();
    }

    @GetMapping(path = "/paginacao-com-parametros-default")
    public String paginacaoComParametrosEOrdenacao(@PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<User> all = repository.findAll(pageable);
        return all.getContent().toString();
    }

    @GetMapping(path = "/query-dinamica")
    public String paginacaoComParametrosEOrdenacao(BuscaUsuarioForm buscaUsuarioForm) {
        return repository.findAll().toString();
    }
}
