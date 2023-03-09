package med.voll.api.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    UserDetails findByLogin(String login);

    //https://cursos.alura.com.br/course/spring-boot-aplique-boas-praticas-proteja-api-rest/task/117186
}
