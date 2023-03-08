package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

// https://cursos.alura.com.br/course/spring-boot-3-desenvolva-api-rest-java/task/116049
// notblank verifica se não está nulo e vazio (funciona somente com string)
// valid faz com que o spring verifique as validações que estão dentro desse atributo
public record DadosCadastroMedico
    (
    @NotBlank
    String nome,
    
    @NotBlank
    @Email
    String email,

    @NotBlank
    String telefone,
    
    @NotBlank
    @Pattern(regexp = "\\d{4,6}")
    String crm, 

    @NotNull
    Especialidade especialidade,
    
    @NotNull
    @Valid
    DadosEndereco endereco
    ) {

}
