package br.com.cwidevs.resource.vm;

import br.com.cwidevs.domain.Partida;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * Classe responsável por representar uma partida no front-end
 * 
 * @author murillo.goulart
 */
public class PartidaVM implements Serializable {
    
    private Long id;
    
    private String adversario;
    
    private LocalDate dataRealizacao;

    private Integer golsPro;
    
    private Integer golsContra;

    private Set<PartidaJogadorVM> jogadoresGols;

    public PartidaVM() {
    }
    
    public PartidaVM(Partida partida) {
       this.id = partida.getId();
       this.adversario = partida.getAdversario();
       this.dataRealizacao = partida.getDataRealizacao();
       this.golsPro = partida.getGolsPro();
       this.golsContra = partida.getGolsContra();
       this.jogadoresGols = new HashSet<>();
       partida.getJogadoresGols().forEach(
            jogadorGol -> this.jogadoresGols.add(
                    new PartidaJogadorVM(
                            jogadorGol.getId().getJogador().getId(), 
                            jogadorGol.getGols()
                    )
            )
       );
    }

    public PartidaVM(Long id, String adversario, LocalDate dataRealizacao, Integer golsPro, Integer golsContra, Set<PartidaJogadorVM> jogadoresGols) {
        this.id = id;
        this.adversario = adversario;
        this.dataRealizacao = dataRealizacao;
        this.golsPro = golsPro;
        this.golsContra = golsContra;
        this.jogadoresGols = jogadoresGols;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdversario() {
        return adversario;
    }

    public void setAdversario(String adversario) {
        this.adversario = adversario;
    }

    public LocalDate getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(LocalDate dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public Integer getGolsPro() {
        return golsPro;
    }

    public void setGolsPro(Integer golsPro) {
        this.golsPro = golsPro;
    }

    public Integer getGolsContra() {
        return golsContra;
    }

    public void setGolsContra(Integer golsContra) {
        this.golsContra = golsContra;
    }

    public Set<PartidaJogadorVM> getJogadoresGols() {
        return jogadoresGols;
    }

    public void setJogadoresGols(Set<PartidaJogadorVM> jogadoresGols) {
        this.jogadoresGols = jogadoresGols;
    }
    
}
