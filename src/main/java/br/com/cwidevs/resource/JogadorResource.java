package br.com.cwidevs.resource;

import br.com.cwidevs.domain.Jogador;
import br.com.cwidevs.repository.JogadorRepository;
import br.com.cwidevs.repository.PartidaRepository;
import br.com.cwidevs.resource.util.HeaderUtil;
import java.net.URISyntaxException;
import java.util.List;
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
@RequestMapping(path = "/api/jogadores", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class JogadorResource {

    @Autowired
    private JogadorRepository jogadorRepository;
    @Autowired
    private PartidaRepository partidaRepository;

    @GetMapping
    public ResponseEntity<List<Jogador>> getAll() {
        List<Jogador> result = jogadorRepository.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Jogador> createOrUpdate(@Valid @RequestBody Jogador jogador) {
        if (jogadorRepository.findOneByNome(jogador.getNome()).isPresent()) {
             return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert("jogador", "nameexists", "Name already in use"))
                .body(null);
        }
        Jogador result = jogadorRepository.save(jogador);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (partidaRepository.findOneByJogadoresGolsIdJogador(id).isPresent()) {
          return ResponseEntity.badRequest()
             .headers(HeaderUtil.createFailureAlert("jogador", "scored", "Jogador already scored"))
             .body(null);
        }
        jogadorRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogador> get(@PathVariable Long id) {
        Jogador result = jogadorRepository.findOne(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
