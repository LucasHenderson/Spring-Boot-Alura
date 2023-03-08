package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import med.voll.api.domain.medico.DadosAtualizacaoMedico;
import med.voll.api.domain.medico.DadosCadastroMedico;
import med.voll.api.domain.medico.DadosDetalhamentoMedico;
import med.voll.api.domain.medico.DadosListagemMedico;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired //indica para o spring q esse atributo vai ser instanciado como "DAO"
    private MedicoRepository repository;
    
    @PostMapping //Declarando que esse metodo vai responder a um post
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) { //RequestBody faz com que a var receba o valor que chegar do endpoint
        var medico = new Medico(dados);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping //não precisa do @Transactional, pois não estamos fazendo nenhuma alteração no bd, somente lendo
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao) {
        //pega todos os medicos do bd, converte para DadosListagemMedico(um DTO) e cria uma lista
        //a paginação começa pela page 0        http://localhost:8080/medicos?size=1&page=0
        //http://localhost:8080/medicos?sort=nome ordena o get por nome crescente
        //http://localhost:8080/medicos?sort=nome,desc ordena o get por nome decrescente

        //return repository.findAll(paginacao).map(DadosListagemMedico::new);
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        Medico medico = repository.getReferenceById(dados.id());
        medico.AtualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
        //não precisa fazer mais nada, só de mudar os dados a JPA já identifica automaticamente e faz a atualização
    }

    /* 
    @DeleteMapping("/{id}") //poderia fazer igual o metodo de atualizar, sem passar o id pela url, mas estou fazendo isso para testar essa outra forma
    @Transactional          //colocando {} o spring ja identifica aquilo como sendo uma variavel, se tirar as {} será uma lido como uma extensão da url
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
    */

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {
        Medico medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build(); // devolve o cdg 204 que é o padrão para exclusão
    }

    
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        Medico medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }
}
