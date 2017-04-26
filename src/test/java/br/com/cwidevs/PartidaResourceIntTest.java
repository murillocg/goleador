package br.com.cwidevs;

import br.com.cwidevs.domain.Jogador;
import br.com.cwidevs.domain.Partida;
import br.com.cwidevs.repository.PartidaRepository;
import br.com.cwidevs.resource.PartidaResource;
import br.com.cwidevs.resource.vm.PartidaJogadorVM;
import br.com.cwidevs.resource.vm.PartidaVM;
import br.com.cwidevs.service.PartidaService;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Test class for the PartidaResource REST controller.
 *
 * @author murillo.goulart
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GoleadorApplication.class)
public class PartidaResourceIntTest {

    @Autowired
    private PartidaRepository partidaRepository;

    @Autowired
    private PartidaService partidaService;

    @Autowired
    private EntityManager em;

    private MockMvc restMockMvc;

    private Jogador jogadorFulano;

    private Jogador jogadorBeltrano;
    
    private PartidaVM partida;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PartidaResource partidaResource = new PartidaResource();
        ReflectionTestUtils.setField(partidaResource, "partidaRepository", partidaRepository);
        ReflectionTestUtils.setField(partidaResource, "partidaService", partidaService);
        this.restMockMvc = MockMvcBuilders.standaloneSetup(partidaResource).build();
    }

    /**
     * Create entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PartidaVM createPartida() {
        PartidaVM partida = new PartidaVM(
                null, //id
                "Bento XV", //adversário
                LocalDate.now(), //dataRealização
                10, //golsPro
                5, //golsContra
                null //jogadoresGols
        );        
        return partida;
    }
    
    /**
     * Save entities for this test.
     *
     */
    public void saveJogadores(EntityManager em) {
        jogadorFulano = new Jogador();
        jogadorFulano.setNome("Fulano");
        em.persist(jogadorFulano);

        jogadorBeltrano = new Jogador();
        jogadorBeltrano.setNome("Beltrano");
        em.persist(jogadorBeltrano);
        
        em.flush();
    }

    @Before
    public void initTest() {
        saveJogadores(em);
        partida = createPartida();
    }

    @Test
    @Transactional
    public void createOrUpdate() throws Exception {
        
        int databaseSizeBeforeCreate = partidaRepository.findAll().size();
        Set<PartidaJogadorVM> jogadoresPartida = new HashSet<>();
        jogadoresPartida.add(new PartidaJogadorVM(jogadorFulano.getId(), 5));
        jogadoresPartida.add(new PartidaJogadorVM(jogadorBeltrano.getId(), 5));
        partida.setJogadoresGols(jogadoresPartida);

        restMockMvc.perform(
                post("/api/partidas")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(partida)))
                .andExpect(status().isOk());

        // Validate in the database
        List<Partida> partidas = partidaRepository.findAll();
        assertThat(partidas).hasSize(databaseSizeBeforeCreate + 1);
        Partida testPartida = partidas.get(partidas.size() - 1);
        assertThat(testPartida.getAdversario()).isEqualTo("Bento XV");
        assertThat(testPartida.getGolsPro()).isEqualTo(10);
        assertThat(testPartida.getGolsContra()).isEqualTo(5);
    }

    @Test
    @Transactional
    public void deleteOne() throws Exception {
        // Initialize the database
        Set<PartidaJogadorVM> jogadoresPartida = new HashSet<>();
        jogadoresPartida.add(new PartidaJogadorVM(jogadorFulano.getId(), 5));
        jogadoresPartida.add(new PartidaJogadorVM(jogadorBeltrano.getId(), 5));
        partida.setJogadoresGols(jogadoresPartida);
        Partida partidaEntity = partidaService.createPartida(partida);

        int databaseSizeBeforeDelete = partidaRepository.findAll().size();
        
        restMockMvc.perform(
                delete("/api/partidas/{id}", partidaEntity.getId())
                    .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isNoContent());

        List<Partida> partidas = partidaRepository.findAll();
        assertThat(partidas).hasSize(databaseSizeBeforeDelete - 1);
    }
    
    @Test
    @Transactional
    public void getAll() throws Exception {
        // Initialize the database
        Set<PartidaJogadorVM> jogadoresPartida = new HashSet<>();
        jogadoresPartida.add(new PartidaJogadorVM(jogadorFulano.getId(), 5));
        jogadoresPartida.add(new PartidaJogadorVM(jogadorBeltrano.getId(), 5));
        partida.setJogadoresGols(jogadoresPartida);
        Partida partidaEntity = partidaService.createPartida(partida);

        // Get all the list
        restMockMvc.perform(get("/api/partidas"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(partidaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].adversario").value(hasItem(partidaEntity.getAdversario())))
            .andExpect(jsonPath("$.[*].golsPro").value(hasItem(partidaEntity.getGolsPro())))
            .andExpect(jsonPath("$.[*].golsContra").value(hasItem(partidaEntity.getGolsContra())))
            .andExpect(jsonPath("$.[*].jogadoresGols.[*].jogadorId").value(hasItem(jogadorFulano.getId().intValue())))
            .andExpect(jsonPath("$.[*].jogadoresGols.[*].gols").value(hasItem(5)));        
    }
    
    @Test
    @Transactional
    public void checkInvalidSumGols() throws Exception {
        
        Set<PartidaJogadorVM> jogadoresPartida = new HashSet<>();
        jogadoresPartida.add(new PartidaJogadorVM(jogadorFulano.getId(), 1));
        jogadoresPartida.add(new PartidaJogadorVM(jogadorBeltrano.getId(), 1));
        partida.setJogadoresGols(jogadoresPartida);

        restMockMvc.perform(
                post("/api/partidas")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(partida)))
                .andExpect(status().isBadRequest());        
    }
    

}
