package br.com.cwidevs.service;

import br.com.cwidevs.domain.Jogador;
import br.com.cwidevs.domain.Partida;
import br.com.cwidevs.domain.PartidaJogador;
import br.com.cwidevs.domain.PartidaJogadorPK;
import br.com.cwidevs.repository.JogadorRepository;
import br.com.cwidevs.repository.PartidaRepository;
import br.com.cwidevs.resource.vm.PartidaVM;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author murillo.goulart
 */
@Service
public class PartidaService {

    @Autowired
    private PartidaRepository partidaRepository;
    
    @Autowired
    private JogadorRepository jogadorRepository;
    
    public Partida createPartida(PartidaVM partidaVM) {
        Partida partida = new Partida();
        partida.setDataRealizacao(partidaVM.getDataRealizacao());
        partida.setAdversario(partidaVM.getAdversario());
        partida.setGolsPro(partidaVM.getGolsPro());
        partida.setGolsContra(partidaVM.getGolsContra());
        
        Set<PartidaJogador> partidaJogadores = new HashSet<>();
        partidaVM.getJogadoresGols().forEach(jogadorGol -> {
            PartidaJogador partidaJogador = new PartidaJogador();
            partidaJogador.setGols(jogadorGol.getGols());
            PartidaJogadorPK pk = new PartidaJogadorPK();
            Jogador jogador = jogadorRepository.findOne(jogadorGol.getJogadorId());
            pk.setJogador(jogador);
            pk.setPartida(partida);
            partidaJogador.setId(pk);
            partidaJogadores.add(partidaJogador);
        });
        partida.setJogadoresGols(partidaJogadores);
        return partidaRepository.save(partida);
    }
    
}
