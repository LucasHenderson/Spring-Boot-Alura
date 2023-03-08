package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// essa interface funciona como um DAO, fazendo a ligação para o bd
// dentro do <> deve-se passar qual classe jpa vai ser trabalhada e qual o tipo da chave primaria
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    //seguindo esse padrão de escrita (findAllBy[algumaCoisa]True) o proprio spring gera o codigo para a gente
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);
    
}
