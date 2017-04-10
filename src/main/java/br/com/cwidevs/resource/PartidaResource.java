package br.com.cwidevs.resource;

import br.com.cwidevs.domain.PartidaJogador;
import br.com.cwidevs.domain.Partida;
import br.com.cwidevs.repository.PartidaRepository;
import br.com.cwidevs.resource.vm.PartidaJogadorVM;
import br.com.cwidevs.resource.vm.PartidaVM;
import br.com.cwidevs.service.PartidaService;
import java.util.List;
import static java.util.stream.Collectors.toList;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author murillo.goulart
 */
@RestController
@RequestMapping(path = "/api/partidas", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PartidaResource {

    @Autowired
    private PartidaRepository repository;

    @Autowired
    private PartidaService partidaService;

    @GetMapping
    public ResponseEntity<List<PartidaVM>> getAll() {
        List<PartidaVM> partidaVMs = repository.findAll()
                .stream()
                .map(PartidaVM::new)
                .collect(toList());
        
        return new ResponseEntity<>(partidaVMs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Partida> updateOrNew(@Valid @RequestBody(required = true) PartidaVM partidaVM) {

        // Se a soma dos gols dos jogadores não fecha com os gols do time
        if (partidaVM.getGolsPro() != partidaVM.getJogadoresGols()
                                                .stream()
                                                .mapToInt(PartidaJogadorVM::getGols)
                                                .sum()) {
            return ResponseEntity.badRequest().build();
        }
        Partida partida = partidaService.createPartida(partidaVM);

        return new ResponseEntity<>(partida, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        
        repository.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
