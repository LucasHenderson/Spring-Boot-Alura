package med.voll.api.medico;

public record DadosListagemMedico(String nome, 
String email, String crm, Especialidade especialidade) {

    //construtor que recebe um Medico e converte para DadosListagemMedico
    public DadosListagemMedico(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    } 
}
