package br.com.cwidevs.resource;

import br.com.cwidevs.dto.Goleador;
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
@RequestMapping(path = "/api/goleadores", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GoleadorResource {

    @Autowired
    private PartidaRepository repository;
    
    @GetMapping
    private List<Goleador> getAll() {
        return repository.getGoleadores();
    }
}
