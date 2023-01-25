package med.voll.api.medico;

import org.springframework.data.jpa.repository.JpaRepository;

// essa interface funciona como um DAO, fazendo a ligação para o bd
// dentro do <> deve-se passar qual classe jpa vai ser trabalhada e qual o tipo da chave primaria
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    
}
