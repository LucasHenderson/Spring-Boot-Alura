package med.voll.api.medico;

import med.voll.api.endereco.DadosEndereco;

//https://cursos.alura.com.br/course/spring-boot-3-desenvolva-api-rest-java/task/116049
public record DadosCadastroMedico
(String nome, String email, String crm, 
Especialidade especialidade, DadosEndereco endereco) {

}
