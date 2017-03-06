package br.com.cwidevs;

import br.com.cwidevs.domain.Jogador;
import br.com.cwidevs.domain.PartidaJogador;
import br.com.cwidevs.domain.Partida;
import br.com.cwidevs.domain.PartidaJogadorPK;
import br.com.cwidevs.repository.JogadorRepository;
import br.com.cwidevs.repository.PartidaRepository;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GoleadorApplication implements CommandLineRunner {

    @Autowired
    private PartidaRepository partidaRepository;
    
    @Autowired
    private JogadorRepository jogadorRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(GoleadorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        
        Partida dezFev = new Partida();
        
        Jogador murillo = new Jogador();
        murillo.setNome("Murillo");
        Jogador dudu = new Jogador();
        dudu.setNome("Eduardo");
        Jogador gabriel = new Jogador();
        gabriel.setNome("Gabriel");
        
        murillo = jogadorRepository.save(murillo);
        dudu = jogadorRepository.save(dudu);
        gabriel = jogadorRepository.save(gabriel);

        PartidaJogador golsMurillo = new PartidaJogador();
        golsMurillo.setGols(6);
        PartidaJogadorPK golsMurilloPK = new PartidaJogadorPK();
        golsMurilloPK.setPartida(dezFev);
        golsMurilloPK.setJogador(murillo);
        golsMurillo.setId(golsMurilloPK);
        
        PartidaJogador golsDudu = new PartidaJogador();
        golsDudu.setGols(4);
        PartidaJogadorPK golsDuduPK = new PartidaJogadorPK();
        golsDuduPK.setPartida(dezFev);
        golsDuduPK.setJogador(dudu);
        golsDudu.setId(golsDuduPK);
        
        Set<PartidaJogador> golsDezFev = new HashSet<>();
        golsDezFev.add(golsMurillo);
        golsDezFev.add(golsDudu);
        
        
        dezFev.setId(1L);
        dezFev.setAdversario("Dois irmãos");
        dezFev.setDataRealizacao(LocalDate.of(2017, 02, 03));
        dezFev.setGolsPro(10);
        dezFev.setGolsContra(3);
        dezFev.setJogadoresGols(golsDezFev);
               
        partidaRepository.save(dezFev);

        Partida tresFev = new Partida();

        Set<PartidaJogador> golsTresFev = new HashSet<>();

        PartidaJogador golsMurilloTres = new PartidaJogador();
        golsMurilloTres.setGols(12);
        PartidaJogadorPK golsMurilloTresPK = new PartidaJogadorPK();
        golsMurilloTresPK.setPartida(tresFev);
        golsMurilloTresPK.setJogador(murillo);
        golsMurilloTres.setId(golsMurilloTresPK);

        PartidaJogador golsDuduTres = new PartidaJogador();
        golsDuduTres.setGols(5);
        PartidaJogadorPK golsDuduTresPK = new PartidaJogadorPK();
        golsDuduTresPK.setPartida(tresFev);
        golsDuduTresPK.setJogador(dudu);
        golsDuduTres.setId(golsDuduTresPK);
        
        golsTresFev.add(golsMurilloTres);
        golsTresFev.add(golsDuduTres);
        
        tresFev.setId(2L);
        tresFev.setAdversario("Amigos Greminho");
        tresFev.setDataRealizacao(LocalDate.now());
        tresFev.setGolsPro(17);
        tresFev.setGolsContra(1);
        tresFev.setJogadoresGols(golsTresFev);
        partidaRepository.save(tresFev);
        
    }
}
