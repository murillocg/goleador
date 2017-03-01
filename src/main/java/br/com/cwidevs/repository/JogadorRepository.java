package br.com.cwidevs.repository;

import br.com.cwidevs.domain.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author murillo.goulart
 */
public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    
}
