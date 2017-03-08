package br.com.cwidevs.resource.vm;

/**
 *
 * @author murillo.goulart
 */
public class PartidaJogadorVM {

    private Long jogadorId;
    private Integer gols;

    public PartidaJogadorVM() {
    }

    public PartidaJogadorVM(Long jogadorId, Integer numeroGols) {
        this.jogadorId = jogadorId;
        this.gols = numeroGols;
    }

    public Long getJogadorId() {
        return jogadorId;
    }

    public void setJogadorId(Long jogadorId) {
        this.jogadorId = jogadorId;
    }

    public Integer getGols() {
        return gols;
    }

    public void setGols(Integer gols) {
        this.gols = gols;
    }

}
