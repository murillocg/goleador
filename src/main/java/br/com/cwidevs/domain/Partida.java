package br.com.cwidevs.domain;

import java.time.LocalDate;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author murillo.goulart
 */
@Document
public class Partida {

    @Id
    private Long id;
        
    private String adversario;
    
    private LocalDate dataRealizacao;

    private Integer golsPro;
    
    private Integer golsContra;

    private Set<JogadorGols> jogadoresGols;

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

    public Set<JogadorGols> getJogadoresGols() {
        return jogadoresGols;
    }

    public void setJogadoresGols(Set<JogadorGols> jogadoresGols) {
        this.jogadoresGols = jogadoresGols;
    }

    
}
