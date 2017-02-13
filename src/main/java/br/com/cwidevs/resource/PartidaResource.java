package br.com.cwidevs.resource;

import br.com.cwidevs.domain.Partida;
import br.com.cwidevs.repository.PartidaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author murillo.goulart
 */
@RestController
@RequestMapping(path = "/partidas", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PartidaResource {

    @Autowired
    private PartidaRepository repository;

    @GetMapping
    public List<Partida> getAll() {
        return repository.findAll();
    }

    @PostMapping
    @PutMapping
    public ResponseEntity<Partida> updateOrNew(
            @RequestBody(required = true) Partida partida) {

        if (partida.getId() != null && !repository.exists(partida.getId())) {
            partida.setId(null);
        }

        return new ResponseEntity<>(partida, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        repository.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
