package br.com.cwidevs.resource;

import br.com.cwidevs.domain.Partida;
import br.com.cwidevs.repository.PartidaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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
}
