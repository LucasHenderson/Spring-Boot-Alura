package med.voll.api.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeErros {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    //posso colocar esse dto aqui mesmo ja q nao vou usar em nenhum outro lugar
    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao (FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

    //https://cursos.alura.com.br/course/spring-boot-aplique-boas-praticas-proteja-api-rest/task/117181
    //https://cursos.alura.com.br/course/spring-boot-aplique-boas-praticas-proteja-api-rest/task/125341
}
