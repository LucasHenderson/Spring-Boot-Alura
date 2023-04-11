package med.voll.api.domain.consulta.validacoes;

import java.time.DayOfWeek;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

public class ValidadorHorarioFuncionamentoClinica {
    

    public void validar (DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horariAntesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var horarioDepoisDoFechamentoDaClinica = dataConsulta.getHour() > 18;

        if (domingo || horariAntesDaAberturaDaClinica || horarioDepoisDoFechamentoDaClinica) {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica!");
        }
    }


}
