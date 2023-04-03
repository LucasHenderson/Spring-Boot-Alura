package med.voll.api.domain.medico;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// essa interface funciona como um DAO, fazendo a ligação para o bd
// dentro do <> deve-se passar qual classe jpa vai ser trabalhada e qual o tipo da chave primaria
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    //seguindo esse padrão de escrita (findAllBy[algumaCoisa]True) o proprio spring gera o codigo para a gente
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    //faz com que voce mesmo monte como vai ser faita a consulta no banco, seguindo a sintaxe do JPQL
    //usando tres aspas no inicio e no fim vc pode escrever em formato de Text Block, sem precisar ficar usando "+" para quebrar linha 
    @Query("""
        select m from Medico m
        where
        m.ativo = 1
        and
        m.especialidade = :especialidade
        and
        m.id not in(
            select c.medico.id from Consulta c
            where
            c.data = :data 
        )
        order by rand()
        limit 1
    """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);
    
}
