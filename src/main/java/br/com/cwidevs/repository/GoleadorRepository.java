package br.com.cwidevs.repository;

import br.com.cwidevs.domain.Partida;
import br.com.cwidevs.dto.Goleador;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.stereotype.Repository;

/**
 *
 * @author murillo.goulart
 */
@Repository
public class GoleadorRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public GoleadorRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Goleador> list() {
        return mongoTemplate.aggregate(Aggregation.newAggregation(
                unwind("jogadoresGols"),
                group("jogadoresGols.jogador.nome").sum("jogadoresGols.numeroGols").as("totalGols"),
                project("totalGols").and("jogador").previousOperation(),
                sort(Sort.Direction.DESC, "totalGols")
        ), Partida.class, Goleador.class).getMappedResults();
    }

}
