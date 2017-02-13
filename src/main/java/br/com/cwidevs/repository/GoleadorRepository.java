package br.com.cwidevs.repository;

import br.com.cwidevs.domain.Partida;
import br.com.cwidevs.dto.Goleador;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
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
        SortOperation sortOperation = buildSortOpertation();
        GroupOperation groupOperation = buildGroupOperation();

        return mongoTemplate.aggregate(Aggregation.newAggregation(
                //sortOperation,
                groupOperation
        ), Partida.class, Goleador.class).getMappedResults();
    }

    private GroupOperation buildGroupOperation() {
        return group("jogadoresGols.jogador.nome")
                .first("jogadoresGols.jogador.nome").as("jogador")
                .sum("jogadoresGols.jogador.numeroGols").as("totalGols");
    }

    private SortOperation buildSortOpertation() {
        return sort(Sort.Direction.DESC, "numeroGols");
    }
    
}
