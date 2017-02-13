package br.com.cwidevs.repository;

import br.com.cwidevs.domain.Jogador;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author murillo.goulart
 */
public interface JogadorRepository extends MongoRepository<Jogador, Long> {
    
}
