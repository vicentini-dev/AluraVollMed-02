package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.MedicoDTO;
import med.voll.api.dto.MedicoDetalhesDTO;
import med.voll.api.dto.MedicoListagemDTO;
import med.voll.api.dto.MedicoUpdateDTO;
import med.voll.api.model.Medico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<MedicoDetalhesDTO> create(@RequestBody @Valid MedicoDTO medicoDTO, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(medicoDTO);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new MedicoDetalhesDTO(medico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoListagemDTO> getMedicoById(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new MedicoListagemDTO(medico));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MedicoListagemDTO>> getAll() {
        var medicos = repository.findAll().stream()
                .map(MedicoListagemDTO::new)
                .toList();
        return ResponseEntity.ok(medicos);
    }

    @GetMapping
    public ResponseEntity<Page<MedicoListagemDTO>> getPageable(@PageableDefault(size = 5, sort = {"nome"}) Pageable pageable) {
        var page = repository.findAllByAtivoTrue(pageable).map(MedicoListagemDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<MedicoDetalhesDTO> update(@RequestBody @Valid MedicoUpdateDTO data) {
        var medico = repository.getReferenceById(data.id());
        medico.update(data);
        return ResponseEntity.ok(new MedicoDetalhesDTO(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Medico> medico = repository.findById(id);
        medico.ifPresent(Medico::disable);
        return ResponseEntity.noContent().build();
    }
}
