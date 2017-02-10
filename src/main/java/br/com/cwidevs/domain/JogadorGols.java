package br.com.cwidevs.domain;

import org.springframework.data.annotation.Id;

/**
 *
 * @author murillo.goulart
 */
public class JogadorGols {
    
    @Id
    private Long id;    
    
    private Jogador jogador;
    
    private Integer numeroGols; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Integer getNumeroGols() {
        return numeroGols;
    }

    public void setNumeroGols(Integer numeroGols) {
        this.numeroGols = numeroGols;
    }
    
    
}
