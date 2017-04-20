package br.com.cwidevs.repository;

import br.com.cwidevs.domain.Partida;
import br.com.cwidevs.dto.Goleador;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author murillo.goulart
 */
public interface PartidaRepository extends JpaRepository<Partida, Long> {

    @Query("select new br.com.cwidevs.dto.Goleador(j.id, sum(pj.gols)) from PartidaJogador pj join pj.id.jogador j group by j")
    public List<Goleador> getGoleadores();

    Optional<Partida> findOneByJogadoresGolsIdJogador(long idJogador);

}
