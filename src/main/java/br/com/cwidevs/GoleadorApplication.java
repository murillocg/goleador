package br.com.cwidevs;

import br.com.cwidevs.domain.Jogador;
import br.com.cwidevs.domain.JogadorGols;
import br.com.cwidevs.domain.Partida;
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
    private PartidaRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(GoleadorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        
        Jogador murillo = new Jogador();
        murillo.setNome("Murillo");
        JogadorGols golsMurillo = new JogadorGols();
        golsMurillo.setJogador(murillo);
        golsMurillo.setNumeroGols(6);

        Jogador dudu = new Jogador();
        dudu.setNome("Eduardo");
        JogadorGols golsDudu = new JogadorGols();
        golsDudu.setJogador(dudu);
        golsDudu.setNumeroGols(4);

        Set<JogadorGols> golsDezFev = new HashSet<>();
        golsDezFev.add(golsMurillo);
        golsDezFev.add(golsDudu);
        
        Partida dezFev = new Partida();
        dezFev.setId("1");
        dezFev.setAdversario("Dois irmãos");
        dezFev.setDataRealizacao(LocalDate.of(2017, 02, 03));
        dezFev.setGolsPro(10);
        dezFev.setGolsContra(3);
        dezFev.setJogadoresGols(golsDezFev);
               
        repository.save(dezFev);

        Set<JogadorGols> golsTresFev = new HashSet<>();

        JogadorGols golsMurilloTres = new JogadorGols();
        golsMurilloTres.setJogador(murillo);
        golsMurilloTres.setNumeroGols(12);

        JogadorGols golsDuduTres = new JogadorGols();
        golsDuduTres.setJogador(dudu);
        golsDuduTres.setNumeroGols(5);

        golsTresFev.add(golsMurilloTres);
        golsTresFev.add(golsDuduTres);
        
        Partida tresFev = new Partida();
        tresFev.setId("2");
        tresFev.setAdversario("Amigos Greminho");
        tresFev.setDataRealizacao(LocalDate.now());
        tresFev.setGolsPro(17);
        tresFev.setGolsContra(1);
        tresFev.setJogadoresGols(golsTresFev);
        repository.save(tresFev);
        
    }
}
