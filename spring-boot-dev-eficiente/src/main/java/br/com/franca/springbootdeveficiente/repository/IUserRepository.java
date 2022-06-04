package br.com.franca.springbootdeveficiente.repository;

import br.com.franca.springbootdeveficiente.domain.model.User;
import br.com.franca.springbootdeveficiente.dto.in.BuscaUsuarioForm;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Repository
public interface IUserRepository extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findById(Long id);

    User save(User user);

    Collection<User> findAll();

    @Query("select u from User u where u.email = :email")
    Optional<User> buscaPorEmail(@Param("email") String email);

    Optional<User>findByEmail(String email);

    Optional<User>findByEmailOrName(String email, String nome);

//    @Query("select u from User u where u.name = :#{busca.name}")
//    Optional<User> buscaGeral(@Param("busca")BuscaUsuarioForm busca);

}

