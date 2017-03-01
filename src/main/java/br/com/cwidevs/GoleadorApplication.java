package br.com.cwidevs;

import br.com.cwidevs.domain.Jogador;
import br.com.cwidevs.domain.JogadorGols;
import br.com.cwidevs.domain.Partida;
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
        murillo = jogadorRepository.save(murillo);
        dudu = jogadorRepository.save(dudu);

        JogadorGols golsMurillo = new JogadorGols(6, dezFev, murillo);
        JogadorGols golsDudu = new JogadorGols(4, dezFev, dudu);

        Set<JogadorGols> golsDezFev = new HashSet<>();
        golsDezFev.add(golsMurillo);
        golsDezFev.add(golsDudu);
        
        
        dezFev.setId(1L);
        dezFev.setAdversario("Dois irmãos");
        dezFev.setDataRealizacao(LocalDate.of(2017, 02, 03));
        dezFev.setGolsPro(10);
        dezFev.setGolsContra(3);
        dezFev.setJogadoresGols(golsDezFev);
               
        partidaRepository.save(dezFev);

//        Partida tresFev = new Partida();
//
//        Set<JogadorGols> golsTresFev = new HashSet<>();
//
//        JogadorGols golsMurilloTres = new JogadorGols(tresFev, murillo);
//        golsMurilloTres.setNumeroGols(12);
//
//        JogadorGols golsDuduTres = new JogadorGols(tresFev, dudu);
//        golsDuduTres.setNumeroGols(5);
//
//        golsTresFev.add(golsMurilloTres);
//        golsTresFev.add(golsDuduTres);
//        
//        tresFev.setId(2L);
//        tresFev.setAdversario("Amigos Greminho");
//        tresFev.setDataRealizacao(LocalDate.now());
//        tresFev.setGolsPro(17);
//        tresFev.setGolsContra(1);
//        tresFev.setJogadoresGols(golsTresFev);
//        partidaRepository.save(tresFev);
        
    }
}
