package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired //indica para o spring q esse atributo vai ser instanciado como "DAO"
    private MedicoRepository repository;
    
    @PostMapping //Declarando que esse metodo vai responder a um post
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) { //RequestBody faz com que a var receba o valor que chegar do endpoint
        repository.save(new Medico(dados));
    }
    
}
