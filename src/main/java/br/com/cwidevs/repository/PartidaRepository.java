package br.com.cwidevs.repository;

import br.com.cwidevs.domain.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author murillo.goulart
 */
public interface PartidaRepository extends JpaRepository<Partida, Long> {
    
}
