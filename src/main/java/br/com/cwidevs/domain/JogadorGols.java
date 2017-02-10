package br.com.cwidevs.domain;

/**
 *
 * @author murillo.goulart
 */
public class JogadorGols {
    
    private Jogador jogador;
    
    private Integer numeroGols; 

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
