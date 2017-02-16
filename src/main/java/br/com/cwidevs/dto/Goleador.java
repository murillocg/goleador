package br.com.cwidevs.dto;

/**
 *
 * @author murillo.goulart
 */
public class Goleador {
    
    private String jogador;
    
    private Integer totalGols;

    public String getJogador() {
        return jogador;
    }

    public void setJogador(String jogador) {
        this.jogador = jogador;
    }

    public Integer getTotalGols() {
        return totalGols;
    }

    public void setTotalGols(Integer totalGols) {
        this.totalGols = totalGols;
    }
    
}
