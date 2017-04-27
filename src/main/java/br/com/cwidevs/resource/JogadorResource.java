package br.com.cwidevs.resource;

import br.com.cwidevs.domain.Jogador;
import br.com.cwidevs.repository.JogadorRepository;
import br.com.cwidevs.repository.PartidaRepository;
import br.com.cwidevs.resource.util.HeaderUtil;
import br.com.cwidevs.resource.util.PaginationUtil;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
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
    public ResponseEntity<List<Jogador>> getAll(@PageableDefault(sort = { "nome" }, direction = Direction.ASC) Pageable pageable) throws URISyntaxException {
        Page<Jogador> page = jogadorRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/jogadores");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Jogador> createOrUpdate(@Valid @RequestBody Jogador jogador) {
        Optional<Jogador> sameNome = jogadorRepository.findOneByNome(jogador.getNome());
        if (sameNome.isPresent() && !jogador.equals(sameNome.get())) {
             return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert("jogador", "nameexists", "Name already in use"))
                .body(null);
        }
        Jogador result = jogadorRepository.save(jogador);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (partidaRepository.findFirstByJogadoresGolsIdJogadorId(id).isPresent()) {
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
