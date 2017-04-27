package br.com.cwidevs.resource;

import br.com.cwidevs.domain.Partida;
import br.com.cwidevs.repository.PartidaRepository;
import br.com.cwidevs.resource.util.PaginationUtil;
import br.com.cwidevs.resource.vm.PartidaJogadorVM;
import br.com.cwidevs.resource.vm.PartidaVM;
import br.com.cwidevs.service.PartidaService;
import java.net.URISyntaxException;
import java.util.List;
import static java.util.stream.Collectors.toList;
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
@RequestMapping(path = "/partidas", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PartidaResource {

    @Autowired
    private PartidaRepository partidaRepository;

    @Autowired
    private PartidaService partidaService;

    @GetMapping
    public ResponseEntity<List<PartidaVM>> getAll(@PageableDefault(sort = { "dataRealizacao" }, direction = Direction.DESC) Pageable pageable) throws URISyntaxException {
        Page<Partida> page = partidaRepository.findAll(pageable);  
        
        List<PartidaVM> partidaVMs = page.getContent()
                .stream()
                .map(PartidaVM::new)
                .collect(toList());
        
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/partidas");
        return new ResponseEntity<>(partidaVMs, headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PartidaVM> updateOrNew(@Valid @RequestBody(required = true) PartidaVM partidaVM) {

        // Se a soma dos gols dos jogadores não fecha com os gols do time
        if (partidaVM.getGolsPro() != partidaVM.getJogadoresGols()
                                                .stream()
                                                .mapToInt(PartidaJogadorVM::getGols)
                                                .sum()) {
            return ResponseEntity.badRequest().build();
        }
        Partida partida = partidaService.createPartida(partidaVM);

        return new ResponseEntity<>(new PartidaVM(partida), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        
        partidaRepository.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
