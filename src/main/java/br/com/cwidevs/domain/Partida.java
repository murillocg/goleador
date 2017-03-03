package br.com.cwidevs.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author murillo.goulart
 */
@Entity
public class Partida implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
    private Long id;
    
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

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<PartidaJogador> jogadoresGols = new HashSet<>();

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

    public Set<PartidaJogador> getJogadoresGols() {
        return jogadoresGols;
    }

    public void setJogadoresGols(Set<PartidaJogador> jogadoresGols) {
        this.jogadoresGols = jogadoresGols;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Partida other = (Partida) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
