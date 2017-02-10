package br.com.cwidevs.repository;

import br.com.cwidevs.domain.Partida;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author murillo.goulart
 */
public interface PartidaRepository extends MongoRepository<Partida, Long> {
    
}
