package med.voll.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosListagemMedico;
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

    @GetMapping //não precisa do @Transactional, pois não estamos fazendo nenhuma alteração no bd, somente lendo
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao) {
        //pega todos os medicos do bd, converte para DadosListagemMedico(um DTO) e cria uma lista
        //a paginação começa pela page 0        http://localhost:8080/medicos?size=1&page=0
        //http://localhost:8080/medicos?sort=nome ordena o get por nome crescente
        //http://localhost:8080/medicos?sort=nome,desc ordena o get por nome decrescente 
        return repository.findAll(paginacao).map(DadosListagemMedico::new);
    }
    
}
