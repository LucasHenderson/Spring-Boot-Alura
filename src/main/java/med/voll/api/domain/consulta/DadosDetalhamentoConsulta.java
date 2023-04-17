package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

//https://cursos.alura.com.br/course/spring-boot-3-documente-teste-prepare-api-deploy/task/122580
//https://cursos.alura.com.br/course/spring-boot-3-documente-teste-prepare-api-deploy/task/122581
public record DadosDetalhamentoConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {

    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
    
}
