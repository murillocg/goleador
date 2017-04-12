package br.com.cwidevs.repository;

import br.com.cwidevs.domain.Jogador;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author murillo.goulart
 */
public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    
    Optional<Jogador> findOneByNome(String nome);
    
}
