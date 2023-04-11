package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;

public class ValidadorMedicoAtivo {
    
    private MedicoRepository repository;

    public void validar (DadosAgendamentoConsulta dados) {
        //escolha de medico opcional
        if (dados.idMedico() == null) {
            return;
        }

        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());
        if (!medicoEstaAtivo) {
            throw new ValidacaoException("A consulta deve ser agendado com um médico que esteja ativo!");
        }
    }

    
}
