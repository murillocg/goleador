package br.com.cwidevs.resource;

import br.com.cwidevs.domain.Jogador;
import br.com.cwidevs.repository.JogadorRepository;
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
@RequestMapping(path = "/jogadores", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class JogadorResource {
    
    @Autowired
    private JogadorRepository jogadorRepository;
    
    @GetMapping
    public ResponseEntity<List<Jogador>> getAll() {
        List<Jogador> result = jogadorRepository.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Jogador> createOrUpdate(@Valid @RequestBody Jogador jogador) {
        Jogador result = jogadorRepository.save(jogador);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        jogadorRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Jogador> get(@PathVariable Long id) {
        Jogador result = jogadorRepository.findOne(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }    
}
