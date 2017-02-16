package br.com.cwidevs.domain;

import java.time.LocalDate;
import java.util.Set;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author murillo.goulart
 */
@Document
public class Partida {

    @Id
    private String id;
    
    @NotNull
    private String adversario;
    
    @NotNull
    private LocalDate dataRealizacao;

    @Min(0)
    @NotNull
    private Integer golsPro;
    
    @Min(0)
    @NotNull
    private Integer golsContra;

    private Set<JogadorGols> jogadoresGols;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
