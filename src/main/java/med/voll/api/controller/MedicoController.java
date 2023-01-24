package med.voll.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.voll.api.medico.DadosCadastroMedico;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    
    @PostMapping //Declarando que esse metodo vai responder a um post
    public void cadastrar(@RequestBody DadosCadastroMedico dados) { //RequestBody faz com que a var receba o valor que chegar do endpoint
        System.out.println(dados);
    }

}
