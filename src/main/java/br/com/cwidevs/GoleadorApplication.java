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
        murillo.setId(1L);
        murillo.setNome("Murillo");
        
        JogadorGols golsMurillo = new JogadorGols();
        golsMurillo.setJogador(murillo);
        golsMurillo.setNumeroGols(10);
        Set<JogadorGols> golsDezFev = new HashSet<>();
        golsDezFev.add(golsMurillo);
        
        Partida dezFev = new Partida();
        dezFev.setId(1L);
        dezFev.setAdversario("Dois irmãos");
        dezFev.setDataRealizacao(LocalDate.now());
        dezFev.setGolsPro(10);
        dezFev.setGolsContra(3);
        dezFev.setJogadoresGols(golsDezFev);
               
        repository.save(dezFev);
    }
}
